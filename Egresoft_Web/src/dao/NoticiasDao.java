package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import conexion.Conexion;
import entidades.Noticia;

public class NoticiasDao {

	
	public void guardarNotiEvento(Noticia objNotiEvento, String imagen){
		
		
		Conexion conex = new Conexion();

		try {
			
			String consulta="INSERT INTO noticia (idNoticia,nombre_noticia,descripcion_noticia,imagen_noticia) VALUES (?,?,?,?)";				

			PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

			preStatement.setInt(1, 0);
			preStatement.setString(2, objNotiEvento.getNombreNoticia());
			preStatement.setString(3, objNotiEvento.getDescripcionNoticia());
			preStatement.setString(4, imagen);
			preStatement.executeUpdate();
			
			conex.desconectar();
		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+FILE+" se subio"));
			
		} catch (SQLException e) {
			System.out.println("errorr al guardar");
			System.out.println(e.getMessage());
			
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+FILE+" se subio"));

		}
	}

	public ArrayList<Noticia> consultar(ArrayList<Noticia> listaNotiEventos, Noticia notiEvento) {

			Conexion conex = new Conexion();
	  
			ResultSet resultado;
			String imagen="";
			
			try {
				
				String consulta="select idNoticia,nombre_noticia,descripcion_noticia,imagen_noticia from noticia";				

				PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

				resultado=preStatement.executeQuery();
				
				while (resultado.next()) {
			
					notiEvento=new Noticia();
					
					notiEvento.setIdNoticia(resultado.getInt(1));
					notiEvento.setNombreNoticia(resultado.getString(2));
					notiEvento.setDescripcionNoticia(resultado.getString(3));
					notiEvento.setImagenNoticia("/resources/imgBD/"+resultado.getString(4));
					
			    	listaNotiEventos.add(notiEvento);
					
				}
				
				
				conex.desconectar();
				
				
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+file.getFileName()+" se subio"));
				
			} catch (SQLException e) {
				System.out.println("errorr al guardar");
				System.out.println(e.getMessage());
				
				
				// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+file.getFileName()+" se subio"));

			}
			System.out.println("img: "+imagen);
			return listaNotiEventos;
		}

	public void eliminar(Noticia noticia) {
		Conexion conex = new Conexion();

		try {
			Statement sentencia = conex.getConnection().createStatement();	
			sentencia.executeUpdate("delete from noticia where idConvenio= "+noticia.getIdNoticia()+"");
			
			 //JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

			
			sentencia.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}

	
}
