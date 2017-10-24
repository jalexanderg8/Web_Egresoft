package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import conexion.Conexion;
import javassist.bytecode.stackmap.BasicBlock.Catch;

public class RecuperarContraseña 
{
	static Conexion cc = new Conexion();
    static Connection cn = cc.conexion();
    
    String nombres;
    String Contraseña;
    
    

	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	private void recuperarContraseña(String codigo)
	{
		try
		{
			String sql = "SELECT * FROM egresado WHERE email_principal = '" + codigo + "'";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String nombres = null;
			String contraseña = null;
			
			if(rs.next())
			{	
				nombres = rs.getString(5);
				contraseña = rs.getString(6);
				
				String nombre1 = "NOMBRE: " + nombres, contraseña1 = "CONTRASEÑA: " + contraseña;
				
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(Recupera.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}






