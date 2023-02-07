package com.oracle.cloud.sdk.samples;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oracle.bmc.email.EmailClient;
import com.oracle.bmc.email.model.CreateSenderDetails;
import com.oracle.bmc.email.model.Sender;
import com.oracle.bmc.email.requests.CreateSenderRequest;
import com.oracle.bmc.email.responses.CreateSenderResponse;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.identity.model.SmtpCredentialSummary;
import com.oracle.bmc.identity.requests.ListSmtpCredentialsRequest;
import com.oracle.bmc.identity.responses.ListSmtpCredentialsResponse;

@SpringBootApplication
public class OciMailSenderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OciMailSenderApplication.class, args);
		
//		String compartmentId = "ocid1.compartment.oc1..aaaaaaaai5kbn7vkcpxadias6cbwr2abzq5dkdye5ewpylsjxzo2ajcayb4q";
//		String senderEmailAddress = "info@ashok.com";
//		String userId = "ocid1.user.oc1..aaaaaaaa3jfondpbrtjds64hk6jfzvtmgxqr7k4wni4t4jergmxe627hf2bq";
//		
//		System.out.println("Hello");
//		
//        final EmailClient emailClient = new EmailClient(OCIConfig.getAuthenticationDetailsProvider());
//        final IdentityClient identityClient = new IdentityClient(OCIConfig.getAuthenticationDetailsProvider());
//        
//        emailClient.setRegion(Region.US_ASHBURN_1);
//        identityClient.setRegion(Region.US_ASHBURN_1);
//        
//        Sender sender = null;
//        Suppression suppression = null;
//        SmtpCredential smtpCredential = null;
//        
//        listSmtpCredentials(identityClient, userId);
//		sendMail();
        
//        System.out.println("Done!!");
	}
	
	
	private static void sendMail() {
		final String fromEmail = "noreply@notification.us-ashburn-1.oci.oraclecloud.com"; //requires valid gmail id
		final String userId = "ocid1.user.oc1..aaaaaaaa3jfondpbrtjds64hk6jfzvtmgxqr7k4wni4t4jergmxe627hf2bq@ocid1.tenancy.oc1..aaaaaaaasu7rvefmsyk5kqczfmdqi5clpddejfjk2attdqnk6sbk72wajq5q.7l.com";
		final String password = "1#rMe.A(seqHm{vF[Jkt"; // correct password for gmail id
		final String toEmail = "ashok.cm@oracle.com"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.email.us-ashburn-1.oci.oraclecloud.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userId, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		sendEmailLocal(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
	}
	
	public static void sendEmailLocal(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("noreply@notification.us-ashburn-1.oci.oraclecloud.com", "NoReply-JD"));

	      msg.setReplyTo(InternetAddress.parse("noreply@notification.us-ashburn-1.oci.oraclecloud.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
    private static void listSmtpCredentials(
            final IdentityClient identityClient, final String userId) {
        System.out.println("Listing SMTP credentials");
        System.out.println("=======================");

        final ListSmtpCredentialsRequest request =
                ListSmtpCredentialsRequest.builder().userId(userId).build();
        final ListSmtpCredentialsResponse response = identityClient.listSmtpCredentials(request);
        

        for (SmtpCredentialSummary scs : response.getItems()) {
            System.out.println(scs);
        }
        System.out.println();
    }
	
    private static Sender createEmailSender(
            final EmailClient emailClient,
            final String compartmentId,
            final String senderEmailAddress)
            throws Exception {
        System.out.println("Creating email sender " + senderEmailAddress);
        System.out.println("=======================");

        final CreateSenderDetails details =
                CreateSenderDetails.builder()
                        .compartmentId(compartmentId)
                        .emailAddress(senderEmailAddress)
//                        .freeformTags(getFreeformTagData())
                        .build();
        final CreateSenderRequest request =
                CreateSenderRequest.builder().createSenderDetails(details).build();
        final CreateSenderResponse response = emailClient.createSender(request);

        System.out.println("Created sender: " + response.getSender().toString());
        System.out.println();

        return response.getSender();
    }
}
