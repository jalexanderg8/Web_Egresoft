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

public class RecuperarContrase�a 
{
	static Conexion cc = new Conexion();
    static Connection cn = cc.conexion();
    
    String nombres;
    String Contrase�a;
    
    

	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getContrase�a() {
		return Contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
	}

	private void recuperarContrase�a(String codigo)
	{
		try
		{
			String sql = "SELECT * FROM egresado WHERE email_principal = '" + codigo + "'";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String nombres = null;
			String contrase�a = null;
			
			if(rs.next())
			{	
				nombres = rs.getString(5);
				contrase�a = rs.getString(6);
				
				String nombre1 = "NOMBRE: " + nombres, contrase�a1 = "CONTRASE�A: " + contrase�a;
				
			}
			
		}catch (SQLException ex) {
			Logger.getLogger(Recupera.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}






