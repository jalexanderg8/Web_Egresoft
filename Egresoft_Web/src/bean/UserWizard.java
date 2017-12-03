package bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FlowEvent;

import dao.EgresadoDao;
import entidades.Egresado;

@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {

	
	//@ManagedProperty(value="#{EgresadoBean}")
    
	
	EgresadoDao miDao = new EgresadoDao();

	public static long idEgresado;

	public static String primeraContraseña = null;
	public String segundaContraseña=null;
	private static boolean verificado = false;

	private boolean pasar;

	public String getSegundaContraseña() {
		return segundaContraseña;
	}

	public void setSegundaContraseña(String segundaContraseña) {
		this.segundaContraseña = segundaContraseña;
	}
	
	public void save() {
		FacesMessage msg = new FacesMessage("Felicitaciones", "Actualizó sus datos");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isPasar() {

		//System.out.println("pasa");

		return pasar;
	}

	public void setPasar(boolean pasar) {
		this.pasar = pasar;
	}

	public String onFlowProcess(FlowEvent event) {
		
		System.out.println("CONTRASEÑA 1: "+primeraContraseña+" CONTRASEÑA 2: "+segundaContraseña);
		
		
		Wizard wizard = (Wizard) event.getComponent();
		String vistaActual = wizard.getStep();

		String evento = "";
		String siguiente = event.getNewStep();
		String antes = event.getOldStep();

		if (verificado==false) {
			System.out.println(verificado);
			if (verificar(idEgresado)) {	
				verificado = true;
				// contadorNext++;
				System.out.println("el usuario se acaba de  verificar y pasaa a la siguiente vista ");
				evento = siguiente;
				System.out.println("la siguiente vista queda en : "+evento.toString());
				
				if (siguiente.equals("confirma")) {
					System.out.println("en confirma");
					
					
					if (siguiente.equals("confirma")) {
						System.out.println("en confirma");
						
						evento=confirmarContraseña(event,evento);
				
					}
		
				}
				
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"No esta registrado en el sistema ... ", "registrese"));
				evento = antes;
			}

		} else {
			verificado = false;

			if (siguiente.equals("personal")) {
				System.out.println("en personal");
				evento = siguiente;

			}
			
			else if (siguiente.equals("Estudio")) {
				System.out.println("en estudio");
				evento = siguiente;

			} else if (siguiente.equals("contraseña")) {
				System.out.println("en contraseñ");
				evento = siguiente;
				System.out.println(siguiente.toString());
				
			}  else if (siguiente.equals("confirma")) {
				System.out.println("en confirma");
				
				evento=confirmarContraseña(event,evento);
		
			}

		}

		return evento;
	}
	
	public String confirmarContraseña(FlowEvent event, String evento){
		
		try {
			if (!primeraContraseña.equals(segundaContraseña)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Las contraseñas no coinciden ", "vuelva a intentarlo"));
				//siguiente = "contraseña";
				evento = event.getOldStep();
			}
			else if(primeraContraseña.equals("") || segundaContraseña.equals("")){
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"no deje campos vacios","vuelva a intentarlo"));
				evento = event.getOldStep();	
			}
			else {
				evento = event.getNewStep();
			}
		} catch (Exception e) {
			System.out.println("error al verificar contraseña"+e.getMessage());			
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"OCURRIO UN ERROR","vuelva a intentarlo"));
			evento = event.getOldStep();


		}
		
		return evento;
		
	}

	public boolean verificar(long idEgresado) {

		if (miDao.consultarDoc(idEgresado)) {
			return true;

		} else {
			return false;
		}

	}

}