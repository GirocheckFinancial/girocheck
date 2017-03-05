package com.alodiga.util.email;

import com.alodiga.persistence.dto.Transferencia;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.List;
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

    public static GoogleMail INSTANCE;

    public static GoogleMail get() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMail();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws MessagingException {
        System.out.println("Sending email...");
        SendEmail("Test", "robertoSoftwareEngineer@gmail.com", buildEmail("08/10/2016", "08/10/2016", null));
        System.out.println("Email sent");
    }

    public static void sendReportEmail(String destinatario, String fechaInicio, String fechaFin, List<Transferencia> transferencias) throws MessagingException {
        String body = buildEmail( fechaInicio,  fechaFin, transferencias);
        SendEmail("Transactions Report",destinatario,body);
    }

    private static String buildEmail(String fechaInicio, String fechaFin, List<Transferencia> transferencias) {
        StringBuilder sb = new StringBuilder();

        sb.append("<br>");
        sb.append("<b>Reporte de transferencias</b>");
        sb.append("<br>");
        sb.append("<br>");
        if (fechaInicio != null) {
            sb.append("Desde: " + fechaInicio);
            sb.append("<br>");
        }

        if (fechaFin != null) {
            sb.append("Hasta: " + fechaFin);
            sb.append("<br>");
        }

        sb.append("<br>");
        sb.append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"2\">");

//        for (Transferencia transferencia : transferencias) {
        for (Transferencia transferencia : transferencias) {
            sb.append("<tr style=\"font-weight:bold; background-color:#e0e2e4\"><td>Fecha</td><td>Monto</td><td>Tarifa</td><td>Monto Enviar</td><td>Monto Entregar</td><td>Codigo Envio</td><td>Estatus</td><td>Pais</td></tr>");
            sb.append("<tr                                ><td>" + transferencia.getFecha() + "</td><td>" + transferencia.getDineroEntregado() + "</td><td>" + transferencia.getTarifa() + "</td><td>" + transferencia.getMontoEntregar() + "</td><td>" + transferencia.getDineroEntregado() + "</td><td>" + transferencia.getCodEnvio() + "</td><td>" + (transferencia.getEstatus() == null ? "" : transferencia.getEstatus())  + "</td><td>" + transferencia.getCodPaisDestinatario() + "</td></tr>");
            sb.append("<tr><td style=\"font-weight:bold\">Remitente</td><td  colspan=\"3\">" + transferencia.getNomRemite().replace("%2520", " ") + "</td><td style=\"font-weight:bold\">Destinatario</td><td  colspan=\"3\">" + transferencia.getNomDestinatario().replace("%2520", " ") + "</td></tr>");

//            sb.append("<tr><td style=\"font-weight:bold\">Remitente</td><td>Derick Brol</td><td>Tel:123456789</td><td colspan=\"4\">Dir:9840 Palmetto Club Dr, Miami FL, 33157</td></tr>");
//            sb.append("<tr><td style=\"font-weight:bold\">Destinatario</td><td>Roberto Rodriguez</td><td>Tel:7864540202</td><td colspan=\"4\">Dir:2322 Hidden Glen Dr, Atlanta GA, 30067 </td></tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }

    public static void SendEmail(String title, String destinatario, String body) throws AddressException, MessagingException {
//        Send("alodiga.reportes@gmail.com", "Alodiga1", "derick_brol@hotmail.com", new String[]{""}, title, body);
//        Send("alodiga.reportes@gmail.com", "Alodiga1", "robertosoftwareengineer@gmail.com", new String[]{""}, title, body);
        Send("alodiga.reportes@gmail.com", "Alodiga1", destinatario, new String[]{"titorobe@yahoo.com"}, title, body);
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
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        for (int i = 0; i < ccEmail.length; i++) {
            msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail[i], false));
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
