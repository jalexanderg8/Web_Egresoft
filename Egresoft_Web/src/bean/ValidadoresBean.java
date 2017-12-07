package bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class ValidadoresBean {

		
		   public void celular(FacesContext arg0, UIComponent arg1, Object arg2) 
		         throws ValidatorException {
			   
			   
			   if (((String) arg2).length() != 0) {
					
				   String cadenaCelular = (String) arg2;

					Pattern pat = Pattern.compile("^[3][0-9]{9}$");
					Matcher mat = pat.matcher(cadenaCelular);

					if (!mat.matches()) {
						throw new ValidatorException(
								new FacesMessage("Ingrese un numero de celular valido"));
					}
				}
		      
		   }
		   
		   public void correo(FacesContext arg0, UIComponent arg1, Object arg2) 
			         throws ValidatorException {
				   
				   
				   if (((String) arg2).length() != 0) {
						
					   String cadenaCorreo = (String) arg2;

						Pattern pat = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");
						Matcher mat = pat.matcher(cadenaCorreo);

						if (!mat.matches()) {
							throw new ValidatorException(
									new FacesMessage("Ingrese un correo valido"));
						}
					}
			      
			   }
		   
		   public void ciudad(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
			   
				if(((String) arg2).length()!=0){
					
					if (((String) arg2).length() <2||((String) arg2).length() > 45) {
						throw new ValidatorException(new FacesMessage("maximo 45 caracteres "));
					}
				}
				
			}
			}
			   
		      
	
