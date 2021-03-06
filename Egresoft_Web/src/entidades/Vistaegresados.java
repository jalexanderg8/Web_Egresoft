package entidades;
// Generated 3/12/2017 09:41:36 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Vistaegresados generated by hbm2java
 */
@Entity
@Table(name = "vistaegresados", catalog = "egresoft")
public class Vistaegresados implements java.io.Serializable {

	private VistaegresadosId id;

	public Vistaegresados() {
	}

	public Vistaegresados(VistaegresadosId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idEgresado", column = @Column(name = "idEgresado", nullable = false)),
			@AttributeOverride(name = "tipoDocumento", column = @Column(name = "tipo_documento", nullable = false, length = 45)),
			@AttributeOverride(name = "nombres", column = @Column(name = "nombres", nullable = false, length = 45)),
			@AttributeOverride(name = "apellidos", column = @Column(name = "apellidos", nullable = false, length = 45)),
			@AttributeOverride(name = "telefonoPrincipal", column = @Column(name = "telefono_principal", length = 45)),
			@AttributeOverride(name = "telefonoAlterno", column = @Column(name = "telefono_alterno", length = 45)),
			@AttributeOverride(name = "emailPrincipal", column = @Column(name = "email_principal", nullable = false, length = 45)),
			@AttributeOverride(name = "emailAlterno", column = @Column(name = "email_alterno", length = 45)),
			@AttributeOverride(name = "lugarResidencia", column = @Column(name = "lugar_residencia", length = 45)),
			@AttributeOverride(name = "contrasena", column = @Column(name = "contrasena", length = 45)),
			@AttributeOverride(name = "modalidad", column = @Column(name = "modalidad", length = 45)),
			@AttributeOverride(name = "fechaInicio", column = @Column(name = "fecha_inicio", length = 10)),
			@AttributeOverride(name = "fechaFin", column = @Column(name = "fecha_fin", length = 10)),
			@AttributeOverride(name = "numeroDeFicha", column = @Column(name = "numero_de_ficha", nullable = false, length = 45)),
			@AttributeOverride(name = "nombreFormacion", column = @Column(name = "nombre_formacion", nullable = false, length = 105)),
			@AttributeOverride(name = "tipoTitulacion", column = @Column(name = "tipo_titulacion", nullable = false, length = 45)) })
	public VistaegresadosId getId() {
		return this.id;
	}

	public void setId(VistaegresadosId id) {
		this.id = id;
	}

}
