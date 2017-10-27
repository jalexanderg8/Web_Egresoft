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

	public static String primeraContraseña = null;
	public String segundaContraseña;
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
			} else if (siguiente.equals("contraseña")) {
				// contadorNext=2;
				System.out.println("en contraseña");
			} else if (siguiente.equals("encuesta")) {

				try {
					if (!primeraContraseña.equals(segundaContraseña)) {
						System.out.println("las contraseñas no coincidend contraseña1: " + primeraContraseña
								+ " contraseña2: " + segundaContraseña);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Las contraseñas no coinciden ", "vuelva a intentarlo"));
						siguiente = "contraseña";
						evento = siguiente;
					} else {
						System.out.println("las contraseñas si coincidend contraseña1: " + primeraContraseña
								+ " contraseña2: " + segundaContraseña);

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