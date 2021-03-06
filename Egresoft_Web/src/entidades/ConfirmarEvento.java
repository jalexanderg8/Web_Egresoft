package entidades;
// Generated 3/12/2017 09:41:36 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ConfirmarEvento generated by hbm2java
 */
@Entity
@Table(name = "confirmar_evento", catalog = "egresoft")
public class ConfirmarEvento implements java.io.Serializable {

	private Integer idConfirmarEvento;
	private Egresado egresado;
	private Evento evento;

	public ConfirmarEvento() {
	}

	public ConfirmarEvento(Egresado egresado, Evento evento) {
		this.egresado = egresado;
		this.evento = evento;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idConfirmar_evento", unique = true, nullable = false)
	public Integer getIdConfirmarEvento() {
		return this.idConfirmarEvento;
	}

	public void setIdConfirmarEvento(Integer idConfirmarEvento) {
		this.idConfirmarEvento = idConfirmarEvento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEgresado", nullable = false)
	public Egresado getEgresado() {
		return this.egresado;
	}

	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEvento", nullable = false)
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
