package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import org.hibernate.Session;
import org.hibernate.Transaction;
import conexion.HibernateUtil;
import entidades.Egresado;




public class EgresadoDao {
	
    
    private Egresado egresado;
    private List<Egresado> listaEgresados;
	
	public void registrar(Egresado egresado) {

		Session s = null;
	
		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.save(egresado);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}


	public void editar(Egresado egresado) {

		Session s = null;

		try {
			
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.update(egresado);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}

	}

	public void eliminar(Egresado egresado) {

		Session s = null;

		try {

			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.delete(egresado);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Egresado> listaEgresados() {

		
		List<Egresado> listaDeEgresados = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		String consulta = "from Egresado";

		try {

			listaDeEgresados = s.createQuery(consulta).getResultList();
			t.commit();
			s.close();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			t.rollback();
		}
		return listaDeEgresados;
	}


	public Egresado getEgresado() {
		return egresado;
	}


	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}


	public List<Egresado> getListaEgresados() {
		return listaEgresados;
	}


	public void setListaEgresados(List<Egresado> listaEgresados) {
		this.listaEgresados = listaEgresados;
	}


}


