package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import conexion.Conexion;
import entidades.Convenio;
import entidades.Evento;

public class EventosDao {

	public void guardarConvenio(Evento objEvento, String imagen) {

		Conexion conex = new Conexion();

		
		// FALTA AGREGAR LOS DEMAS ATRIBUTOS DE LA ENTIDAD CONVENIOS
			
			try {
				
				String consulta="INSERT INTO evento (idEvento,nombre_evento,url_redireccion,imagen_evento,descripcion_evento) VALUES (?,?,?,?,?)";				

				PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

				preStatement.setInt(1, 0);
				preStatement.setString(2, objEvento.getNombreEvento());
				preStatement.setString(3, objEvento.geturl_redireccion());
				preStatement.setString(4, imagen);
				preStatement.setString(5, objEvento.getDescripcionEvento());
				preStatement.executeUpdate();
				
				conex.desconectar();
				
				
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+FILE+" se subio"));
				
			} catch (SQLException e) {
				System.out.println("errorr al guardar");
				System.out.println(e.getMessage());
				
				// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+FILE+" se subio"));

			}
		
	}

	public ArrayList<Evento> consultar(ArrayList<Evento> listaEventos, Evento objEvento) {
		Conexion conex = new Conexion();
		  
		ResultSet resultado;
		String imagen="";
		
		try {
			
			String consulta="select idEvento,nombre_evento,url_redireccion,imagen_evento,descripcion_evento from evento";				

			PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

			resultado=preStatement.executeQuery();
			
			while (resultado.next()) {
		
				objEvento=new Evento();
				
				objEvento.setIdEvento(resultado.getInt(1));
				objEvento.setNombreEvento(resultado.getString(2));
				objEvento.seturl_redireccion(resultado.getString(3));
				objEvento.setImagenEvento("/resources/imgBD/"+resultado.getString(4));
				objEvento.setDescripcionEvento(resultado.getString(5));
				
				
		    	listaEventos.add(objEvento);
				
			}
			
			
			conex.desconectar();
			
			
		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+file.getFileName()+" se subio"));
			
		} catch (SQLException e) {
			System.out.println("errorr al guardar");
			System.out.println(e.getMessage());
			
			
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+file.getFileName()+" se subio"));

		}
		System.out.println("img: "+imagen);
		return listaEventos;
	}

	public void eliminar(Evento evento) {

		Conexion conex = new Conexion();

		try {
			Statement sentencia = conex.getConnection().createStatement();	
			sentencia.executeUpdate("delete from evento where idEvento= "+evento.getIdEvento()+"");
			
			 //JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

			
			sentencia.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
	
}
