package entidades;
// Generated 16/11/2017 09:52:27 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Administrador generated by hbm2java
 */
@Entity
@Table(name = "administrador", catalog = "egresoft")
public class Administrador implements java.io.Serializable {

	private Integer idAdministrador;
	private String nombreUsuarioAdmin;
	private String contrasenaAdmin;

	public Administrador() {
	}

	public Administrador(String nombreUsuarioAdmin, String contrasenaAdmin) {
		this.nombreUsuarioAdmin = nombreUsuarioAdmin;
		this.contrasenaAdmin = contrasenaAdmin;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idAdministrador", unique = true, nullable = false)
	public Integer getIdAdministrador() {
		return this.idAdministrador;
	}

	public void setIdAdministrador(Integer idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	@Column(name = "nombre_usuario_admin", nullable = false, length = 45)
	public String getNombreUsuarioAdmin() {
		return this.nombreUsuarioAdmin;
	}

	public void setNombreUsuarioAdmin(String nombreUsuarioAdmin) {
		this.nombreUsuarioAdmin = nombreUsuarioAdmin;
	}

	@Column(name = "contrasena_admin", nullable = false, length = 45)
	public String getContrasenaAdmin() {
		return this.contrasenaAdmin;
	}

	public void setContrasenaAdmin(String contrasenaAdmin) {
		this.contrasenaAdmin = contrasenaAdmin;
	}

}
