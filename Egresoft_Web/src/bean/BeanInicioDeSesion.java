package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class BeanInicioDeSesion {

	
	private String contraseña="";

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String iniciarSesion(){
		
		String redireccion="IndexEgresado";
		
		return redireccion;
	}
	
	
	
	
	
	
	
	
	
}
