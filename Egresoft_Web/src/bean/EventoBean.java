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
import dao.EventosDao;
import entidades.Convenio;
import entidades.Evento;



@ManagedBean
@RequestScoped

public class EventoBean {

	
     EventosDao miEventoDao=new EventosDao();
	public static ArrayList<Evento> ListaEventos;;

	
    
	private Evento ObjEvento=new Evento();
	private UploadedFile file;	

	public EventoBean() {}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	

	public  ArrayList<Evento> getListaEventos() {
		return ListaEventos;
	}

	public  void setListaEventos(ArrayList<Evento> listaEventos) {
		ListaEventos = listaEventos;
	}

	public Evento getObjEvento() {
		return ObjEvento;
	}

	public void setObjEvento(Evento objEvento) {
		ObjEvento = objEvento;
	}

	public void guardar(){
    	System.out.println("en guardar"+file.getContents()); 
    	System.out.println("en guardar"+file.getFileName()); 
	
        
    	try {
            //FileOutputStream fos = new FileOutputStream("C:\\Git\\.git\\Web_Egresoft\\Egresoft_Web\\WebContent\\resources\\imgBD\\"+this.file.getFileName());
			
    		//FileOutputStream fos = new FileOutputStream("D:\\RepositorioEgresoft\\Web_Egresoft\\Egresoft_Web\\WebContent\\resources\\imgBD\\"+this.file.getFileName());
    		FileOutputStream fos = new FileOutputStream("C:\\Users\\junior\\git\\Web_Egresoft1\\Egresoft_Web\\WebContent\\resources\\imgBD\\"+this.file.getFileName());
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
    	
    	miEventoDao.guardarConvenio(this.ObjEvento,this.file.getFileName());
    	
    	//contemplar validaciones de las fotos que si hay error muestre algun mensaje
    	//consultar();
    	
    	
    	//return "gestionarConvenios.jsf";
    		   	
    }
    
    public  void consultar(){
    	System.err.println("en guardar ");
    	this.ListaEventos=new ArrayList<Evento>();
    	System.out.println("la lista antes de consultar esta asi: "+ListaEventos.size());

    	this.ListaEventos=miEventoDao.consultar(this.ListaEventos,ObjEvento);
    	System.out.println("la lista despues de consultar quedo asi: "+ListaEventos.size());
    	
    	//return "convenios.jsf";

    	
    }
    
    
    public void eliminar(Evento evento){
    	
    	miEventoDao.eliminar(evento);
    	ListaEventos.remove(evento);
    	
    	
    }
    
    
}
