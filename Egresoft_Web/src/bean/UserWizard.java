package bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

import dao.EgresadoDao;
import entidades.Egresado;

@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {

	EgresadoDao miDao = new EgresadoDao();
	// EgresadoBean Egresado= new EgresadoBean();

	int contadorNext = 0;

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

		String evento = null;

		if (!verificado) {// si no esta verificado lo verifica ,incrementa 1 al
							// presionar siguiente y verificar que esta en BD
			if (verificar(idEgresado)) {
				verificado = true;
				contadorNext++;
				System.out.println("el usuario se acaba de  verificar y pasaa a la siguiente vista, el contador es: "
						+ contadorNext);
				evento = event.getNewStep();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Egresado no registrado... ", "registrece"));
				evento = event.getOldStep();
			}

		} else if (contadorNext == 2) {// se hace la validacion para poder
										// cambiar a la otra vista del wizard

			System.out.println("esta en confirmar contraseña y no a confirmado");

			try {
				if (!primeraContraseña.equals(segundaContraseña)) {
					System.out.println("las contraseñas no coinciden");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Las contraseñas no coinciden ", "vuelva a intentarlo"));
					//contadorNext--;
					evento = event.getOldStep();

				} else {
					evento = event.getNewStep();
				}
			} catch (Exception e) {
				System.out.println("error");
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"no deje campos vacios, si desea ir a actualizar datos, le toca refrescar",
								"vuelva a intentarlo"));
				evento = event.getOldStep();

				

			}

		} else if (verificado) {// esta verificado
			contadorNext++;
			System.out.println(
					"el usuario esta verificado y pasaa a la siguiente vista, el contador va en : " + contadorNext);
			evento = event.getNewStep();
		} else {
			contadorNext--;
			System.out.println("esta en el default, contador en: " + contadorNext);
			evento = event.getOldStep();
		}
		if (!evento.equals(event.getNewStep())) {
			contadorNext--;
			System.out.println("esta en diferente de el siguientecontador en: " + contadorNext);
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