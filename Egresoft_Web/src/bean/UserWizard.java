package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;



@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {
 	
    
    private boolean pasar;
     
    public void save() {        
        FacesMessage msg = new FacesMessage("Felicitaciones", "Actualizó sus datos");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public boolean isPasar() {
        return pasar;
    }
 
    public void setPasar(boolean pasar) {
        this.pasar = pasar;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(pasar) {
        	pasar = false;  
            return "confirmar";
        }
        else {
            return event.getNewStep();
        }
   
}
}