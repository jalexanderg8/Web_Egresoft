package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.component.api.UIData;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;
import vo.CorreoVo;

@ManagedBean(name = "envioCorreos", eager = true)
@SessionScoped
public class EnvioCorreos implements Serializable {
	private static final long serialVersionUID = 1L;
	public String correos;
	private MessagesView msj = new MessagesView();
	private Egresado egresadoMail;
	EgresadoDao daoEgresado = new EgresadoDao();
	//private UIData invoicesDataTable;

	private boolean correoSeleccionado;
	private Map<Long, String> mapaCorreos= new HashMap<Long, String>();
	@ManagedProperty("#{correoBean}")
	private CorreoBean correoBean;

	List<Egresado> listaEgresados;
	private boolean selectAllInvoices;
	
	public void EnvioCorreosBean(){
		System.out.println("entro a inicializar el bean EnvioCorreosBean");
		
	}

	public String getCorreos() {
		return correos;
	}

	public void setCorreos(String correos) {
		this.correos = correos;
	}

//	public Egresado bucarEmail(Egresado egre) throws Exception {
//
//		try {
//
//			egresadoMail = daoEgresado.buscarMail(egre.getEmailPrincipal());
//			System.out.println("llego al bean " + egre.getEmailPrincipal());
//
//			if (egresadoMail != null) {
//
//				System.out.println("instacio la clase");
//
//				final String username = "jhonjairohincapie21@gmail.com";
//				final String password = "junior399";
//
//				Properties props = new Properties();
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.starttls.enable", "true");
//				props.put("mail.smtp.host", "smtp.gmail.com");
//				props.put("mail.smtp.port", "587");
//				props.put("mail.smtp.ssl.trust", "*");
//
//				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username, password);
//					}
//				});
//
//				try {
//
//					Message message = new MimeMessage(session);
//					message.setFrom(new InternetAddress(username));
//					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));
//					message.setSubject("Envio Correos Masivos");
//					// message.setText("Texto de prueba," + "\n\n cuerpo del
//					// correo");
//
//					Transport.send(message);
//
//					System.out.println("enviado");
//					msj.info("Correo enviado exitosamente");
//
//				} catch (MessagingException e) {
//					throw new RuntimeException(e);
//				}
//
//			} else {
//				System.out.println("No instacio la clase");
//				msj.error("Correo no enviado exitosamente");
//
//			}
//
//		} catch (Exception e) {
//
//			throw e;
//		}
//
//		return egresadoMail;
//	}
//
	public List<Egresado> getListaEgresados() {

		daoEgresado = new EgresadoDao();
		listaEgresados = daoEgresado.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {

		this.listaEgresados = listaEgresados;
	}

//	public void setSelectAllInvoices(boolean selectAllInvoices) {
//		this.selectAllInvoices = selectAllInvoices;
//	}
//
//	public boolean isSelectAllInvoices() {
//		return selectAllInvoices;
//	}
//
//	private Map<Egresado, Boolean> invoicesSelected = new HashMap<Egresado, Boolean>() {
//
//		private static final long serialVersionUID = -3360838896781243282L;
//
//		@Override
//		public Boolean get(Object object) {
//			if (isSelectAllInvoices()) {
//				invoicesSelected.put((Egresado) object, Boolean.TRUE);
//				return Boolean.TRUE;
//			}
//			if (!containsKey(object)) {
//				return Boolean.FALSE;
//			}
//			return super.get(object);
//		};
//
//	};
//
//	public Map<Egresado, Boolean> getInvoicesSelected() {
//		return invoicesSelected;
//	}
//
//	public List<Egresado> getAllInvoicesSelected() {
//		if (isSelectAllInvoices()) {
//			return getListaEgresados();
//		}
//		final List<Egresado> result = new ArrayList<Egresado>();
//		final Iterator<?> iterator = invoicesSelected.keySet().iterator();
//		while (iterator.hasNext()) {
//			Egresado key = (Egresado) iterator.next();
//			if (invoicesSelected.get(key)) {
//				result.add(key);
//			}
//		}
//		System.out.println("allInvoicesSelected " + result);
//
//		return result;
//	}
//
//	public void clearEntitiesSelected() {
//		selectAllInvoices = false;
//		invoicesSelected.clear();
//	}
//
//	public void selectAllInvoicesListener(ValueChangeEvent event) {
//		selectAllInvoices = ((Boolean) event.getNewValue()).booleanValue();
//		if (!selectAllInvoices) {
//			clearEntitiesSelected();
//		}
//	}
//
//	public void markAsInvoicedListener(ActionEvent event) {
//		final List<Egresado> invoicesSelected = getAllInvoicesSelected();
//
//		System.out.println("markAsInvoicedListener " + invoicesSelected);
//
//		// FinancialService.getInstance().markAsInvoiced(invoicesSelected);
//	}
//
//	public void selectInvoiceListener(ValueChangeEvent event) {
//		selectAllInvoices = false;
//		invoicesSelected.put((Egresado) invoicesDataTable.getRowData(), (Boolean) event.getNewValue());
//	}
//
//	public void setInvoicesDataTable(UIData invoicesDataTable) {
//		this.invoicesDataTable = invoicesDataTable;
//	}
//
//	public UIData getInvoicesDataTable() {
//		return invoicesDataTable;
//	}
//
	public boolean isCorreoSeleccionado() {
		return correoSeleccionado;
	}

	public void setCorreoSeleccionado(boolean correoSeleccionado) {
		this.correoSeleccionado = correoSeleccionado;

	}

	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}

	public void addCorreo(Egresado egre) {

		try {
			//if (correoSeleccionado) {
				//mapaCorreos.put(egresadoMail.getEmailPrincipal(), correoSeleccionado);
				String summary = correoSeleccionado ? "Checked" : "Unchecked";
				
				if(summary.equals("Checked")){
					System.out.println("entro a colocar un egresado en el mapa "+ egre.toString());
					mapaCorreos.put(egre.getIdEgresado(), egre.getEmailPrincipal());
					System.out.println("datos del mapa:");
					imprimirMapa();
				}else{
					if(mapaCorreos.containsKey(egre.getIdEgresado())){
						System.out.println("entro a remover un egresado en el mapa "+ egre.toString());
						mapaCorreos.remove(egre.getIdEgresado());
						System.out.println("datos del mapa:");
						imprimirMapa();
					}
				}
				msj.info("usuario seleccionado " + egre.getNombres() + " " + summary);
			//}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enviar() {

		System.out.println("entro a enviar el correo en EnvioCorreoBean");
		try {
			imprimirMapa();
			if (!mapaCorreos.isEmpty()) {
				// Imprimimos el Map con un Iterador
				CorreoVo correoVo = correoBean.getCorreo();

				String para = "";
				Iterator<Long> it = mapaCorreos.keySet().iterator();
				while (it.hasNext()) {
					Long key = (Long) it.next();
					System.out.println("Clave: " + key + " -> Valor: " + mapaCorreos.get(key));
					para = para + mapaCorreos.get(key) + ",";
				}
				System.out.println(mapaCorreos);

				correoVo.setPara(para);
				correoBean.setCorreo(correoVo);
				correoBean.enviarCorreoAdjunto();

			} else {
				msj.error("Debe seleccionar por lo menos un destinatario para el correo");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public CorreoBean getCorreoBean() {
		return correoBean;
	}

	public void setCorreoBean(CorreoBean correoBean) {
		this.correoBean = correoBean;
	}
	
	public void imprimirMapa(){
		Iterator<Long> it = mapaCorreos.keySet().iterator();
		while (it.hasNext()) {
			Long key = (Long) it.next();
			System.out.println("Diana imprimiendo el mapa:");
			System.out.println("Clave: " + key + " -> Valor: " + mapaCorreos.get(key));
		
		}
	}

}
