package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.EgresadoDao;
import dao.VistaEgresadoDao;
import entidades.Egresado;
import entidades.Vistaegresados;
import entidades.VistaegresadosId;
import mensajes.MessagesView;

@ManagedBean(name = "vistaEgresadoBean", eager = true)
@ViewScoped
public class VistaEgresadoBean {

	private long idEgresado;

	private MessagesView msj = new MessagesView();
	private Egresado egresado;

	private VistaegresadosId vistaEgresado;
	VistaEgresadoDao v = new VistaEgresadoDao();
	EgresadoDao e = new EgresadoDao();

	public VistaegresadosId bucarEgresado() throws Exception {

		try {
			vistaEgresado = v.consultarDoc(idEgresado);

			if (vistaEgresado != null) {

				msj.info("Consula OK ");

				return vistaEgresado;
			} else {

				msj.error("Documento no registrado en BD");

				return null;
			}
		} catch (Exception e) {

			throw e;
		}
	}

	public Egresado BuscarEgre() {

		try {

			egresado = e.buscarEgresado(idEgresado);

			if (egresado != null) {

				msj.info("Egresado encontrado con exito");

			} else {

				msj.error("No se encontro al egresado");

			}

		} catch (Exception e) {

		}
		return egresado;

	}

	public void editar() {

		try {
			e = new EgresadoDao();
			e.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");

		} catch (Exception e) {

			msj.error("NO actualizo ");
		}
	}

	public void eliminar() {
		

		try {
			e = new EgresadoDao();
			e.eliminarEgresado(idEgresado);
			egresado = new Egresado();
			msj.info("Informacion eliminada exitosamente");

		} catch (Exception e) {

			msj.error("NO elimino ");
		}
	}

	public long getIdEgresado() {
		return idEgresado;
	}

	public void setIdEgresado(long idEgresado) {
		this.idEgresado = idEgresado;
	}

	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}

	public VistaegresadosId getVistaEgresado() {
		return vistaEgresado;
	}

	public void setVistaEgresado(VistaegresadosId vistaEgresado) {
		this.vistaEgresado = vistaEgresado;
	}

	public Egresado getEgresado() {
		return egresado;
	}

	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}

}
