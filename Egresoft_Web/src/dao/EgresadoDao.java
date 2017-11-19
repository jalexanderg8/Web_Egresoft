package dao;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.jdbc.PreparedStatement;

import conexion.Conexion;
import conexion.HibernateUtil;
import entidades.Egresado;






public class EgresadoDao {
	
    
    private Egresado egresado;
    private List<Egresado> listaEgresados;
    private Egresado egresadoAtributos;
    private Egresado egresadoMail;
	
	public boolean registrar(Egresado egresado) {

		Session s = null;
		boolean a;
						
	
		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.save(egresado);
			s.getTransaction().commit();
			
			a=true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			a= false;
		} finally {

			if (s != null) {
				s.close();
			}
		}
		return a;
	}
////administrador editar egresado//////
	public void editarEgresadoDesdeAdmin(Egresado egresado) {

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
	// editaaar egresado desde egresado////
	public boolean editaEgresadoFromEgresado(Egresado egresado) {
		System.out.println("1");

				Conexion conex = new Conexion();
				
				System.out.println("2");

				boolean B=false;
				
				System.out.println("3");

				
				java.sql.Date  fechaInicio = new Date(egresado.getFechaInicio().getTime());
				java.sql.Date  fechaFin = new Date(egresado.getFechaFin().getTime());
				
				System.out.println("lo que ingreso en la pagina: "+egresado.getFechaInicio()+" convertido a sqldate: "+ fechaFin);

				System.out.println("4");

				
				try {
					System.out.println("5");

					
					String consulta = "UPDATE egresado SET telefono_principal=?, telefono_alterno=?, email_alterno=? , "
							+"modalidad=? ,lugar_residencia=? ,contrasena=? ,fecha_inicio=? , fecha_fin=? WHERE idEgresado= ? ";

					System.out.println("6");
					
					System.out.println("sentencia: "+consulta);
					String datos="";
					datos+="tel principal -"+egresado.getTelefonoPrincipal();
					datos+="tel alterno - "+egresado.getTelefonoAlterno();
					datos+="email alterno - "+egresado.getEmailAlterno();
					datos+="modalidad -"+egresado.getModalidad();
					datos+="residencia - "+egresado.getLugarResidencia();
					datos+="contrato - "+egresado.getContrasena();
					datos+="fecha inicio - "+((Date) fechaInicio);
					datos+="fecha fin - "+((Date) fechaFin);
					datos+="id - "+egresado.getIdEgresado();
					System.out.println(datos);

					
					PreparedStatement statement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

					System.out.println("7");

					statement.setString(1, egresado.getTelefonoPrincipal());
					statement.setString(2, egresado.getTelefonoAlterno());
					statement.setString(3, egresado.getEmailAlterno());
					statement.setString(4, egresado.getModalidad());
					statement.setString(5, egresado.getLugarResidencia());
					statement.setString(6, egresado.getContrasena());
					statement.setDate(7,(Date) fechaInicio);
					statement.setDate(8, (Date) fechaFin);
					statement.setLong(9, egresado.getIdEgresado());

					System.out.println("8");

					
					statement.executeUpdate();

					System.out.println("9");

					
					B=true;

					System.out.println("10");

					
					statement.close();
					conex.desconectar();

					System.out.println("11");

				} catch (SQLException e) {

					System.out.println(e);
			
		           System.out.println("error de sql");
		           
		           B=false;
		           
				}
				return B;

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
		String consulta = "FROM Egresado";

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
	 

	//CONSULTA PARA QUE EL WIZARD CONTINUE

		public boolean consultarDoc(long idEgresado){
			long usuario = 0;		
			Conexion conexion=new Conexion();

			try {
				System.out.println("en el try");
				PreparedStatement consulta = (PreparedStatement) conexion.getConnection().prepareStatement("SELECT *  FROM egresado WHERE idEgresado=?");
				consulta.setLong(1, idEgresado);
				ResultSet res = consulta.executeQuery();
				System.out.println("despues de executeQyery");

				if (res.next())
				{
					 usuario=res.getLong("idEgresado");
				}
				
				System.out.println("dfespues de asignar valores a variables internas");
			
				res.close();
				System.out.println("cerró resultset");
				conexion.desconectar();
				System.out.println("cerro la BD");

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (usuario==idEgresado) {
				System.out.println(usuario+" y "+idEgresado);
	System.out.println("true");
				return true;

			}else{
				System.out.println("false");

				return false;
			}
			
		}
	
	/*public Egresado consultarEgresado(String Usuario,String Contraseña){
	String contraseña="";
	String usuario="";
	
	Conexion conexion=new Conexion();
	System.out.println("usuario "+Usuario+" contraseña "+Contraseña);
	
	try {
		System.out.println("en el try");
		PreparedStatement consulta = (PreparedStatement) conexion.getConnection().prepareStatement("SELECT * FROM egresado WHERE email_principal=? AND contrasena=?");
		//PreparedStatement consulta = (PreparedStatement) conexion.getConnection().prepareStatement("SELECT * FROM Egresado WHERE idEgresado= "+Usuario+" AND contrasena= '"+Contraseña+"'");
		consulta.setString(1, Usuario);
		consulta.setString(2,Contraseña);		
		ResultSet res = consulta.executeQuery();
		System.out.println("despues de executeQyery");
		 //JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

		if (res.next())
		{
			 usuario=res.getString("email_principal");
				contraseña=res.getString("contrasena");
		}
		
		System.out.println("dfespues de asignar valores a variables internas");
	
		res.close();
		System.out.println("cerró resultset");
		conexion.desconectar();
		System.out.println("cerro la BD");

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	System.out.println("usuario ingresado: "+Usuario+" contraseña ingresada:  "+Contraseña+" usuario de bd: "+usuario+" contraseña bd: "+contraseña);
	
	if (usuario.equals(Usuario) && contraseña.equals(Contraseña)) {
		System.err.println("verdadero");
		return true;

	}else{
		System.err.println("false");

		return false;
	}
	
}*/

	
//CONSULTA PARA EL LOGIN
	public Egresado consultarEgresado(String emailPrincipal, String contrasena) {
		System.out.println("Entro al dao a buscar un usuario por nombre y contraseña ");
		Egresado egre = null;
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		System.out.println("Abro conexion para buscar");
		try {
			t = s.beginTransaction();
			String sql = "SELECT * FROM egresado WHERE email_principal = :email_principal and contrasena = :contrasena ";
			SQLQuery query = s.createSQLQuery(sql);
			query.addEntity(Egresado.class);
			query.setParameter("email_principal", emailPrincipal);
			query.setParameter("contrasena", contrasena);
			List results = query.list();
			
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				egre = (Egresado) iterator.next();
				
			}
			
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return egre;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Egresado buscarEgresado(long dniEgresado) {
	
	
		Egresado egresadoAtributos=null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		
		
		try{
			t = s.beginTransaction();
			egresadoAtributos=(Egresado)s.get(Egresado.class, dniEgresado);
			t.commit();
			
			
		}catch (Exception e) {
			
			if (t != null)
				t.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		
		return egresadoAtributos;
	}
	
	public Egresado buscarMail(String direccionEmail) {
	
		egresadoMail = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		System.out.println("Abro conexion para buscar");
		
		try {
			t = s.beginTransaction();
			String sql = "SELECT * FROM egresado WHERE email_principal = :email_principal";
			System.out.println("estoy en la busqueda");
			SQLQuery query = s.createSQLQuery(sql);
			query.addEntity(Egresado.class);
			query.setParameter("email_principal", direccionEmail);
			List results = query.list();
			
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				egresadoMail = (Egresado) iterator.next();
				
			}
			} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			} finally {
			s.close();
			}
		System.out.println("devuelvo el objeto");		
		return egresadoMail;
	}
	public Egresado getEgresadoAtributos() {
		return egresadoAtributos;
	}
	public void setEgresadoAtributos(Egresado egresadoAtributos) {
		this.egresadoAtributos = egresadoAtributos;
	}
	public Egresado getEgresadoMail() {
		return egresadoMail;
	}
	public void setEgresadoMail(Egresado egresadoMail) {
		this.egresadoMail = egresadoMail;
	}
	
	
}
	
	
		
	



