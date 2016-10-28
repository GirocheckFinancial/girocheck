/*
 ** File: EmailUtils.java
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
package com.smartbt.girocheck.servercommon.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * The EmailUtils class
 */
public class EmailUtils {

    private String smtpHostAddress = null;
    private String smtpHostPort = null;
    private String smtpUsername = null;
    private String smtpPassword = null;
    ///
    private String emailMessage = null;
    private String emailMessageContentType = null;
    ///
    private String imageBase64 = null;
    private String imageContentType = null;
    private String imageContentID = null;
    private String imageName = null;
    
    private List<ImagePart> images = new ArrayList<ImagePart>();
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailUtils.class);

    public void addImage(ImagePart image){
        images.add(image);
    }
    
    /**
     * Constructor for no authentication
     *
     * @param smtpHostAddress The SMTP host address
     * @param smtpHostPort The SMTP host port
     */
    public EmailUtils(String smtpHostAddress, String smtpHostPort) {
        this.smtpHostAddress = smtpHostAddress;
        this.smtpHostPort = smtpHostPort;
    }

    /**
     * Constructor with authentication parameters
     *
     * @param smtpHostAddress The SMTP host address
     * @param smtpHostPort The SMTP host port
     * @param smtpUsername The SMTP username
     * @param smtpPassword The SMTP password
     */
    public EmailUtils(String smtpHostAddress, String smtpHostPort, String smtpUsername, String smtpPassword) {
        this.smtpHostAddress = smtpHostAddress;
        this.smtpHostPort = smtpHostPort;
        this.smtpUsername = smtpUsername;
        this.smtpPassword = smtpPassword;
    }

    /**
     * Set the message body and content type
     *
     * @param emailMessage The message body
     * @param emailMessageContentType The content type, i.e. "text/html"
     */
    public void setMessage(String emailMessage, String emailMessageContentType) {
        this.emailMessage = emailMessage;
        this.emailMessageContentType = emailMessageContentType;
    }

    /**
     * Set the attached image
     *
     * @param imageBase64 The base-64 encoded signature
     * @param imageName The image name
     * @param imageContentType The image content type
     * @param imageContentID The image content id - matching message body
     */
    public void setImage(String imageBase64, String imageName, String imageContentType, String imageContentID) {
        this.imageBase64 = imageBase64;
        this.imageName = imageName;
        this.imageContentType = imageContentType;
        this.imageContentID = imageContentID;
    }

    /**
     * Send email
     *
     * @param recipients The array of email addresses
     * @param from The from email address
     * @param subject The email subject
     * @param debug Flag to enable or disable SMTP debug output
     */
    public void sendEmail(String recipients[], String from, String subject, boolean debug) {

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHostAddress);
        props.put("mail.smtp.port", smtpHostPort);

        if (debug) {
//            log.info("== Attempting to send email with host: " + smtpHostAddress);
//            log.info("== Attempting to send email with port: " + smtpHostPort);
//            log.info("== Attempting to send email with smtpUsername: " + smtpUsername);
//            log.info("== Attempting to send email with smtpPassword: " + smtpPassword);
        }
        
        Session session = null;

        if (smtpUsername != null) {

            props.put("mail.smtp.auth", "true");

            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUsername, smtpPassword);
                }
            });
        } else {

            props.put("mail.smtp.auth", "false");
            session = Session.getInstance(props);
        }

        session.setDebug(debug);

        Message msg = new MimeMessage(session);

        try {

            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            msg.setSubject(subject);

            Multipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(getMessagePart());

                for (ImagePart image : images) {
                    multipart.addBodyPart(getImagePart(image));
                }
            

            msg.setContent(multipart);

            InternetAddress[] addressTo = new InternetAddress[1];

            for (int i = 0; i < recipients.length; i++) {
                System.out.println("sending email to " + recipients[i]);
                if (debug) {
//                    log.info("== Sending email to: " + recipients[i]);
                }
                addressTo[0] = new InternetAddress(recipients[i]);
                msg.setRecipients(Message.RecipientType.TO, addressTo);
                Transport.send(msg);
            }

        } catch (MessagingException ex) {
//            log.debug("Error sending email.");
            ex.printStackTrace();
        }
    }

    private BodyPart getMessagePart() throws MessagingException {

        if (emailMessage == null) {
            throw new MessagingException("No message body.");
        }

        if (emailMessageContentType == null) {
            throw new MessagingException("No message content type.");
        }

        BodyPart messagePart = new MimeBodyPart();
        messagePart.setContent(emailMessage, emailMessageContentType);
        return messagePart;
    }

    private BodyPart getImagePart() throws MessagingException {

        if (imageBase64 == null) {
            throw new MessagingException("No image.");
        }

        if (imageContentType == null) {
            throw new MessagingException("No imagecontent type.");
        }

        if (imageContentID == null) {
            throw new MessagingException("No image content id.");
        }

        if (imageName == null) {
            throw new MessagingException("No image name.");
        }

        InternetHeaders headers = new InternetHeaders();
        headers.addHeader("Content-Type", imageContentType);
        headers.addHeader("Content-Transfer-Encoding", "base64");
        MimeBodyPart imagePart = new MimeBodyPart(headers, imageBase64.getBytes());
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.setContentID("<" + imageContentID + ">");
        imagePart.setFileName(imageName);

        return imagePart;
    }

    private BodyPart getImagePart(ImagePart image) throws MessagingException {

        if (image.getImageBase64() == null) {
            throw new MessagingException("No image.");
        }

        if (image.getImageContentType() == null) {
            throw new MessagingException("No imagecontent type.");
        }

        if (image.getImageContentID() == null) {
            throw new MessagingException("No image content id.");
        }

        if (image.getImageName() == null) {
            throw new MessagingException("No image name.");
        }

        InternetHeaders headers = new InternetHeaders();
        headers.addHeader("Content-Type", image.getImageContentType());
        headers.addHeader("Content-Transfer-Encoding", "base64");
        MimeBodyPart imagePart = new MimeBodyPart(headers, image.getImageBase64().getBytes());
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.setContentID("<" + image.getImageContentID() + ">");
        imagePart.setFileName(image.getImageName());

        return imagePart;
    }
    
    
}
