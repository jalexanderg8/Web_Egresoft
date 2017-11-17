package entidades;
// Generated 16/11/2017 09:52:27 PM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Evento generated by hbm2java
 */
@Entity
@Table(name = "evento", catalog = "egresoft")
public class Evento implements java.io.Serializable {

	private Integer idEvento;
	private String nombreEvento;
	private String urlRedireccion;
	private String imagenEvento;
	private String descripcionEvento;
	private Set<ConfirmarEvento> confirmarEventos = new HashSet<ConfirmarEvento>(0);

	public Evento() {
	}

	public Evento(String nombreEvento, String urlRedireccion, String imagenEvento, String descripcionEvento) {
		this.nombreEvento = nombreEvento;
		this.urlRedireccion = urlRedireccion;
		this.imagenEvento = imagenEvento;
		this.descripcionEvento = descripcionEvento;
	}

	public Evento(String nombreEvento, String urlRedireccion, String imagenEvento, String descripcionEvento,
			Set<ConfirmarEvento> confirmarEventos) {
		this.nombreEvento = nombreEvento;
		this.urlRedireccion = urlRedireccion;
		this.imagenEvento = imagenEvento;
		this.descripcionEvento = descripcionEvento;
		this.confirmarEventos = confirmarEventos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEvento", unique = true, nullable = false)
	public Integer getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	@Column(name = "nombre_evento", nullable = false, length = 45)
	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	@Column(name = "url_redireccion", nullable = false, length = 45)
	public String getUrlRedireccion() {
		return this.urlRedireccion;
	}

	public void setUrlRedireccion(String urlRedireccion) {
		this.urlRedireccion = urlRedireccion;
	}

	@Column(name = "imagen_evento", nullable = false, length = 45)
	public String getImagenEvento() {
		return this.imagenEvento;
	}

	public void setImagenEvento(String imagenEvento) {
		this.imagenEvento = imagenEvento;
	}

	@Column(name = "descripcion_evento", nullable = false)
	public String getDescripcionEvento() {
		return this.descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
	public Set<ConfirmarEvento> getConfirmarEventos() {
		return this.confirmarEventos;
	}

	public void setConfirmarEventos(Set<ConfirmarEvento> confirmarEventos) {
		this.confirmarEventos = confirmarEventos;
	}

}
