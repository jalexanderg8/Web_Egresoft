package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.HibernateUtil;
import entidades.NumeroFicha;

public class NumeroFichaDao {
		
		@SuppressWarnings("unchecked")
		public List<NumeroFicha> listaNumeroFicha() {
			
			
			List<NumeroFicha> listaNumeroFicha = null;
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();
			String consulta = "FROM Numero_ficha";

			try {

				listaNumeroFicha = s.createQuery(consulta).getResultList();
				t.commit();
				s.close();
			} catch (Exception e) {

				System.out.println(e.getMessage());
				t.rollback();
			}
			return listaNumeroFicha;
		}

		public void registrar(NumeroFicha numeroFicha) {
		
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.save(numeroFicha);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}
		}
		
		public void editar(NumeroFicha numeroFicha) {
			
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.update(numeroFicha);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}
		}
		
		public void eliminar(NumeroFicha numeroFicha) {
			
			Session s = null;
			
			
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.delete(numeroFicha);
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
