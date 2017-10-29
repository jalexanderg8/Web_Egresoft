package entidades;
// Generated 28/10/2017 02:18:48 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Egresado generated by hbm2java
 */
@Entity
@Table(name = "egresado", catalog = "egresoft")
public class Egresado implements java.io.Serializable {

	private long idEgresado;
	private String tipoDocumento;
	private String nombres;
	private String apellidos;
	private String telefonoPrincipal;
	private String telefonoAlterno;
	private String emailPrincipal;
	private String emailAlterno;
	private String lugarResidencia;
	private String contrasena;
	private String modalidad;
	private Date fechaInicio;
	private Date fechaFin;
	private Set<NumeroFicha> numeroFichas = new HashSet<NumeroFicha>(0);
	private Set<ConfirmarEvento> confirmarEventos = new HashSet<ConfirmarEvento>(0);

	public Egresado() {
	}

	public Egresado(long idEgresado, String tipoDocumento, String nombres, String apellidos, String emailPrincipal) {
		this.idEgresado = idEgresado;
		this.tipoDocumento = tipoDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.emailPrincipal = emailPrincipal;
	}
	
	public Egresado(long idEgresado, String tipoDocumento, String nombres, String apellidos, String emailPrincipal,
			String telefonoPrincipal) {
		
		this.idEgresado = idEgresado;
		this.tipoDocumento = tipoDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.emailPrincipal = emailPrincipal;
		this.telefonoPrincipal=telefonoPrincipal;
	}
	public Egresado(long idEgresado, String tipoDocumento, String nombres, String apellidos, String emailPrincipal,
			String telefonoPrincipal,String contrasena) {
		
		this.idEgresado = idEgresado;
		this.tipoDocumento = tipoDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.emailPrincipal = emailPrincipal;
		this.telefonoPrincipal=telefonoPrincipal;
		this.contrasena = contrasena;
	}

	public Egresado(long idEgresado, String tipoDocumento, String nombres, String apellidos, String telefonoPrincipal,
			String telefonoAlterno, String emailPrincipal, String emailAlterno, String lugarResidencia,
			String contrasena, String modalidad, Date fechaInicio, Date fechaFin, Set<NumeroFicha> numeroFichas,
			Set<ConfirmarEvento> confirmarEventos) {
		
		this.idEgresado = idEgresado;
		this.tipoDocumento = tipoDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefonoPrincipal = telefonoPrincipal;
		this.telefonoAlterno = telefonoAlterno;
		this.emailPrincipal = emailPrincipal;
		this.emailAlterno = emailAlterno;
		this.lugarResidencia = lugarResidencia;
		this.contrasena = contrasena;
		this.modalidad = modalidad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.numeroFichas = numeroFichas;
		this.confirmarEventos = confirmarEventos;
	}

	@Id

	@Column(name = "idEgresado", unique = true, nullable = false)
	public long getIdEgresado() {
		return this.idEgresado;
	}

	public void setIdEgresado(long idEgresado) {
		this.idEgresado = idEgresado;
	}

	@Column(name = "tipo_documento", nullable = false, length = 45)
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "nombres", nullable = false, length = 45)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "apellidos", nullable = false, length = 45)
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "telefono_principal", length = 45)
	public String getTelefonoPrincipal() {
		return this.telefonoPrincipal;
	}

	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}

	@Column(name = "telefono_alterno", length = 45)
	public String getTelefonoAlterno() {
		return this.telefonoAlterno;
	}

	public void setTelefonoAlterno(String telefonoAlterno) {
		this.telefonoAlterno = telefonoAlterno;
	}

	@Column(name = "email_principal", nullable = false, length = 45)
	public String getEmailPrincipal() {
		return this.emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	@Column(name = "email_alterno", length = 45)
	public String getEmailAlterno() {
		return this.emailAlterno;
	}

	public void setEmailAlterno(String emailAlterno) {
		this.emailAlterno = emailAlterno;
	}

	@Column(name = "lugar_residencia", length = 45)
	public String getLugarResidencia() {
		return this.lugarResidencia;
	}

	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}

	@Column(name = "contrasena", length = 45)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name = "modalidad", length = 45)
	public String getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "egresado")
	public Set<NumeroFicha> getNumeroFichas() {
		return this.numeroFichas;
	}

	public void setNumeroFichas(Set<NumeroFicha> numeroFichas) {
		this.numeroFichas = numeroFichas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "egresado")
	public Set<ConfirmarEvento> getConfirmarEventos() {
		return this.confirmarEventos;
	}

	public void setConfirmarEventos(Set<ConfirmarEvento> confirmarEventos) {
		this.confirmarEventos = confirmarEventos;
	}

}
