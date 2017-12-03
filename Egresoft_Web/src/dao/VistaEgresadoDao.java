package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.custom.Return;

import com.mysql.jdbc.PreparedStatement;

import conexion.Conexion;
import conexion.HibernateUtil;
import entidades.Egresado;
import entidades.Vistaegresados;
import entidades.VistaegresadosId;


public class VistaEgresadoDao {

	@SuppressWarnings({ "unchecked", "null" })
	public ArrayList<VistaegresadosId> listaDeVista() throws SQLException {

		ArrayList<VistaegresadosId> listaDeVistas =new ArrayList<>();
		Conexion conexion = new Conexion();
		System.out.println("Abro conexion para buscar");
		VistaegresadosId v = null;
		

		try {
			System.out.println("en el try");
			PreparedStatement consulta = (PreparedStatement) conexion.getConnection()
					.prepareStatement("SELECT * FROM VistaEgresados");//consulta que devuelve la informacion de un egresado desde la vista SQL con parametro del documento
			//consulta.setLong(1, idEgresado);
			ResultSet res = consulta.executeQuery();
			System.out.println("despues de executeQyery");

			while (res.next()) {
				v=new VistaegresadosId();
				v.setTipoDocumento(res.getString("tipo_documento"));
				v.setIdEgresado(res.getLong("idEgresado"));
				v.setNombres(res.getString("nombres"));
				v.setApellidos(res.getString("apellidos"));
				v.setTelefonoPrincipal(res.getString("telefono_principal"));
				v.setTelefonoAlterno(res.getString("telefono_alterno"));
				v.setEmailPrincipal(res.getString("email_principal"));
				v.setEmailAlterno(res.getString("email_alterno"));
				v.setLugarResidencia(res.getString("lugar_residencia"));
				v.setModalidad(res.getString("modalidad"));
				v.setTipoTitulacion(res.getString("tipo_titulacion"));
				v.setNombreFormacion(res.getString("nombre_formacion"));
				v.setNumeroDeFicha(res.getString("numero_de_ficha"));
				v.setContrasena(res.getString("contrasena"));
				v.setFechaInicio(res.getDate("fecha_inicio"));
				v.setFechaFin(res.getDate("fecha_fin"));
				System.out.println(v.toString());
				
				listaDeVistas.add(v);
			}
			
			
			
			System.out.println("despues de llenar la lista");

			res.close();
			System.out.println("cerró resultset");
			conexion.desconectar();
			System.out.println("cerro la BD");
			return listaDeVistas;
			

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Estamos en la excepcion");
			return null;
		}
		
		
	}
	
	

	public boolean registrar(Egresado egresado) {

		boolean a = false;
		Session s = null;

		try {
			s = HibernateUtil.sessionFactory.openSession();
			s.beginTransaction();
			s.save(egresado);
			s.getTransaction().commit();
			a = true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			a = false;
		} finally {

			if (s != null) {
				s.close();
			}
		}
		return a;
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

	

	public VistaegresadosId consultarDoc(long idEgresado) {
		
		
		VistaegresadosId v=new VistaegresadosId();
		Conexion conexion = new Conexion();

		try {
			System.out.println("en el try");
			PreparedStatement consulta = (PreparedStatement) conexion.getConnection()
					.prepareStatement("SELECT * FROM VistaEgresados WHERE idEgresado="+idEgresado);//consulta que devuelve la informacion de un egresado desde la vista SQL con parametro del documento
			//consulta.setLong(1, idEgresado);
			ResultSet res = consulta.executeQuery();
			System.out.println("despues de executeQyery");

			if (res.next()&&idEgresado!=0) {
				v.setTipoDocumento(res.getString("tipo_documento"));
				v.setIdEgresado(res.getLong("idEgresado"));
				v.setNombres(res.getString("nombres"));
				v.setApellidos(res.getString("apellidos"));
				v.setTelefonoPrincipal(res.getString("telefono_principal"));
				v.setTelefonoAlterno(res.getString("telefono_alterno"));
				v.setEmailPrincipal(res.getString("email_principal"));
				v.setEmailAlterno(res.getString("email_alterno"));
				v.setLugarResidencia(res.getString("lugar_residencia"));
				v.setModalidad(res.getString("modalidad"));
				v.setTipoTitulacion(res.getString("tipo_titulacion"));
				v.setNombreFormacion(res.getString("nombre_formacion"));
				v.setNumeroDeFicha(res.getString("numero_de_ficha"));
				v.setContrasena(res.getString("contrasena"));
				v.setFechaInicio(res.getDate("fecha_inicio"));
				v.setFechaFin(res.getDate("fecha_fin"));
				
				
			}else{
				
				System.out.println("Usuario no esta en base de datos ");
				return null;
			}

			System.out.println("dfespues de asignar valores a variables internas");

			res.close();
			System.out.println("cerró resultset");
			conexion.desconectar();
			System.out.println("cerro la BD");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		
			System.out.println(v.toString());
			return v;	
	}
	public static void main(String[] args) throws SQLException{
		
		VistaEgresadoDao v=new VistaEgresadoDao();
		
		
	      System.out.println(v.listaDeVista());
		}
	
}
