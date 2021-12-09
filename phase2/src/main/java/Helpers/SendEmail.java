package main.java.Helpers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    private final String username;
    private final String useremailid;
    private final String rafflename;

    public SendEmail(String user, String emailID, String raffle) {
        this.username = user;
        this.useremailid = emailID;
        this.rafflename = raffle;
    }

    public void send() {
        //authentication
        final String username = "skyscraperraffles@gmail.com";
        final String password = "@207phase2";
        String fromEmail = "skyscraperraffles@gmail.com";

        //server setup
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        //mail constructor
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.useremailid));
            msg.setSubject("Winner Winner Chicken Dinner! Skyscraper Raffle Win Notification");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Dear " + this.username + ",\n"
                    + "Congratulations! You have won " + this.rafflename + "!" +
                    "\n" +
                    "You can expect your prize in 2-3 business days, or will receive it online. " +
                    "Please open Skyscraper Raffles to confirm your win!\n" +
                    "\n" +
                    "We hope you enjoyed participating in the Raffle, " +
                    "head over to our application to participate and win more prizes! \n" +
                    "\n" +
                    "Best Regards,\n" +
                    "The Sedative Skyscraper Raffle Team.");

            emailContent.addBodyPart(textBodyPart);


            msg.setContent(emailContent);

            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}