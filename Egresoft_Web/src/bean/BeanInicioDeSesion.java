package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class BeanInicioDeSesion {

	
	private String contrase�a="";

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String iniciarSesion(){
		
		String redireccion="IndexEgresado";
		
		return redireccion;
	}
	
	
	
	
	
	
	
	
	
}
