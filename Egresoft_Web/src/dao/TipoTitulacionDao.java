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

		public boolean registrar(TipoTitulacion tipoTitulacion) {
		
			boolean a=false;
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.save(tipoTitulacion);
				s.getTransaction().commit();
				a=true;
			} catch (Exception e) {

				System.out.println(e.getMessage());
				a=false;
			} finally {

				if (s != null) {
					s.close();
				}
			}
			return a;
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
		
		@SuppressWarnings("unchecked")
		public TipoTitulacion buscarTipoTitulacion(String tipoT) {
			
			
			TipoTitulacion tipoTitulacion= null;
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();
			String consulta2="From TipoTitulacion as t Where t.tipoTitulacion ='"+tipoT+"'";
			try {

				tipoTitulacion =(TipoTitulacion) s.createQuery(consulta2).getSingleResult();
				System.out.println("consulta ok");
				t.commit();
				s.close();
			} catch (Exception e) {

				System.out.println(e.getMessage());
				t.rollback();
				System.out.println("No consulta ok");
			}
			return tipoTitulacion;
		}
		
		
	}
