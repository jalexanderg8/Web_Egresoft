package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.HibernateUtil;
import entidades.ProgramaFormacion;

public class ProgramaDao {


	@SuppressWarnings("unchecked")
	public List<ProgramaFormacion> listaProgramasFormacion() {
		
		
		List<ProgramaFormacion> listaProgramasFormacion = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		String consulta = "FROM Programa_formacion";

		try {

			listaProgramasFormacion = s.createQuery(consulta).getResultList();
			t.commit();
			s.close();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			t.rollback();
		}
		return listaProgramasFormacion;
	}

	public void registrar(ProgramaFormacion programaFormacion) {
	
		Session s = null;
		
		
		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.save(programaFormacion);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}
	
	public void editar(ProgramaFormacion programaFormacion) {
		
		Session s = null;
		
		
		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.update(programaFormacion);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}
	
	public void eliminar(ProgramaFormacion programaFormacion) {
		
		Session s = null;
		
		
		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.delete(programaFormacion);
			s.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}

	}
