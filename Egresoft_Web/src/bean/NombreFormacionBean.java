package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import dao.AdminDao;
import dao.ProgramaDao;
import dao.TipoTitulacionDao;
import entidades.Administrador;
import entidades.ProgramaFormacion;
import entidades.TipoTitulacion;
import mensajes.MessagesView;





@ManagedBean (name="programaFormacionBean", eager=true)
@RequestScoped


public class NombreFormacionBean implements Serializable{
		
		private ProgramaFormacion programaFormacion=new ProgramaFormacion();
		private List<ProgramaFormacion>listaProgramasFormacion;
		private ProgramaDao proDao;
		private MessagesView msj = new MessagesView();
		private List<SelectItem>listaFormaciones;
		
		
		public ProgramaFormacion getProgramaFormacion() {
			return programaFormacion;
		}

		public void setProgramaFormacion(ProgramaFormacion programaFormacion) {
			this.programaFormacion = programaFormacion;
		}
		public List<ProgramaFormacion> getListaProgramaFormacion() {

			proDao = new ProgramaDao();
			listaProgramasFormacion = proDao.listaProgramasFormacion();

			return listaProgramasFormacion;
		}

		public void setListaProgramasFormacion(List<ProgramaFormacion> listaProgramasFormacion) {

			this.listaProgramasFormacion = listaProgramasFormacion;
		}

		public void nuevoProgramaFormacion() {

			try {
				proDao = new ProgramaDao();
				proDao.registrar(programaFormacion);
				programaFormacion = new ProgramaFormacion();

			} catch (Exception e) {

			}

		}

		public void editarProgramaFormacion() {

			try {
				proDao = new ProgramaDao();
				proDao.editar(programaFormacion);
				programaFormacion = new ProgramaFormacion();

			} catch (Exception e) {

			}

		}

		public void eliminarAdministrador() {

			try {
				proDao = new ProgramaDao();
				proDao.editar(programaFormacion);
				programaFormacion = new ProgramaFormacion();

			} catch (Exception e) {

			}


		}

		public MessagesView getMsj() {
			return msj;
		}

		public void setMsj(MessagesView msj) {
			this.msj = msj;
		}

		public List<SelectItem> getListaFormaciones() {
			
			this.listaFormaciones=new ArrayList<SelectItem>();
			ProgramaDao t=new ProgramaDao();
			List<ProgramaFormacion> p=t.listaProgramasFormacion();
			listaFormaciones.clear();
			
			for(ProgramaFormacion programa:p){
				
				SelectItem tipoItem=new SelectItem(programa.getNombreFormacion());
				this.listaFormaciones.add(tipoItem);
			}
			return listaFormaciones;
		}

		public void setListaFormaciones(List<SelectItem> listaFormaciones) {
			this.listaFormaciones = listaFormaciones;
		}

		

	}
