package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import conexion.HibernateUtil;
import entidades.TipoTitulacion;

public class TipoTitulacionDao {

		
		@SuppressWarnings("unchecked")
		public List<TipoTitulacion> listaTipoTitulaciones() {
			
			
			List<TipoTitulacion> listaTipoTitulaciones = null;
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();
			String consulta = "FROM Tipo_titulacion";

			try {

				listaTipoTitulaciones = s.createQuery(consulta).getResultList();
				t.commit();
				s.close();
			} catch (Exception e) {

				System.out.println(e.getMessage());
				t.rollback();
			}
			return listaTipoTitulaciones;
		}

		public void registrar(TipoTitulacion tipoTitulacion) {
		
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.save(tipoTitulacion);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}
		}
		
		public void editar(TipoTitulacion tipoTitulacion) {
			
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.update(tipoTitulacion);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}
		}
		
		public void eliminar(TipoTitulacion tipoTitulacion) {
			
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.delete(tipoTitulacion);
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
