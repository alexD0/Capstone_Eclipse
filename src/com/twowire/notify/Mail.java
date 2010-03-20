
package com.twowire.notify;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

public class Mail
{   
	
public static void main(String args[])
{
    String host = "smtp.gmail.com";
    String username = "";                                                  ///
    String password = "";                                                  ///
    
    Properties props = new Properties();
    props.put("mail.smtps.auth", "true");
    Session session = Session.getInstance(props, null);              
    try {       
    	MimeMessage msg = new MimeMessage(session);
    	msg.setFrom();                                                     
    	msg.setRecipients(Message.RecipientType.TO,"hrobertu@asu.edu");     ////
    	msg.setSubject("JavaMail");                    
    	msg.setSentDate(new Date());                                       
    	msg.setText("Greetings");                                   
    
    	Transport t = session.getTransport("smtps");
    	t.connect(host, username, password);
    	t.sendMessage(msg, msg.getAllRecipients());
    	t.close();
    } 
    
    catch (MessagingException mex) {
        System.out.println("message send failed" + mex);
    }
        
}

}




