package bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FlowEvent;

import dao.EgresadoDao;
import entidades.Egresado;

@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {

	EgresadoDao miDao = new EgresadoDao();
	// EgresadoBean Egresado= new EgresadoBean();

	// int contadorNext = 0;

	public static long idEgresado;

	public static String primeraContrase�a = null;
	public String segundaContrase�a;
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

		System.out.println("pasa");

		return pasar;
	}

	public void setPasar(boolean pasar) {
		this.pasar = pasar;
	}

	public String onFlowProcess(FlowEvent event) {

		Wizard wizard = (Wizard) event.getComponent();
		String vistaActual = wizard.getStep();

		String evento = "";
		String siguiente = event.getNewStep();
		String antes = event.getOldStep();

		if (!verificado) {// si no esta verificado lo verifica ,incrementa 1 al
							// // presionar siguiente y verificar que esta en BD
			if (verificar(idEgresado)) {
				verificado = true;
				// contadorNext++;
				System.out.println("el usuario se acaba de  verificar y pasaa a la siguiente vista ");
				evento = siguiente;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Parce ud no existe en este sistema... ", "registrece"));
				evento = antes;
			}

		} else {
			if (siguiente.equals("Estudio")) {
				// contadorNext=1;
				System.out.println("en estudio");
			} else if (siguiente.equals("contrase�a")) {
				// contadorNext=2;
				System.out.println("en contrase�a");
			} else if (siguiente.equals("encuesta")) {

				try {
					if (!primeraContrase�a.equals(segundaContrase�a)) {
						System.out.println("las contrase�as no coincidend contrase�a1: " + primeraContrase�a
								+ " contrase�a2: " + segundaContrase�a);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Las contrase�as no coinciden ", "vuelva a intentarlo"));
						siguiente = "contrase�a";
						evento = siguiente;
					} else {
						System.out.println("las contrase�as si coincidend contrase�a1: " + primeraContrase�a
								+ " contrase�a2: " + segundaContrase�a);

						evento = event.getNewStep();
					}
				} catch (Exception e) {
					System.out.println("error");

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"no deje campos vacios, si desea ir a actualizar datos, le toca refrescar",
									"vuelva a intentarlo"));
					evento = antes;

				}

				System.out.println("en encuesta");
			} else if (siguiente.equals("confirma")) {
				System.out.println("en confirma");

			}
			evento = siguiente;

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