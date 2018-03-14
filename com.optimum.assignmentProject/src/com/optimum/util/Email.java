package com.optimum.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.optimum.model.User;

public class Email {
	public static void main() {
		String to = User.getEmail();
//		String to = "yisheng.wah@theoptimum.net";
		String from = "TheAssignment@theoptimum.net";
		String host="smtp.theoptimum.net";
		Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      properties.put("mail.smtp.port", 587); 	//	Port Number for smtp.theoptimum.net
	      Session session = Session.getDefaultInstance(properties);  
	      try{  
	    	  //	Constructing the Email Contents	---	START
	          MimeMessage message = new MimeMessage(session);  
	          message.setFrom(new InternetAddress(from));  
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	          //	Email Subject	---	START
	          message.setSubject("Account Registration -	The Assignment");  
	          //	Email Subject	---	END
	          //	Email Body	---	START
	          StringBuilder stringBuilder = new StringBuilder();
	          stringBuilder.append("Dear "+User.getName() +",\n\n");
	          stringBuilder.append("The Admin has registered an account for you.\n\n");
	          stringBuilder.append("The Login ID for your account will be "+User.getEmail()+ " and the temporary password is "+User.getPassword()+ ".");
	          stringBuilder.append("\n\n");
	          stringBuilder.append("Best Regards,\n");
	          stringBuilder.append("The Assignment");
	          message.setText(stringBuilder.toString());
	          //	Email Body	---	END
	    	  //	Constructing the Email Contents	---	END
	   
	          // Send message  
	          Transport.send(message);  
	          System.out.println("--- An Email has been sent to the User ---");  
	          System.out.println("--- REDIRECTING BACK TO ADMIN PAGE ---");
	   
	       }catch (MessagingException mex) {
//	    	   mex.printStackTrace();	//	Prints out Exceptions
	    	   //	^Commented Out as smtp is only catered to optimum.net
	    	   //	Will get error if User registered email is not optimum.net
	    	   }  
	      catch(Exception e) {}
	    }  
	}


