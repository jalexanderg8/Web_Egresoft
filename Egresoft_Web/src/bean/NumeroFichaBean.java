package bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import dao.NumeroFichaDao;
import dao.ProgramaDao;
import entidades.NumeroFicha;
import entidades.ProgramaFormacion;
import mensajes.MessagesView;




@ManagedBean (name="numeroFichaBean", eager=true)
@RequestScoped
		public class NumeroFichaBean implements Serializable{

		private NumeroFicha numeroFicha=new NumeroFicha();
		private List<NumeroFicha>listaNumeroFicha;
		private NumeroFichaDao numFichaDao;
		
		
		private MessagesView msj = new MessagesView();

		public NumeroFicha getNumeroFicha() {
			return numeroFicha;
		}

		public void setNumeroFicha(NumeroFicha numeroFicha) {
			this.numeroFicha = numeroFicha;
		}
		public List<NumeroFicha> getListaNumeroFicha() {

			numFichaDao = new NumeroFichaDao();
			listaNumeroFicha = numFichaDao.listaNumeroFicha();

			return listaNumeroFicha;
		}

		public void setListaNumeroFicha(List<NumeroFicha> listaNumeroFicha) {

			this.listaNumeroFicha = listaNumeroFicha;
		}

		public void nuevoProgramaFormacion() {

			try {
				numFichaDao = new NumeroFichaDao();
				numFichaDao.registrar(numeroFicha);
				numeroFicha = new NumeroFicha();

			} catch (Exception e) {

			}

		}

		public void editarProgramaFormacion() {

			try {
				numFichaDao = new NumeroFichaDao();
				numFichaDao.editar(numeroFicha);
				numeroFicha = new NumeroFicha();

			} catch (Exception e) {

			}

		}

		public void eliminarAdministrador() {

			try {
				numFichaDao = new NumeroFichaDao();
				numFichaDao.editar(numeroFicha);
				numeroFicha = new NumeroFicha();

			} catch (Exception e) {

			}


		}

		public MessagesView getMsj() {
			return msj;
		}

		public void setMsj(MessagesView msj) {
			this.msj = msj;
		}
		
		
	}
