package bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.AdminDao;
import entidades.Administrador;
import entidades.Egresado;
import mensajes.MessagesView;

@ManagedBean(name = "adminBean", eager = true)
@SessionScoped
	public class AdminBean implements Serializable{

	private Administrador admin=new Administrador();
	private List<Administrador>listaAdministradores;
	private AdminDao aDao;
	private MessagesView msj = new MessagesView();
	
	
	
	public Administrador getAdmin() {
		return admin;
	}
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
public List<Administrador> getListaAdministradores() {
		
		aDao=new AdminDao();
		listaAdministradores = aDao.listaAdministradores();

		return listaAdministradores;
	}

	public void setListaEgresados(List<Administrador> listaAdministradores) {
		
		this.listaAdministradores = listaAdministradores;
	}

    
	public void nuevoEgresado() {

		try{
			aDao=new AdminDao();
			aDao.registrar(admin);
			admin=new Administrador();
			
			
		}catch(Exception e){
			
			
		}
		

	}

	public void editarEgresado() {

		try{
			aDao=new AdminDao();
			aDao.editar(admin);
			admin = new Administrador();
			
			
		}catch (Exception e) {
			
		}
		
	}

	public void eliminarEgresado() {

		try{
			aDao=new AdminDao();
			aDao.eliminar(admin);
			admin = new Administrador();
			
			
		}catch(Exception e){
			
		}
		
	}

	
	
	/*public String iniciarSesion() throws Exception {
		String redireccion = null;
		try {
			aDao = new AdminDao();
			Administrador encontrado = aDao.buscarUsuario(admin.getNombreUsuarioAdmin(), admin.getContrasenaAdmin());

			if (encontrado != null) {
				
				System.out.println("Admin " + encontrado.toString());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", encontrado);
				redireccion = "indexAdmin";
				
				msj.info("Bienvenido Administrador");
			} else {
				msj.error("Credenciales no validas");
			}

		} catch (Exception e) {
			throw e;
		}
		return redireccion;
	}
	
	public void verificarSesion(){
		
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			Egresado egre = (Egresado) context.getExternalContext().getSessionMap().get("administrador");
			
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
	public AdminDao getaDao() {
		return aDao;
	}
	public void setaDao(AdminDao aDao) {
		this.aDao = aDao;
	}
		
		
	}
