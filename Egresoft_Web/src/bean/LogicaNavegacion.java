package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.AdminDao;
import dao.EgresadoDao;
import entidades.Administrador;
import entidades.Egresado;


@ManagedBean
@ViewScoped
@SessionScoped

public class LogicaNavegacion implements Serializable {

	
	AdminDao miAdminDao;
	EgresadoDao miEgresadoDao;
	
	private String contrase�a;
	private String usuario;

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String iniciarSesion() throws Exception{
		
		System.out.println("en iniciar cecsion  usuario "+usuario+" contrase�a "+contrase�a);
		
		String redireccion=null;
		
		try{
		 miEgresadoDao=new EgresadoDao();
		 miAdminDao=new AdminDao();
		 
		 Egresado egre=miEgresadoDao.consultarEgresado(usuario,contrase�a);
		 Administrador admin=miAdminDao.consultarAdmin(usuario,contrase�a);
		 
		 if (egre!=null) {
			
			 System.out.println("en la consulta egresado");
			 redireccion="indexEgresado";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("egresado", egre);
			
			 System.out.println("ya metio el usuario");

		 }
		else if (admin!=null) {
			 System.out.println("en la consulta admin");
			 redireccion="indexAdmin";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", admin);
			 
			 System.out.println("ya metio el usuario");
			
		}
		else {
			System.err.println("");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "parce la contrase�a o el usuario no son correctos, usted no es un egresado aun, o el administrador no a actualizado el sistema con sus datos... ", "contactelo"));
		}
		}catch (Exception e) {
			
		}
		return redireccion;
	}
	
	


	public void verificarSesion() {

		System.out.println("en el metodo verificar sesion");
			
	    try {
	    	
	    	FacesContext context = FacesContext.getCurrentInstance();	
			Object usuario = context.getExternalContext().getSessionMap().get("egresado");
			Object administrador= context.getExternalContext().getSessionMap().get("admin");

			
			
			if (usuario==null) {
					context.getExternalContext().redirect("login.jsf");
					System.out.println("usuario vacion egre");
	                //mensajes.fatal("no a iniciado sesion");
					
			if(administrador==null){
				context.getExternalContext().redirect("login.jsf");	
				System.out.println("usuario vacion admin");
			}
			}
			else{
				
				System.out.println("no esta vacio");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void cerrarSesion() {

		try {

			FacesContext context = FacesContext.getCurrentInstance();

			context.getExternalContext().invalidateSession();

			context.getExternalContext().redirect("login.jsf");

		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}}
