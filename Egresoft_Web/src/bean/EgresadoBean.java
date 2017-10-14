package bean;


import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean(name = "egresadoBean", eager = true)
@ViewScoped
@RequestScoped
public class EgresadoBean implements Serializable {

	private Egresado egresado;
	private EgresadoDao egresadoDao;
	private MessagesView msj = new MessagesView();
	private List<Egresado>listaEgresados;
	private int dniEgresado;

	

	public int getDniEgresado() {
		return dniEgresado;
	}

	public void setDniEgresado(int dniEgresado) {
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

	public List<Egresado> getListaEgresados() {
		
		EgresadoDao aDao = new EgresadoDao();
		listaEgresados = aDao.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {
		
		this.listaEgresados = listaEgresados;
	}

    
	public void nuevoEgresado() {

		EgresadoDao aDao = new EgresadoDao();
		aDao.registrar(egresado);
		egresado=new Egresado();

	}

	public void editarEgresado() {

		EgresadoDao aDao = new EgresadoDao();
		aDao.editar(egresado);
		egresado = new Egresado();
	}

	public void eliminarEgresado() {

		EgresadoDao aDao = new EgresadoDao();
		aDao.eliminar(egresado);
		egresado = new Egresado();
	}

	public EgresadoDao getEgresadoDao() {
		return egresadoDao;
	}

	public void setEgresadoDao(EgresadoDao egresadoDao) {
		this.egresadoDao = egresadoDao;
	}

}
