package bean;


import java.io.Serializable;
import java.util.List;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.management.Query;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean (name="egresadoBean", eager = true )
@ViewScoped
public class EgresadoBean implements Serializable {

	private MessagesView msj = new MessagesView();
	EgresadoDao daoEgresado=new EgresadoDao();
	private Egresado egresado = new Egresado();
	private List<Egresado>listaEgresados;	
	private Egresado listaAtributos;
	private long dniEgresado;

	
	public long getDniEgresado() {
		return dniEgresado;
	}

	public void setDniEgresado(long dniEgresado) {
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
	public Egresado bucarEgresado() throws Exception{
		
		try{
			
			listaAtributos=daoEgresado.buscarEgresado(dniEgresado);
		}catch (Exception e) {
		
			throw e;
		}
		
		return listaAtributos;	
	}

	public Egresado getListaAtributos() {
		return listaAtributos;
	}

	public void setListaAtributos(Egresado listaAtributos) {
		this.listaAtributos = listaAtributos;
	}

	public List<Egresado> getListaEgresados() {
		
		daoEgresado=new EgresadoDao();
		listaEgresados = daoEgresado.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {
		
		this.listaEgresados = listaEgresados;
	}

    
	public void nuevoEgresado() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.registrar(egresado);
			egresado=new Egresado();
			
			
		}catch(Exception e){
			
			
		}
	}

	public void editarEgresadoAdmin() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");
			
		}catch (Exception e) {
			
		}
		
	}
	
	public void editarEgresado(){
		
		
		if (daoEgresado.editaEgresadoFromEgresado(egresado)) {
			System.out.println("editó al egresado");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", "se editó al egresado"));
			
		}else{
			System.err.println("no lo edito");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "no se pudo editar al egresado"));
		}
		

		}
	

	public void eliminarEgresado() {

		try{
			daoEgresado=new EgresadoDao();
			daoEgresado.eliminar(egresado);
			egresado = new Egresado();
			msj.info("Egresado eliminado exitosamente ");
			
		}catch(Exception e){
			
		}
		
	}

	public EgresadoDao getEgreDao() {
		return daoEgresado;
	}

	public void setEgreDao(EgresadoDao daoEgresado) {
		this.daoEgresado = daoEgresado;
	}

	
	

}
