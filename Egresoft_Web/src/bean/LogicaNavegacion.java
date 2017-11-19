package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.AdminDao;
import dao.EgresadoDao;
import entidades.Administrador;
import entidades.Egresado;


@ManagedBean
@ViewScoped
@RequestScoped
public class LogicaNavegacion implements Serializable {

	
	AdminDao miAdminDao;
	EgresadoDao miEgresadoDao;
	Egresado egre=null;
	Administrador admin=null;
	
	
	private String contraseña;
	private String usuario;

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String iniciarSesion() throws Exception{
		
		System.out.println("en iniciar sesion  usuario "+usuario+" contraseña "+contraseña);
		
		String redireccion=null;
		
		try{
		 miEgresadoDao=new EgresadoDao();
		 miAdminDao=new AdminDao();
		 
		  egre=miEgresadoDao.consultarEgresado(usuario,contraseña);
		  admin=miAdminDao.consultarAdmin(usuario,contraseña);
		 
		 if (egre!=null) {
		
			 System.out.println("en la consulta egresado");
			 redireccion="indexEgresado";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("egresado", egre);
			
			 System.out.println("ya metio el usuario egresado");

		 }
		else if (admin!=null) {
			 System.out.println("en la consulta admin");
			 redireccion="indexAdmin";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", admin);
			 
			 System.out.println("ya metio el usuarioa dministrador");
			
		}
		 
		else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario no registrado revise sus credenciales ... ", "Contacte al Administrador del sistema "));
		}
		}catch (Exception e) {
			
		}
		System.err.println("usuario admin: "+admin+" usuario egresado: "+egre);

		return redireccion;
	}
	
	
	public void verificarSesionAdmin() {

		System.out.println("en el metodo verificar sesion admin");
			
	    try {
	    	
	    	FacesContext context = FacesContext.getCurrentInstance();	
			 Administrador administrador= (Administrador) context.getExternalContext().getSessionMap().get("admin");	
			
				if (administrador==null) {
					context.getExternalContext().redirect("login.jsf");
					System.out.println("usuario vacio validacion");
					System.err.println("usuario admin: "+administrador);

				}
				else{
					System.err.println("usuario admin: "+administrador);

					System.out.println("no esta vacio");
				}
		
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void verificarSesionEgresado() {

		System.out.println("en el metodo verificar sesion egresado");
			
	    try {
	    	
	    	FacesContext context = FacesContext.getCurrentInstance();	
			 Egresado egresado = (Egresado) context.getExternalContext().getSessionMap().get("egresado");
			
				if (egresado==null) {
					context.getExternalContext().redirect("login.jsf");
					System.out.println("usuario vacio validacion");
					System.err.println(" usuario egresado: "+egresado);

				}
				else{
					System.err.println(" usuario egresado: "+egresado);

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
