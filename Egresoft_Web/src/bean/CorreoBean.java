package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mail.SendMailTLS;
import mensajes.MessagesView;
import vo.CorreoVo;



@ManagedBean
@RequestScoped
public class CorreoBean {

	private CorreoVo correo = new CorreoVo();


	private MessagesView msj = new MessagesView();

	public CorreoBean() {

	}

	public CorreoVo getCorreo() {
		return correo;
	}

	public void setCorreo(CorreoVo correo) {
		this.correo = correo;
	}

	public void enviarCorreo() {

		SendMailTLS sendMailTLS = new SendMailTLS();
		//SendMailSSL sendMailSSL = new SendMailSSL();
		
		if (sendMailTLS.enviar(correo)) {
			msj.info("Correo enviado");
		} else {
			msj.error("No se pudo enviar el correo");
		}
	}
}
		
	
	

	
