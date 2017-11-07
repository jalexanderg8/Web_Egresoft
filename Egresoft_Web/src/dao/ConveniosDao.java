package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import entidades.Convenio;
import entidades.Noticia;

public class ConveniosDao {

	public void guardarConvenio(Convenio objConvenio, String imagen) {

		Conexion conex = new Conexion();

		
	// FALTA AGREGAR LOS DEMAS ATRIBUTOS DE LA ENTIDAD CONVENIOS
		
		try {
			
			String consulta="INSERT INTO convenio (idConvenio,nombrel_covenio,info_convenio,imagen_convenio,persona_contacto) VALUES (?,?,?,?,?)";				

			PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

			preStatement.setInt(1, 0);
			preStatement.setString(2, objConvenio.getNombrelCovenio());
			preStatement.setString(3, objConvenio.getInfoConvenio());
			preStatement.setString(4, imagen);
			preStatement.setString(5, objConvenio.getPersonaContacto());
			preStatement.executeUpdate();
			
			conex.desconectar();
			
			
		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+FILE+" se subio"));
			
		} catch (SQLException e) {
			System.out.println("errorr al guardar");
			System.out.println(e.getMessage());
			
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+FILE+" se subio"));

		}
		
		
		
	}

	public ArrayList<Convenio> consultar(ArrayList<Convenio> listaConvenios, Convenio objConvenio) {

		Conexion conex = new Conexion();
		  
		ResultSet resultado;
		String imagen="";
		
		try {
			
			String consulta="select idConvenio,nombrel_covenio,info_convenio,imagen_convenio,persona_contacto from convenio";				

			PreparedStatement preStatement = (PreparedStatement) conex.getConnection().prepareStatement(consulta);

			resultado=preStatement.executeQuery();
			
			while (resultado.next()) {
		
				objConvenio=new Convenio();
				
				objConvenio.setIdConvenio(resultado.getInt(1));
				objConvenio.setNombrelCovenio(resultado.getString(2));
				objConvenio.setInfoConvenio(resultado.getString(3));
				objConvenio.setImagenConvenio("/resources/imgBD/"+resultado.getString(4));
				objConvenio.setPersonaContacto(resultado.getString(5));
				
				
		    	listaConvenios.add(objConvenio);
				
			}
			
			
			conex.desconectar();
			
			
		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excito "+file.getFileName()+" se subio"));
			
		} catch (SQLException e) {
			System.out.println("errorr al guardar");
			System.out.println(e.getMessage());
			
			
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no se pudo guardar "+file.getFileName()+" se subio"));

		}
		System.out.println("img: "+imagen);
		return listaConvenios;
	}

	public void eliminar(Convenio convenioo) {

		
		Conexion conex = new Conexion();

		try {
			Statement sentencia = conex.getConnection().createStatement();	
			sentencia.executeUpdate("delete from convenio where idConvenio= "+convenioo.getIdConvenio()+"");
			
			 //JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

			
			sentencia.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
