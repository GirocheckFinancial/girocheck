/*
 ** File: SbtSuiteJmsManager.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.jms;

import com.smartbt.girocheck.common.ServerJNDI;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 * The JMS Manager class
 */
public class JMSManager {

    private Queue watchdogQueue;
    private Queue coreInQueue;
    private Queue coreOutQueue;
    private Queue core2InQueue;
    private Queue core2OutQueue;
    private Queue hostInQueue;
    private Queue hostOutQueue;
    private Queue frontIStreamInQueue;
    private Queue frontIStreamOutQueue;
    private QueueConnectionFactory connectionFactory;
    private long timelife;
    private static JMSManager manager;
//    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(com.smartbt.girocheck.servercommon.jms.JMSManager.class);

    private JMSManager() {
        //    timelife = Long.parseLong(SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.BEAN_SEND_TIMEOUT_MS.getViewValue()));
        timelife = 2000;
        initJMS();
    }

    public static JMSManager get() {
        if (manager == null) {
            manager = new JMSManager();
        }
        return manager;
    }

    
    
    
    public void scanQueue(Queue q, String selector) throws JMSException {
        Connection connection = null;
        Session session = null;
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
       QueueBrowser browser =  session.createBrowser(q, selector);
        
      //   QueueBrowser  myBrowser = jmsManager.createBrowser(myDest)
       DirexTransactionRequest request;         
       
                Enumeration queueMessages = browser.getEnumeration();
               
                
                Message eachMessage;

                while (queueMessages.hasMoreElements()) {
                    Object obj = queueMessages.nextElement();
                    
                     if (obj instanceof ObjectMessage) {
                         System.out.println("ObjectMessage");
                         ObjectMessage objMessage = (ObjectMessage) obj;
                         Serializable s = objMessage.getObject();
                         request = (DirexTransactionRequest) s;
                    }else{
                         System.out.println("Unknown.");
                         }
                
    }
                
    }
    
    public void scanQueue(Queue q) throws JMSException {
        Connection connection = null;
        Session session = null;
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
       QueueBrowser browser =  session.createBrowser(q);
        
      //   QueueBrowser  myBrowser = jmsManager.createBrowser(myDest)
       DirexTransactionRequest request;         
       
                Enumeration queueMessages = browser.getEnumeration();
               
                
                Message eachMessage;

                while (queueMessages.hasMoreElements()) {
                    Object obj = queueMessages.nextElement();
                    
                     if (obj instanceof ObjectMessage) {
                         System.out.println("ObjectMessage");
                         ObjectMessage objMessage = (ObjectMessage) obj;
                         
                         Serializable s = objMessage.getObject();
                         request = (DirexTransactionRequest) s;
                    }else{
//                         log.debug("Unknown.");
                         }
                
    }
                
                System.out.println("------ -------");
    }

    private void initJMS() {
        try {
            InitialContext ctx = new InitialContext();
            //      watchdogQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.WATCHDOG_QUEUE_JNDI);
            coreInQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.CORE_IN_QUEUE_JNDI);	
            
            if(coreInQueue == null){
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[JMSManager]  coreInQueue == null",null );
            }else{
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[JMSManager]  coreInQueue has Value",null );
            }
            coreOutQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.CORE_OUT_QUEUE_JNDI);
            core2InQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.CORE2_IN_QUEUE_JNDI);
            core2OutQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.CORE2_OUT_QUEUE_JNDI);
            hostInQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.HOST_IN_QUEUE_JNDI);
            hostOutQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.HOST_OUT_QUEUE_JNDI);
            frontIStreamInQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.FRONT_ISTREAM_IN_QUEUE_JNDI);
            frontIStreamOutQueue = (javax.jms.Queue) ctx.lookup(ServerJNDI.FRONT_ISTREAM_OUT_QUEUE_JNDI);
            connectionFactory = (QueueConnectionFactory) ctx.lookup(ServerJNDI.CONNECTION_FACTORY_JNDI);
        } catch (NamingException e) {
//            log.error(" Error getting web resources: " + e.getMessage(), e);
        }
    }

    public void send(Object obj, Queue queue) throws JMSException {
        send(obj, queue, UtilsJMS.generateGUID());
    }

    /**
     * Sends message to queue.
     *
     * @param obj The object to send
     * @param queue The queue
     * @param CorrelationId
     * @throws JMSException
     */
    public void send(Object obj, Queue queue, String CorrelationId) throws JMSException {
        Connection connection = null;
        Session session = null;
        Message message = null;
        MessageProducer producer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            message = session.createObjectMessage((Serializable) obj);
            message.setStringProperty("DestinationQueue", queue.getQueueName());

            message.setJMSCorrelationID(CorrelationId);
            producer = session.createProducer(queue);
            producer.setTimeToLive(timelife);
            producer.send(message);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (producer != null) {
                try {
                    producer.close();
                } catch (Exception ignore) {
                }
            }

            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void sendWithProps(Object obj, Queue queue, String CorrelationId, Properties props) throws JMSException {
        Connection connection = null;
        Session session = null;
        Message message;
        MessageProducer producer = null;
        Enumeration keysEnum = props.keys();

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            message = session.createObjectMessage((Serializable) obj);
            message.setStringProperty("DestinationQueue", queue.getQueueName());

            while (keysEnum.hasMoreElements()) {
                String key = (String) keysEnum.nextElement();
                String value = props.getProperty(key);
                message.setStringProperty(key, value);
            }

            message.setJMSCorrelationID(CorrelationId);
            producer = session.createProducer(queue);
            producer.setTimeToLive(timelife);
            producer.send(message);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    /**
     * Receives message from queue.
     *
     * @param queue The queue
     * @param cf The connection factory
     * @param id The correlation id
     * @param waittime The time to wait
     * @return
     * @throws IOException
     * @throws JMSException
     */
    public Message receive(Queue queue, String CorrelationId, long waittime) throws IOException, JMSException {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        Message message = null;
        String corralated = "JMSCorrelationID = '" + CorrelationId + "'";
        try {

            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            consumer = session.createConsumer(queue, corralated);
            message = consumer.receive(waittime);
         

        } catch (JMSException e) {
            e.getMessage();
        } finally {
            if (consumer != null) {
                try {
                    consumer.close();
                } catch (Exception ignore) {
                }
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    public Queue getWatchdogQueue() {
        return watchdogQueue;
    }

    public Queue getCoreInQueue() {
        return coreInQueue;
    }

    public Queue getCoreOutQueue() {
        return coreOutQueue;
    }

    public Queue getHostInQueue() {
        return hostInQueue;
    }

    public Queue getHostOutQueue() {
        return hostOutQueue;
    }

    public Queue getCore2InQueue() {
        return core2InQueue;
    }

    public Queue getCore2OutQueue() {
        return core2OutQueue;
    }
    
    

    public static JMSManager getManager() {
        return manager;
    }

    public static Logger getLog() {
        return null;
    }

    /**
     * @return the frontIStreamInQueue
     */
    public Queue getFrontIStreamInQueue() {
        return frontIStreamInQueue;
    }


    /**
     * @return the frontIStreamOutQueue
     */
    public Queue getFrontIStreamOutQueue() {
        return frontIStreamOutQueue;
    }

   
}
