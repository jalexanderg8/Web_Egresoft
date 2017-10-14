package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import entidades.ProgramaFormacion;





@ManagedBean
@RequestScoped
public class NombreFormacionBean {
		
		private ProgramaFormacion programaFormacion=new ProgramaFormacion();

		public ProgramaFormacion getProgramaFormacion() {
			return programaFormacion;
		}

		public void setProgramaFormacion(ProgramaFormacion programaFormacion) {
			this.programaFormacion = programaFormacion;
		}
		
		

	}
