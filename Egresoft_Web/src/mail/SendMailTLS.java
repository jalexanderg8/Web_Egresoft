package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import vo.CorreoVo;

public class SendMailTLS {

	// public static void main(String[] args) {

	public boolean enviar(CorreoVo correo) {
		boolean seEnvio = false;
		final String username = "jhonjairohincapie21@gmail.com";
		final String password = "junior399";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo.getPara()));
			message.setSubject(correo.getAsunto());
			//message.setText("Texto de prueba," + "\n\n cuerpo del correo");
			message.setText(correo.getCuerpo());

			Transport.send(message);

			System.out.println("Done");
			seEnvio = true;
			
			return seEnvio;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
