package entidades;
// Generated 22/11/2017 02:34:27 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Noticia generated by hbm2java
 */
@Entity
@Table(name = "noticia", catalog = "egresoft")
public class Noticia implements java.io.Serializable {

	private Integer idNoticia;
	private String nombreNoticia;
	private String descripcionNoticia;
	private String imagenNoticia;

	public Noticia() {
	}

	public Noticia(String nombreNoticia, String descripcionNoticia, String imagenNoticia) {
		this.nombreNoticia = nombreNoticia;
		this.descripcionNoticia = descripcionNoticia;
		this.imagenNoticia = imagenNoticia;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idNoticia", unique = true, nullable = false)
	public Integer getIdNoticia() {
		return this.idNoticia;
	}

	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}

	@Column(name = "nombre_noticia", nullable = false, length = 45)
	public String getNombreNoticia() {
		return this.nombreNoticia;
	}

	public void setNombreNoticia(String nombreNoticia) {
		this.nombreNoticia = nombreNoticia;
	}

	@Column(name = "descripcion_noticia", nullable = false, length = 85)
	public String getDescripcionNoticia() {
		return this.descripcionNoticia;
	}

	public void setDescripcionNoticia(String descripcionNoticia) {
		this.descripcionNoticia = descripcionNoticia;
	}

	@Column(name = "imagen_noticia", nullable = false, length = 45)
	public String getImagenNoticia() {
		return this.imagenNoticia;
	}

	public void setImagenNoticia(String imagenNoticia) {
		this.imagenNoticia = imagenNoticia;
	}

}
