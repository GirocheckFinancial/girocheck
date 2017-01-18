/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.ServerJNDI;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.vtsuite.servercommon.model.Host;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class HostManagerTest {

    private Queue hostInQueue;
    private Queue hostOutQueue;
    private QueueConnectionFactory connectionFactory;
    

    public HostManagerTest() {
   
    }

    static class TestClass {

        String name = null;
        String Address = null;

    }

    /**
     * Test of onMessage method, of class IStreamMDB.
     *
     * @param args
     * @throws javax.jms.JMSException
     */

    public static void main (String ... args ) throws JMSException {
        
        HostManagerTest hmt = new HostManagerTest();
        JMSManager jmsManager = JMSManager.get();

        try {
            InitialContext ctx = new InitialContext();
            hmt.hostInQueue = (javax.jms.Queue) ctx.lookup( ServerJNDI.HOST_IN_QUEUE_JNDI );
            hmt.connectionFactory = (QueueConnectionFactory) ctx.lookup( ServerJNDI.CONNECTION_FACTORY_JNDI );
        } catch ( NamingException e ) {
          
        }


        TestClass testing = new TestClass();

        jmsManager.send( testing, jmsManager.getHostInQueue() );

        System.out.println( "************************* [ISTREAM_MDB] *************************************" );
    }

}
