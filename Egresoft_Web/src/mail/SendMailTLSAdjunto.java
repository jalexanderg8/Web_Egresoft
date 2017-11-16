package mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import vo.CorreoVo;

public class SendMailTLSAdjunto {

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

			// Primero construimos la parte de texto
			BodyPart texto = new MimeBodyPart();
			texto.setText(correo.getCuerpo());

			// Luego construimos la parte del adjunto con la imagen. Suponemos
			// que la tenemos en un fichero
			BodyPart[] adjuntos = adjuntarArchivos();

			// Ahora juntamos ambas en una sola parte que luego debemos añadir
			// al mensaje
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);

			for (BodyPart aux : adjuntos) {
				multiParte.addBodyPart(aux);
			}

			// Finalmente construimos el mensaje, le ponemos este MimeMultiPart
			// como contenido y rellenamos el resto de campos from, to y
			// subject.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			// destinatarios
			Address[] destinos = cargarDestinatarios(correo.getPara());

			message.addRecipients(Message.RecipientType.TO, destinos);// agregamos
																		// los
																		// destinatarios

			message.setSubject(correo.getAsunto());

			// Se agrega el texto y los archivos adjuntos.
			message.setContent(multiParte);

			Transport.send(message);

			System.out.println("Done");
			seEnvio = true;

			return seEnvio;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public BodyPart[] adjuntarArchivos() {

		String ruta = "D:\\Entorno JEE\\wildfly-10.1.0.Final\\standalone\\deployments\\Egresoft_Web.war\\resources\\cargados";

		File directorio = new File(ruta);

		int total = 0;

		String[] arrArchivos = directorio.list();
		total += arrArchivos.length;
		System.out.println("total de archivos es: " + total);
		File tmpFile;

		BodyPart[] adjuntos = new BodyPart[total];
		for (int i = 0; i < arrArchivos.length; i++) {
			tmpFile = new File(directorio.getPath() + "/" + arrArchivos[i]);
			System.out.println("nombre del archivo: " + tmpFile.getName());

			adjuntos[i] = new MimeBodyPart();
			try {
				adjuntos[i].setDataHandler(new DataHandler(new FileDataSource(tmpFile.getPath())));

				// Opcional. De esta forma transmitimos al receptor el nombre
				// original del
				// fichero de imagen.
				adjuntos[i].setFileName(tmpFile.getName());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return adjuntos;

	}

	public Address[] cargarDestinatarios(String cadDestinatarios) {

		String[] arregloDest = cadDestinatarios.split(",");

		Address[] destinos = new Address[arregloDest.length];// Aqui usamos el
																// arreglo de
																// destinatarios
		for (int i = 0; i < destinos.length; i++) {
			try {
				destinos[i] = new InternetAddress(arregloDest[i]);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return destinos;

	}

	public void borrarAdjuntos() {

		String ruta = "D:\\Entorno JEE\\wildfly-10.1.0.Final\\standalone\\deployments\\Egresoft_Web.war\\resources\\cargados";

		File directorio = new File(ruta);

		int total = 0;

		String[] arrArchivos = directorio.list();
		total += arrArchivos.length;
		System.out.println("total de archivos es: " + total);
		File tmpFile;

		for (int i = 0; i < arrArchivos.length; i++) {
			tmpFile = new File(directorio.getPath() + "/" + arrArchivos[i]);
			System.out.println("nombre del archivo: " + tmpFile.getName());

			// borramos el archivo
			if (tmpFile.delete())
				System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
				System.out.println("El fichero no puede ser borrado");

		}
	}

}
