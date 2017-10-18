package conexion;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexion {

	
	private String nombreBD="egresoft";
	private String usuario="root";
	private String contraseña="";
	private String url="jdbc:mysql://localhost/"+nombreBD;
	
	Connection conexion=null;
	
	public Conexion() {

	try {
		Class.forName("com.mysql.jdbc.Driver");
		
			conexion=(Connection) DriverManager.getConnection(url,usuario,contraseña);
		
		if (conexion!=null) {
			System.out.println("se conecto a BD");
		}
	} catch (ClassNotFoundException e) {
		
		System.out.println("no se conecto error de clasfaun eseption"+e.getMessage());
		e.printStackTrace();
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public Connection getConnection(){
		return conexion;
	}

	public void desconectar(){
		conexion=null;
	}
	
}
