package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.hibernate.sql.Select;

import dao.ProgramaDao;
import dao.TipoTitulacionDao;
import entidades.ProgramaFormacion;
import entidades.TipoTitulacion;
import mensajes.MessagesView;

@ManagedBean (name="tipoTitulacionBean", eager = true)
@RequestScoped

public class TipoTitulacionBean implements Serializable{
	
	
		private TipoTitulacion tipoTitulacion=new TipoTitulacion();
		private List<TipoTitulacion>listaTipoTitulaciones;
		private TipoTitulacionDao tipoDao;
		private MessagesView msj = new MessagesView();
		private List<SelectItem>ListaTitulaciones;
		

		
		public TipoTitulacion getTipoTitulacion() {
			return tipoTitulacion;
		}

		public void setTipoTitulacion(TipoTitulacion tipoTitulacion) {
			this.tipoTitulacion = tipoTitulacion;
		}
		public List<TipoTitulacion> getListaTipoTitulaciones() {

			tipoDao = new TipoTitulacionDao();
			listaTipoTitulaciones = tipoDao.listaTipoTitulaciones();

			return listaTipoTitulaciones;
		}

		public void setListaTipoTitulaciones(List<TipoTitulacion> listaTipoTitulaciones) {

			this.listaTipoTitulaciones = listaTipoTitulaciones;
		}

		public void nuevoProgramaFormacion() {

			try {
				tipoDao = new TipoTitulacionDao();
				tipoDao.registrar(tipoTitulacion);
				tipoTitulacion = new TipoTitulacion();

			} catch (Exception e) {

			}

		}

		public void editarProgramaFormacion() {

			try {
				tipoDao = new TipoTitulacionDao();
				tipoDao.editar(tipoTitulacion);
				tipoTitulacion = new TipoTitulacion();

			} catch (Exception e) {

			}

		}

		public void eliminarAdministrador() {

			try {
				tipoDao = new TipoTitulacionDao();
				tipoDao.editar(tipoTitulacion);
				tipoTitulacion = new TipoTitulacion();

			} catch (Exception e) {

			}


		}

		public MessagesView getMsj() {
			return msj;
		}

		public void setMsj(MessagesView msj) {
			this.msj = msj;
		}

		public List<SelectItem> getListaTitulaciones() {
			
			this.ListaTitulaciones=new ArrayList<SelectItem>();
			TipoTitulacionDao t=new TipoTitulacionDao();
			List<TipoTitulacion> p=t.listaTipoTitulaciones();
			ListaTitulaciones.clear();
			
			for(TipoTitulacion tipo:p){
				
				SelectItem tipoItem=new SelectItem(tipo.getTipoTitulacion());
				this.ListaTitulaciones.add(tipoItem);
			}
			return ListaTitulaciones;
		}

		public void setListaTitulaciones(List<SelectItem> listaTitulaciones) {
			ListaTitulaciones = listaTitulaciones;
		}


		
		
	}
