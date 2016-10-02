package com.aviato;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class BookingEmail {
  private String toDP, toAP, from, host, subject, messageDP, messageAP;
  Properties properties = System.getProperties();
  Session session;

  BookingEmail(String toDP, String toAP) {
    this.toDP = toDP;
    this.toAP = toAP;
    this.from = "raghavp96@gmail.com";
    this.host = "localhost";
    this.subject = "Your flight has been booked!";

    this.messageDP = "Congratulations! One of the interesting people you wanted to take a trip with," +
            toAP + ", has accepted your offer! Enjoy your ride fam. \n Sincerely, Aviato Team.";
    this.messageAP = "Congratulations! The person who wanted to ride with you," +
            toDP + ", has booked your flight! Enjoy your free ride fam. \n Sincerely, Aviato Team.";
  }

  public void sendEmail() {
    // Setup mail server
    properties.setProperty("mail.smtp.host", host);

    // Get the default Session object.
    session = Session.getDefaultInstance(properties);

    try {
      // Create a default MimeMessage object.
      MimeMessage messageToDP = new MimeMessage(session);
      MimeMessage messageToAP = new MimeMessage(session);

      // Set From: header field of the header.
      messageToDP.setFrom(new InternetAddress(from));
      messageToAP.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      messageToDP.addRecipient(Message.RecipientType.TO, new InternetAddress(toDP));
      messageToAP.addRecipient(Message.RecipientType.TO, new InternetAddress(toAP));

      // Set Subject: header field
      messageToDP.setSubject(subject);
      messageToAP.setSubject(subject);

      // Now set the actual message
      messageToDP.setText("" + messageDP);
      messageToAP.setText("" + messageAP);

      // Send message
      Transport.send(messageToDP);
      Transport.send(messageToAP);
      System.out.println("Sent messages successfully....");
    }catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }
}