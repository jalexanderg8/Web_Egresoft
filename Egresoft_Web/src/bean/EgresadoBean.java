package bean;


import java.io.Serializable;
import java.util.List;


import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.EgresadoDao;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean(name = "egresadoBean", eager = true)
@SessionScoped
public class EgresadoBean implements Serializable {

	private Egresado egresado= new Egresado();
	private MessagesView msj = new MessagesView();
	private List<Egresado>listaEgresados;
	private EgresadoDao aDao;
	

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
		
		aDao=new EgresadoDao();
		listaEgresados = aDao.listaEgresados();

		return listaEgresados;
	}

	public void setListaEgresados(List<Egresado> listaEgresados) {
		
		this.listaEgresados = listaEgresados;
	}

    
	public void nuevoEgresado() {

		try{
			aDao=new EgresadoDao();
			aDao.registrar(egresado);
			egresado=new Egresado();
			
			
		}catch(Exception e){
			
			
		}
		

	}

	public void editarEgresado() {

		try{
			aDao=new EgresadoDao();
			aDao.editarEgresadoDesdeAdmin(egresado);
			egresado = new Egresado();
			msj.info("Informacion actualizada exitosamente");
			
		}catch (Exception e) {
			
		}
		
	}

	public void eliminarEgresado() {

		try{
			aDao=new EgresadoDao();
			aDao.eliminar(egresado);
			egresado = new Egresado();
			msj.info("Egresado eliminado exitosamente ");
			
		}catch(Exception e){
			
		}
		
	}

	
	
/*	public String iniciarSesion() throws Exception {
		String redireccion = null;
		try {
			aDao = new EgresadoDao();
			Egresado encontrado = aDao.buscarUsuario(egresado.getEmailPrincipal(), egresado.getContrasena());

			if (encontrado != null) {
				
				System.out.println("Egresado " + encontrado.toString());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("egresado", encontrado);
				redireccion = "indexEgresado";
				
				msj.info("Bienvenido Egresado");
			} else {
				msj.error("Credenciales no validas");
			}

		} catch (Exception e) {
			throw e;
		}
		return redireccion;
	}
	*/
	/*public void verificarSesion(){
		
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			Egresado egre = (Egresado) context.getExternalContext().getSessionMap().get("egresado");
			
			if (egre == null) {
				context.getExternalContext().redirect("permisos.jsf");
			}
		}catch (Exception e) {
			
		}
	}
	
	public void cerrarSesion(){
		
		try{
			
			FacesContext context = FacesContext.getCurrentInstance();

			context.getExternalContext().invalidateSession();

			context.getExternalContext().redirect("login.jsf");
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
*/
	public EgresadoDao getEgreDao() {
		return aDao;
	}

	public void setEgreDao(EgresadoDao egreDao) {
		this.aDao = egreDao;
	}
	
	

}
