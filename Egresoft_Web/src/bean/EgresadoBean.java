package bean;


import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.Query;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean (name="egresadoBean", eager = true )
@ViewScoped
public class EgresadoBean implements Serializable {

	private MessagesView msj = new MessagesView();
	EgresadoDao daoEgresado=new EgresadoDao();
	private Egresado egresado = new Egresado();
	private List<Egresado>listaEgresados;	
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
	
	public Egresado bucarEmail() throws Exception{
		
		try{
			
			egresadoMail=daoEgresado.buscarMail(direccionEmail);
			System.out.println("llego al bean");
			
			if(egresadoMail!=null){
				
				System.out.println("instacio la clase");
				
				final String username = "pruebaegresado@gmail.com";
				final String password = "Egresoft2017";
				String contra;
				
				contra=egresadoMail.getContrasena();

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
					//message.setText("Texto de prueba," + "\n\n cuerpo del correo");
					message.setText("Su contraseña es "+contra);

					Transport.send(message);

					System.out.println("enviado");
					msj.info("Correo enviado exitosamente");
					
					
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
				
				
			}else {
				System.out.println("NO instacio la clase");
				msj.info("Correo no enviado exitosamente");
				
			}
			
		}catch (Exception e) {
		
			throw e;
		}
		
		return egresadoMail;	
	}
	
	public Egresado bucarEgresado() throws Exception{
		
		try{
			
			egresadoAtributos=daoEgresado.buscarEgresado(dniEgresado);
		}catch (Exception e) {
		
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
		
		daoEgresado=new EgresadoDao();
		listaEgresados = daoEgresado.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {
		
		this.listaEgresados = listaEgresados;
	}

    
	public void nuevoEgresado() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.registrar(egresado);
			egresado=new Egresado();
			msj.info("Egresado registrado exitosamente");
			
		}catch(Exception e){
			
			
		}
	}

	public void editarEgresadoAdmin() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");
			
		}catch (Exception e) {
			
		}
		
	}
	
	public void editarEgresado(){
		
		
		if (daoEgresado.editaEgresadoFromEgresado(egresado)) {
			System.out.println("editó al egresado");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "se editó al egresado"));
			
		}else{
			System.err.println("no lo edito");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "no se pudo editar al egresado"));
		}
		

		}
	

	public void eliminarEgresado() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.eliminar(egresado);
			egresado = new Egresado();
			msj.info("Egresado eliminado exitosamente ");
			
		}catch(Exception e){
			
		}
		
	}

	public EgresadoDao getEgreDao() {
		return daoEgresado;
	}

	public void setEgreDao(EgresadoDao daoEgresado) {
		this.daoEgresado = daoEgresado;
	}

	
	

}
