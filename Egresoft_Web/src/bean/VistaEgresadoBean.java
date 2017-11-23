package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Egresado;
import entidades.VistaegresadoId;

@ManagedBean (name="vistaEgresadoBena", eager = true )
@ViewScoped
	public class VistaEgresadoBean {

	private long idEgresado;
	VistaegresadoId vistaegresadoId;
	
	
		public VistaegresadoId bucarEgresado() throws Exception{
		
		
		
		return vistaegresadoId;	
	}


		public long getIdEgresado() {
			return idEgresado;
		}


		public void setIdEgresado(long idEgresado) {
			this.idEgresado = idEgresado;
		}
		
	}
