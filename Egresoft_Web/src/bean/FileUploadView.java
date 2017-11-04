package bean;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class FileUploadView {

	String ruta = "C:\\Servidores_de_Aplicaciones\\wildfly-10.1.0.Final\\standalone\\deployments\\Egresoft_Web.war\\resources\\archivosExcel";

	private String name;
	// private UploadedFile document;
	private static ArrayList<DocumentModel> documentList = null;

	private UploadedFile file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ArrayList<DocumentModel> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(ArrayList<DocumentModel> documentList) {
		FileUploadView.documentList = documentList;
	}

	/// nuevo
	public FileUploadView() {
		try {
			FileUploadView.documentList = initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<DocumentModel> initialize() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = null;

		File directorio = new File(ruta);

		int total = 0;

		String[] arrArchivos = directorio.list();
		total += arrArchivos.length;
		System.out.println("total de archivos es: " + total);

		

		for (int i = 0; i < arrArchivos.length; i++) {
			obj=new DocumentModel();
			
			File tmpFile = new File(directorio.getPath() + "/" + arrArchivos[i]);
			String nombreArchivo = tmpFile.getName();

			obj.setSrNo(i + 1);
			System.out.println("archivo numero : " + i);
			obj.setDocumentName(tmpFile.getName());
			System.out.println("nombre del archivos es: " + tmpFile.getName());
			obj.setDocumentUploadedPath(tmpFile.getPath());
			System.out.println("Ruta del archivos es: " + tmpFile.getPath());
			obj.setRemovable(true);
			list.add(obj);

		}
		return list;
	}



	public String removeRow(DocumentModel row) {
		documentList.remove(row);

		// updates serial no.
		int i = 0;
		for (DocumentModel fl : documentList) {

			i++;
			fl.setSrNo(i);
		}
		borrarArchivo(row);
		removeDoc(row);

		return null;
	}

	public String removeDoc(DocumentModel row) {

		System.out.println("filename to be removed " + row.getDocumentUploadedPath());

		row.setDocumentUploadedPath("");
		row.setUploaded(false);

		return null;
	}

	public void borrarArchivo(DocumentModel row) {

		File directorio = new File(ruta);

		File tmpFile;

		tmpFile = new File(row.getDocumentUploadedPath());
		System.out.println("nombre del archivo: " + tmpFile.getName());

		// borramos el archivo
		if (tmpFile.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");

	}



	public void uploadAttachment(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String filePath = ec.getRealPath(String.format("/resources/archivosExcel/%s", file.getFileName()));
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(file.getContents());
			fos.flush();
			fos.close();

			documentList = initialize();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Archivo cargado: %s ", file.getFileName()), " "));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
