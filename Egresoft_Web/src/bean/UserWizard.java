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

	public static String primeraContrase�a = null;
	public String segundaContrase�a=null;
	private static boolean verificado = false;

	private boolean pasar;

	public String getSegundaContrase�a() {
		return segundaContrase�a;
	}

	public void setSegundaContrase�a(String segundaContrase�a) {
		this.segundaContrase�a = segundaContrase�a;
	}
	
	public void save() {
		FacesMessage msg = new FacesMessage("Felicitaciones", "Actualiz� sus datos");
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
		
		System.out.println("CONTRASE�A 1: "+primeraContrase�a+" CONTRASE�A 2: "+segundaContrase�a);
		
		
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
						
						evento=confirmarContrase�a(event,evento);
				
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

			} else if (siguiente.equals("contrase�a")) {
				System.out.println("en contrase�");
				evento = siguiente;
				System.out.println(siguiente.toString());
				
			}  else if (siguiente.equals("confirma")) {
				System.out.println("en confirma");
				
				evento=confirmarContrase�a(event,evento);
		
			}

		}

		return evento;
	}
	
	public String confirmarContrase�a(FlowEvent event, String evento){
		
		try {
			if (!primeraContrase�a.equals(segundaContrase�a)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Las contrase�as no coinciden ", "vuelva a intentarlo"));
				//siguiente = "contrase�a";
				evento = event.getOldStep();
			}
			else if(primeraContrase�a.equals("") || segundaContrase�a.equals("")){
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"no deje campos vacios","vuelva a intentarlo"));
				evento = event.getOldStep();	
			}
			else {
				evento = event.getNewStep();
			}
		} catch (Exception e) {
			System.out.println("error al verificar contrase�a"+e.getMessage());			
			
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