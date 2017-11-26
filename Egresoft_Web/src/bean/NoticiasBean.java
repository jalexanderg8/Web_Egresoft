package bean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import dao.NoticiasDao;
import entidades.Evento;
import entidades.Noticia;

@ManagedBean
@SessionScoped

public class NoticiasBean {

	NoticiasDao noticiaDao = new NoticiasDao();
	public static ArrayList<Noticia> ListaNoticias;;

	private Noticia ObjNoticia = new Noticia();
	private UploadedFile file;

	public NoticiasBean() {
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Noticia getObjNoticia() {
		return ObjNoticia;
	}

	public void setObjNoticia(Noticia objNoticia) {
		ObjNoticia = objNoticia;
	}

	public ArrayList<Noticia> getListaNoticias() {
		// prepararImagen();
		return ListaNoticias;
	}

	public void setListaNoticias(ArrayList<Noticia> listaNoticias) {
		ListaNoticias = listaNoticias;
	};

	public void guardar() {
		System.out.println("en guardar" + file.getContents());
		System.out.println("en guardar" + file.getFileName());

		try {
			FileOutputStream fos = new FileOutputStream("C:\\xampp\\htdocs\\imgBD\\" + this.file.getFileName());

			fos.write(file.getContents());
			fos.flush();
			fos.close();

			// documentList = initialize();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Archivo cargado: %s ", this.file), " "));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		noticiaDao.guardarNotiEvento(this.ObjNoticia, this.file.getFileName());

		// contemplar validaciones de las fotos que si hay error muestre algun
		// mensaje
		// consultar();

	}

	public void consultar() {
		System.err.println("en guardar ");
		this.ListaNoticias = new ArrayList<Noticia>();
		System.out.println("la lista antes de consultar esta asi: " + ListaNoticias.size());

		this.ListaNoticias = noticiaDao.consultar(this.ListaNoticias, ObjNoticia);
		System.out.println("la lista despues de consultar quedo asi: " + ListaNoticias.size());

		// Noticia noticia=new Noticia();
		// ObjNoticia.enviarNoticia(this.ListaNoticias);
		// System.out.println("despues de enviar la lista");

	}

	public void eliminar(Noticia noticia) {

		noticiaDao.eliminar(noticia);
		ListaNoticias.remove(noticia);

	}

}
