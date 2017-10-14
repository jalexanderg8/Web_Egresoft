package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import entidades.NumeroFicha;




@ManagedBean
@RequestScoped
		public class NumeroFichaBean {

		private NumeroFicha numeroFicha=new NumeroFicha();

		public NumeroFicha getNumeroFicha() {
			return numeroFicha;
		}

		public void setNumeroFicha(NumeroFicha numeroFicha) {
			this.numeroFicha = numeroFicha;
		}
		
		
	}
