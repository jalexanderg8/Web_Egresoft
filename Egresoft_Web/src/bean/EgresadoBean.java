package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean(name = "egresadoBean", eager = true)
@ViewScoped
public class EgresadoBean implements Serializable {

	private MessagesView msj = new MessagesView();
	EgresadoDao daoEgresado = new EgresadoDao();

	private Egresado egresado = new Egresado();
	private List<Egresado> listaEgresados;
	private Egresado egresadoAtributos;
	private Egresado egresadoMail;
	private long dniEgresado;
	private String direccionEmail;

	public String getDireccionEmail() {
		return direccionEmail;
	}

	public void setDireccionEmail(String direccionEmail) {
		this.direccionEmail = direccionEmail;
	}

	public long getDniEgresado() {
		return dniEgresado;
	}

	public void setDniEgresado(long dniEgresado) {
		this.dniEgresado = dniEgresado;
	}

	public Egresado getEgresado() {
		return egresado;
	}

	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}

	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}

	public Egresado bucarEmail() throws Exception {

		try {

			egresadoMail = daoEgresado.buscarMail(direccionEmail);
			System.out.println("llego al bean");

			if (egresadoMail != null) {

				System.out.println("instacio la clase");

				final String username = "pruebaegresado@gmail.com";
				final String password = "Egresoft2017";
				String contra;

				contra = egresadoMail.getContrasena();

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.ssl.trust", "*");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccionEmail));
					message.setSubject("Recuperar Contraseña");
					// message.setText("Texto de prueba," + "\n\n cuerpo del
					// correo");
					message.setText("Su contraseña es " + contra);

					Transport.send(message);

					System.out.println("enviado");
					msj.info("Correo enviado exitosamente");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			} else {
				System.out.println("No instacio la clase");
				msj.error("Correo no enviado exitosamente");

			}

		} catch (Exception e) {

			throw e;
		}

		return egresadoMail;
	}

	public Egresado bucarEgresado() throws Exception {

		try {

			egresadoAtributos = daoEgresado.buscarEgresado(dniEgresado);

			if (egresadoAtributos != null) {

				msj.info("Egresado en Base de datos");
			} else {

				msj.error("Egresado no existe en Base de Datos");
			}
		} catch (Exception e) {

			throw e;
		}

		return egresadoAtributos;
	}

	public Egresado getEgresadoAtributos() {
		return egresadoAtributos;
	}

	public void setEgresadoAtributos(Egresado egresadoAtributos) {
		this.egresadoAtributos = egresadoAtributos;
	}

	public Egresado getEgresadoMail() {
		return egresadoMail;
	}

	public void setEgresadoMail(Egresado egresadoMail) {
		this.egresadoMail = egresadoMail;
	}

	public List<Egresado> getListaEgresados() {

		daoEgresado = new EgresadoDao();
		listaEgresados = daoEgresado.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {

		this.listaEgresados = listaEgresados;
	}

	public void nuevoEgresado() {

		try {
			daoEgresado = new EgresadoDao();
			daoEgresado.registrar(egresado);
			egresado = new Egresado();
			msj.info("Egresado registrado exitosamente");

		} catch (Exception e) {

		}
	}

	public void editarEgresadoAdmin() {

		try {
			daoEgresado = new EgresadoDao();
			daoEgresado.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");

		} catch (Exception e) {

		}

	}

	public void editarEgresado() {

		if (daoEgresado.editaEgresadoFromEgresado(egresado)) {
			System.out.println("editó al egresado");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "Se registro al  egresado"));
			enviarEmail();

		} else {
			System.err.println("no lo edito");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "no se pudo registrar al egresado"));
		}

	}

	private void enviarEmail() {

		Long id = egresado.getIdEgresado();
		System.out.println("hola" + id);

		egresadoAtributos = daoEgresado.buscarEgresado(id);

		if (egresadoAtributos != null) {

			System.out.println(egresadoAtributos.getEmailPrincipal());

			final String username = "pruebaegresado@gmail.com";
			final String password = "Egresoft2017";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.ssl.trust", "*");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			String mjs = "Felicitaciones por su registro:\n";
			mjs += "sus datos en la plataforma son :\n";
			mjs+="\nTipo de documento : "+egresadoAtributos.getTipoDocumento()+"\n";
			mjs+="Numero de documento : "+egresadoAtributos.getIdEgresado()+"\n";
			mjs += "Nombres : " + egresadoAtributos.getNombres() + "\n";
			mjs+="Apellidos : "+egresadoAtributos.getApellidos()+"\n";
			mjs+="Nombre de Usuario para ingresar en la plataforma : "+egresadoAtributos.getEmailPrincipal()+"\n";
			mjs+="Su contraseña para ingresar en la plataforma es : "+egresadoAtributos.getContrasena()+"\n";

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(egresadoAtributos.getEmailPrincipal()));
				message.setSubject("Bienvenido a Egresoft ");
				message.setText(mjs);

			    Transport.send(message);

				System.out.println("enviado");
				msj.info("Correo enviado exitosamente");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		}
	}

	
	public void eliminarEgresado() {

		try {
			daoEgresado = new EgresadoDao();
			daoEgresado.eliminar(egresado);
			egresado = new Egresado();
			msj.info("Egresado eliminado exitosamente ");

		} catch (Exception e) {

		}

	}

	public EgresadoDao getEgreDao() {
		return daoEgresado;
	}

	public void setEgreDao(EgresadoDao daoEgresado) {
		this.daoEgresado = daoEgresado;
	}
	
	
	public void validarCelular(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
		if (((String) arg2).length() != 0) {
		
			String cadenaCelular = (String) arg2;

			Pattern pat = Pattern.compile("^[3][0-9]{9}$");
			Matcher mat = pat.matcher(cadenaCelular);

			if (!mat.matches()) {
				throw new ValidatorException(
						new FacesMessage("Ingrese un numero valido"));
			}
		}
		
	}

}
