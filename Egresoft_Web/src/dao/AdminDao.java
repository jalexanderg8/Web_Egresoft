package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.jdbc.PreparedStatement;

import conexion.Conexion;
import conexion.HibernateUtil;
import entidades.Administrador;
import entidades.Egresado;

public class AdminDao {

		
		private Administrador admin;
		private List<Administrador>listaAdministradores;
			
		public void registrar(Administrador admin) {
			
			Session s = null;
			
			
		
			try {
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.save(admin);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}
		}
		
		public void editar(Administrador admin) {

			Session s = null;

			try {
				
				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.update(admin);
				s.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			} finally {

				if (s != null) {
					s.close();
				}
			}

		}
		
		public void eliminar(Administrador admin) {

			Session s = null;

			try {

				s = HibernateUtil.sessionFactory.openSession();
				s.beginTransaction();
				s.delete(admin);
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
		public List<Administrador> listaAdministradores() {

			
			List<Administrador> listaAdministradores = null;
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();
			String consulta = "FROM Administrador";

			try {

				listaAdministradores = s.createQuery(consulta).getResultList();
				t.commit();
				s.close();
			} catch (Exception e) {

				System.out.println(e.getMessage());
				t.rollback();
			}
			return listaAdministradores;
		}

		public Administrador getAdmin() {
			return admin;
		}

		public void setAdmin(Administrador admin) {
			this.admin = admin;
		}

		public List<Administrador> getListaAdministradores() {
			return listaAdministradores;
		}

		public void setListaAdministradores(List<Administrador> listaAdministradores) {
			this.listaAdministradores = listaAdministradores;
		}
		
		public Administrador consultarAdmin(String nombreUsuario, String contrasena) {
			System.out.println("Entro al dao a buscar un usuario por nombre y contraseña ");
			Administrador admin = null;
			
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction t=null;
			System.out.println("Abro conexion para buscar");
			try {
				t = s.beginTransaction();
				String sql = "SELECT * FROM administrador WHERE nombre_usuario_admin = :nombre_usuario_admin and contrasena_admin = :contrasena_admin ";
				SQLQuery query = s.createSQLQuery(sql);
				query.addEntity(Administrador.class);
				query.setParameter("nombre_usuario_admin", nombreUsuario);
				query.setParameter("contrasena_admin", contrasena);
				List results = query.list();
				
				for (Iterator iterator = results.iterator(); iterator.hasNext();) {
					admin = (Administrador) iterator.next();
					
				}
				
			} catch (HibernateException e) {
				if (t != null)
					t.rollback();
				e.printStackTrace();
			} finally {
				s.close();
			}
			return admin;
		}
		/*public Administrador consultarAdmin(String Usuario,String Contraseña){
			String contraseña="";
			String usuario="";	
			Conexion conexion=new Conexion();
				
			try {
				System.out.println("en el try");
				//PreparedStatement consulta = (PreparedStatement) conexion.getConnection().prepareStatement("SELECT * FROM administrador WHERE nombre_usuario_admin=? AND contrasena_admin=?");
				PreparedStatement consulta = (PreparedStatement) conexion.getConnection().prepareStatement("SELECT * FROM administrador WHERE nombre_usuario_admin=? AND contrasena_admin=?");
				consulta.setString(1, Usuario);
				consulta.setString(2,Contraseña);		
				ResultSet res = consulta.executeQuery();
				System.out.println("despues de executeQyery");
				 //JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
				if (res.next())
				{
					 usuario=res.getString("nombre_usuario_admin");
						contraseña=res.getString("contrasena_admin");
				}				
				System.out.println("dfespues de asignar valores a variables internas");
		
				res.close();
				System.out.println("cerró resultset");
				conexion.desconectar();
				System.out.println("cerro la BD");

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (usuario.equals(Usuario) && contraseña.equals(Contraseña)) {
				return true;

			}else{
				return false;
			}
			
		}*/

	}
