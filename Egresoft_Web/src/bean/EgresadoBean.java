package bean;


import java.io.Serializable;
import java.util.List;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean
@ViewScoped
public class EgresadoBean implements Serializable {

	private MessagesView msj = new MessagesView();
	EgresadoDao DaoEgresado=new EgresadoDao();
	private Egresado egresado = new Egresado();
	private List<Egresado>listaEgresados;	

	
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
		
		DaoEgresado=new EgresadoDao();
		listaEgresados = DaoEgresado.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {
		
		this.listaEgresados = listaEgresados;
	}

    
	public void nuevoEgresado() {

		try{
			DaoEgresado=new EgresadoDao();
			DaoEgresado.registrar(egresado);
			egresado=new Egresado();
			
			
		}catch(Exception e){
			
			
		}
	}
/*
	public void editarEgresado() {

		try{
			aDao=new EgresadoDao();
			aDao.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");
			
		}catch (Exception e) {
			
		}
		
	}*/
	
	public void editarEgresado(){
		
		
		if (DaoEgresado.editaEgresadoFromEgresado(egresado)) {
			System.out.println("editó al egresado");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "se editó al egresado"));
			
		}else{
			System.err.println("no lo edito");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "no se pudo editar al egresado"));
		}
		

		}
	

	public void eliminarEgresado() {

		try{
			DaoEgresado=new EgresadoDao();
			DaoEgresado.eliminar(egresado);
			egresado = new Egresado();
			msj.info("Egresado eliminado exitosamente ");
			
		}catch(Exception e){
			
		}
		
	}

	public EgresadoDao getEgreDao() {
		return DaoEgresado;
	}

	public void setEgreDao(EgresadoDao egreDao) {
		this.DaoEgresado = egreDao;
	}
	
	

}
