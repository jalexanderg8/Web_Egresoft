package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.HibernateUtil;
import entidades.NumeroFicha;
import entidades.NumeroFichaEgresado;

public class NumerofichaEgresadoDao {

			
				
				@SuppressWarnings("unchecked")
				public List<NumeroFichaEgresado> listaNumeroFichas() {
					
					
					List<NumeroFichaEgresado> listaNumeroFicha = null;
					Session s = HibernateUtil.getSessionFactory().openSession();
					Transaction t = s.beginTransaction();
					String consulta = "FROM Numero_ficha_egresado";

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
				
				public boolean registrar(NumeroFichaEgresado numeroFichaEgresado) {
					
					boolean a=false;
					Session s = null;
					
					
					try {
						s = HibernateUtil.sessionFactory.openSession();
						s.beginTransaction();
						s.save(numeroFichaEgresado);
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
				
				public void editar(NumeroFichaEgresado numeroFichaEgresado) {
					
					Session s = null;
					
					
					try {
						s = HibernateUtil.sessionFactory.openSession();
						s.beginTransaction();
						s.update(numeroFichaEgresado);
						s.getTransaction().commit();
					} catch (Exception e) {

						System.out.println(e.getMessage());
					} finally {

						if (s != null) {
							s.close();
						}
					}
				}
				
				public void eliminar(NumeroFichaEgresado numeroFichaEgresado) {
					
					Session s = null;
					
					
					try {
						s = HibernateUtil.sessionFactory.openSession();
						s.beginTransaction();
						s.delete(numeroFichaEgresado);
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
