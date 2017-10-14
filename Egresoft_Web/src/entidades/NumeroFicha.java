package entidades;
// Generated 11/10/2017 03:41:20 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NumeroFicha generated by hbm2java
 */
@Entity
@Table(name = "numero_ficha", catalog = "egresoft")
public class NumeroFicha implements java.io.Serializable {

	private Integer idNumeroFicha;
	private int idprogramaFormacion;
	private String numeroFicha;
	private long idEgresado;

	public NumeroFicha() {
	}

	public NumeroFicha(int idprogramaFormacion, String numeroFicha, long idEgresado) {
		this.idprogramaFormacion = idprogramaFormacion;
		this.numeroFicha = numeroFicha;
		this.idEgresado = idEgresado;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idNumero_ficha", unique = true, nullable = false)
	public Integer getIdNumeroFicha() {
		return this.idNumeroFicha;
	}

	public void setIdNumeroFicha(Integer idNumeroFicha) {
		this.idNumeroFicha = idNumeroFicha;
	}

	@Column(name = "idprograma_formacion", nullable = false)
	public int getIdprogramaFormacion() {
		return this.idprogramaFormacion;
	}

	public void setIdprogramaFormacion(int idprogramaFormacion) {
		this.idprogramaFormacion = idprogramaFormacion;
	}

	@Column(name = "numero_ficha", nullable = false, length = 45)
	public String getNumeroFicha() {
		return this.numeroFicha;
	}

	public void setNumeroFicha(String numeroFicha) {
		this.numeroFicha = numeroFicha;
	}

	@Column(name = "idEgresado", nullable = false)
	public long getIdEgresado() {
		return this.idEgresado;
	}

	public void setIdEgresado(long idEgresado) {
		this.idEgresado = idEgresado;
	}

}
