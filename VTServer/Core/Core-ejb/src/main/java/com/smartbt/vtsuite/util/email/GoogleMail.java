package com.smartbt.vtsuite.util.email;

import com.smartbt.girocheck.servercommon.dao.EmailDAO;
import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.manager.EmailManager;
import com.smartbt.girocheck.servercommon.model.Email;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author doraemon
 */
public class GoogleMail {

    private EmailManager emailManager = EmailManager.get();

    public static GoogleMail INSTANCE;

    public static GoogleMail get() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMail();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws MessagingException {
        System.out.println("Sending email...");
        String body = "This is an email generated automatically by <b>Girocheck</b>,\n"
                + "<br>\n"
                + "The reason of this email is comunicate to <b>Tecnicard</b> that the user user_name performed a successful second load to the card ending in {card_last4}.\n"
                + "<br><br>\n"
                + "This is the personal information of the user:\n"
                + "<br>\n"
                + "<b>Name:</b>&nbsp;user_name\n"
                + "<br>\n"
                + "<b>Last Name:</b>&nbsp;user_lastname\n"
                + "<br>\n"
                + "<b>SSN:</b>&nbsp;user_ssn\n"
                + "<br>\n"
                + "<b>DOB:</b>&nbsp;user_dob\n"
                + "<br>\n"
                + "<b>Phone:</b>&nbsp;user_phone\n"
                + "<br>\n"
                + "<b>Address:</b>&nbsp;user_address\n"
                + "<br>\n"
                + "For further information, please contact Girocheck Financial INC.";

        Map<String, String> values = new HashMap<String, String>();
        values.put("user_name", "John");
        values.put("user_lastname", "Smith");
        values.put("user_ssn", "123456789");
        values.put("user_dob", "01/03/1988");
        values.put("user_phone", "7864540209");
        values.put("user_address", "9840 Palmetto Club Dr, Miami FL, 33157");

        Iterator<String> keys = values.keySet().iterator();
        String b = body;
        while (keys.hasNext()) {
            String keyword = keys.next();
            b = b.replaceAll( keyword, values.get(keyword));
        }
        System.out.println("Email sent");
        System.out.println(b);
    }

    public void sendEmail(Email email) throws EmailException, MessagingException {

        String recipient = email.getRecipients();
        String[] ccList;

        if (recipient == null) {
            throw new EmailException("Recipients list is empty.");
        }

        if (recipient.contains(",")) {
            String[] recipientList = recipient.split(",");
            recipient = recipientList[0];

            ccList = new String[recipientList.length - 1];

            for (int i = 1; i < recipientList.length; i++) {
                ccList[i - 1] = recipientList[i];
            }
        } else {
            ccList = new String[0];
        }

        Send(email.getUsername(), email.getPassword(), recipient, ccList, email.getTitle(), email.getFormatBody());
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the
     * connected state or if the message is not a MimeMessage
     */
    public static void SendEmail(String title, String body) throws AddressException, MessagingException {
        Send("cubacomprar", "hocuspocus", "robertosoftwareengineer@gmail.com", new String[]{"titorobe@yahoo.com", "robesolutions@gmail.com"}, title, body);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the
     * connected state or if the message is not a MimeMessage
     */
    public static void Send(final String username, final String password, String recipientEmail, String[] ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
         If set to false, the QUIT command is sent and the connection is immediately closed. If set 
         to true (the default), causes the transport to wait for the response to the QUIT command.

         ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
         http://forum.java.sun.com/thread.jspa?threadID=5205249
         smtpsend.java - demo program from javamail
         */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        for (int i = 0; i < ccEmail.length; i++) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail[i], false));
        }

        msg.setSubject(title);
        msg.setContent(message, "text/html");
//        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

}
