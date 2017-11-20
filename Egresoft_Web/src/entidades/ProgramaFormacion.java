package entidades;
// Generated 16/11/2017 09:52:27 PM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ProgramaFormacion generated by hbm2java
 */
@Entity
@Table(name = "programa_formacion", catalog = "egresoft", uniqueConstraints = @UniqueConstraint(columnNames = "nombre_formacion"))
public class ProgramaFormacion implements java.io.Serializable {


	private Integer idProgramaFormacion;
    private TipoTitulacion tipoTitulacion;
	private String nombreFormacion;
	private Set<NumeroFicha> numeroFichas = new HashSet<NumeroFicha>(0);

	public ProgramaFormacion() {
	}

	public ProgramaFormacion(TipoTitulacion tipoTitulacion, String nombreFormacion) {
		this.tipoTitulacion = tipoTitulacion;
		this.nombreFormacion = nombreFormacion;
	}

	public ProgramaFormacion(TipoTitulacion tipoTitulacion, String nombreFormacion, Set<NumeroFicha> numeroFichas) {
		this.tipoTitulacion = tipoTitulacion;
		this.nombreFormacion = nombreFormacion;
		this.numeroFichas = numeroFichas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idPrograma_formacion", unique = true, nullable = false)
	public Integer getIdProgramaFormacion() {
		return this.idProgramaFormacion;
	}

	public void setIdProgramaFormacion(Integer idProgramaFormacion) {
		this.idProgramaFormacion = idProgramaFormacion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipo_titulacion", nullable = false)
	public TipoTitulacion getTipoTitulacion() {
		return this.tipoTitulacion;
	}

	public void setTipoTitulacion(TipoTitulacion tipoTitulacion) {
		this.tipoTitulacion = tipoTitulacion;
	}

	@Column(name = "nombre_formacion", unique = false, nullable = false, length = 55)
	public String getNombreFormacion() {
		return this.nombreFormacion;
	}

	public void setNombreFormacion(String nombreFormacion) {
		this.nombreFormacion = nombreFormacion;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "programaFormacion",cascade=CascadeType.ALL)
	public Set<NumeroFicha> getNumeroFichas() {
		return this.numeroFichas;
	}

	public void setNumeroFichas(Set<NumeroFicha> numeroFichas) {
		this.numeroFichas = numeroFichas;
	}
	@Override
	public String toString() {
		return "ProgramaFormacion [idProgramaFormacion=" + idProgramaFormacion + ", tipoTitulacion=" + tipoTitulacion
				+ ", nombreFormacion=" + nombreFormacion + ", numeroFichas=" + numeroFichas + "]";
	}

}
