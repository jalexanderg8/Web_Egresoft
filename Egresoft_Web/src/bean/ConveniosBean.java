package bean;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import dao.ConveniosDao;
import entidades.Convenio;
import mensajes.MessagesView;



@ManagedBean
@RequestScoped

public class ConveniosBean {

	
	ConveniosDao miConvenioDao=new ConveniosDao();
	public static ArrayList<Convenio> ListaConvenios;;
	private Convenio ObjConvenio=new Convenio();
	private UploadedFile file;	
	private MessagesView msj=new MessagesView();

	public ConveniosBean() {}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public Convenio getObjConvenio() {
		return ObjConvenio;
	}

	public void setObjConvenio(Convenio objConvenio) {
		ObjConvenio = objConvenio;
	}
	
	public  ArrayList<Convenio> getListaConvenios() {
		return ListaConvenios;
	}

	public  void setListaConvenios(ArrayList<Convenio> listaConvenios) {
		ListaConvenios = listaConvenios;
	}

	public void guardar(){
    	System.out.println("en guardar"+file.getContents()); 
    	System.out.println("en guardar"+file.getFileName()); 	
        
    	try {
    		
    		  FileOutputStream fos = new FileOutputStream("C:\\xampp\\htdocs\\imgBD\\"+this.file.getFileName());
    		
			fos.write(file.getContents());
			fos.flush();
			fos.close();

			//documentList = initialize();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Archivo cargado: %s ", this.file), " "));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	miConvenioDao.guardarConvenio(this.ObjConvenio,this.file.getFileName());
    	
    	//contemplar validaciones de las fotos que si hay error muestre algun mensaje
    	//consultar();
    	
    	
    	//return "gestionarConvenios.jsf";
    	msj.info("Convenio registrado satisfactoriamente");   	
    }
    
   
	public  void consultar(){
    	System.err.println("en guardar ");
    	this.ListaConvenios=new ArrayList<Convenio>();
    	System.out.println("la lista antes de consultar esta asi: "+ListaConvenios.size());

    	this.ListaConvenios=miConvenioDao.consultar(this.ListaConvenios,ObjConvenio);
    	System.out.println("la lista despues de consultar quedo asi: "+ListaConvenios.size());
    	
    	//return "convenios.jsf";
    	
    	
    }
   
    public String eliminar(Convenio convenioo){
    	System.out.println("en eliminar convenio");
    	
    miConvenioDao.eliminar(convenioo);	
    	ListaConvenios.remove(convenioo);
    	msj.info("Convenio eliminado satisfactoriamente");
    	
    	
    	return "gestionarConvenios.jsf";
    }

	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}


}
