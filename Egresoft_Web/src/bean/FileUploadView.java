package bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.EgresadoDao;
import dao.NumeroFichaDao;
import dao.NumerofichaEgresadoDao;
import dao.ProgramaDao;
import dao.TipoTitulacionDao;
import entidades.Egresado;
import entidades.NumeroFicha;
import entidades.NumeroFichaEgresado;
import entidades.ProgramaFormacion;
import entidades.TipoTitulacion;
import mensajes.MessagesView;

@ManagedBean
@RequestScoped
public class FileUploadView {

	String ruta = "C:\\Servidores_de_Aplicaciones\\wildfly-10.1.0.Final\\standalone\\deployments\\Egresoft_Web.war\\resources\\archivosExcel\\";

	Workbook wb;
	private TipoTitulacion tipoTitulacion;
	private TipoTitulacion tipoTitulacion2;
	private ProgramaFormacion programaFormacion;
	private NumeroFicha numeroFicha;
	private NumeroFichaEgresado numeroFichaEgresado;
	private Egresado egresado;
	private String titulacion;
	private String nombreFormacion;
	private String fichaFormacion;
	private EgresadoDao daoEgresado;
	private TipoTitulacionDao tipoTitulacionDao;
	private ProgramaDao programaDao;
	private NumeroFichaDao numeroFichaDao;
	private NumerofichaEgresadoDao numerofichaEgresadoDao;
	// private UploadedFile document;
	private static ArrayList<DocumentModel> documentList = null;
	private String rutaArchivo;
	private UploadedFile file;
	private MessagesView msj = new MessagesView();

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
			obj = new DocumentModel();

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
			rutaArchivo = ruta + nombreArchivo;

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

	public void Importar() {

		// DefaultTableModel modeloT = new DefaultTableModel();
		// tablaD.setModel(modeloT);
		// tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		HashMap<Long, Egresado> mapaEgresados = new HashMap<Long, Egresado>();

		try {

			FileInputStream fil = new FileInputStream(new File(rutaArchivo));

			// wb = WorkbookFactory.create(new
			// FileInputStream(archivo));//CREAMOS UNA REPRESENTACIÓN DE HOJA
			// EXCEL
			wb = WorkbookFactory.create(fil);// CREAMOS UNA REPRESENTACIÓN DE
												// HOJA EXCEL

			Sheet hoja = wb.getSheetAt(0);// SELECCIONAMOS LA HOJA DE LA
											// POSICIÓN -> 0 DEL LIBRO
			// Row row = hoja.getRow(13);

			Iterator filaIterator = hoja.rowIterator();
			int indiceFila = -1;
			// int indiceColumna = -1;
			String valorCelda = "";
			int contador = 0;
			int contador1 = 0;

			boolean egresadoEs = false;
			boolean nivelFormacion = false;
			boolean salir = true;
			while (filaIterator.hasNext()) {
				indiceFila++;

				Egresado egresado = new Egresado();
				Row fila = (Row) filaIterator.next();
				Iterator columnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[1000];
				int indiceColumna = -1;

				while (columnaIterator.hasNext()) {
					indiceColumna++;

					Cell celda = (Cell) columnaIterator.next();
					// if(indiceFila==0){
					// modeloT.addColumn(celda.getStringCellValue());
					// }else{
					if (celda != null) {
						switch (celda.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							// egresado.setTipoDocumento(celda.getStringCellValue());
							// System.out.println(egresado.getTipoDocumento());
							System.out.println("FILA: " + indiceFila + "Columna" + indiceColumna + "valor de la celda"
									+ celda.getStringCellValue());
							valorCelda = celda.getStringCellValue();

							break;
						case Cell.CELL_TYPE_NUMERIC:
							// egresado.setIdEgresado(Long.parseLong(
							// celda.getStringCellValue()));
							// System.out.println(egresado.getIdEgresado());
							System.out.println("FILA: " + indiceFila + "Columna" + indiceColumna + "valor de la celda"
									+ celda.getNumericCellValue());
							break;

						default:

							if (contador == 1) {

								egresadoEs = false;
								salir = false;
							}

						}
						if (egresadoEs) {

							if (celda != null) {

								switch (contador) {

								case (1):
									egresado.setTipoDocumento(celda.getStringCellValue());
									System.out.println(egresado.getTipoDocumento());
									contador++;
									break;

								case (2):

									egresado.setIdEgresado(Long.parseLong(celda.getStringCellValue()));
									System.out.println(egresado.getIdEgresado());
									contador++;
									break;
								case (3):
									egresado.setNombres(celda.getStringCellValue());
									System.out.println(egresado.getNombres());
									contador++;
									break;
								case (4):
									egresado.setApellidos(celda.getStringCellValue());
									System.out.println(egresado.getApellidos());
									contador++;
									break;
								case (5):
									egresado.setTelefonoPrincipal(celda.getStringCellValue());
									System.out.println(egresado.getTelefonoPrincipal());
									contador++;
									break;
								case (6):
									egresado.setEmailPrincipal(celda.getStringCellValue());
									System.out.println(egresado.getEmailPrincipal());
									contador++;
									break;

								default:

									contador = 1;
								}
							}
						}
						if (nivelFormacion) {

							if (celda != null) {

								switch (contador1) {

								case (1):

									String string = celda.getStringCellValue();
									System.out.println(string);
									String[] parts = string.split("-");

									String numero = parts[0].trim();
									String nombre = parts[1].trim();
									String tipo = parts[2].trim();

									System.out.println(tipo);
									System.out.println(nombre);
									System.out.println(numero);

									titulacion = tipo;
									nombreFormacion = nombre;
									fichaFormacion = numero;

									tipoTitulacionDao = new TipoTitulacionDao();

									try {

										tipoTitulacion2 = tipoTitulacionDao.buscarTipoTitulacion(titulacion);
										if (tipoTitulacion2 == null) {

											tipoTitulacion = new TipoTitulacion(titulacion);

											if (tipoTitulacionDao.registrar(tipoTitulacion)) {

												System.out.println("Registro tipo de titulacion ");

												programaFormacion = new ProgramaFormacion(tipoTitulacion, nombre);
												programaDao = new ProgramaDao();

												if (programaDao.registrar(programaFormacion)) {

													System.out.println("registro nombre Formacion");

												} else {

													System.out.println("No registro nombre Formacion");
												}

											} else {

												System.out.println("no registro tipo titulacion");

											}
										} else {

											programaFormacion = new ProgramaFormacion(tipoTitulacion2, nombre);
											programaDao = new ProgramaDao();

											if (programaDao.registrar(programaFormacion)) {

												System.out.println("registro nombre Formacion");

											} else {

												System.out.println("No registro nombre Formacion");
											}

										}

										numeroFichaDao = new NumeroFichaDao();
										numeroFicha = new NumeroFicha(programaFormacion, fichaFormacion);

										if (numeroFichaDao.registrar(numeroFicha)) {

											System.out.println("registro numero de ficha");
										} else {

											System.out.println("No registro numero de ficha");
										}

									} catch (Exception e) {

										System.out.println(e.getMessage());
										System.out.println("Excepcion de registro ");

									}
									nivelFormacion = false;
									break;

								default:
									contador1++;
								}
							}

						}

					}

					// if(indiceFila!=0)modeloT.addRow(listaColumna);

					System.out.println("Estos son los datos del egresado en el mapa: " + egresado.toString());
					if (egresadoEs && valorCelda.equals("EN FORMACION")) {
						mapaEgresados.put(egresado.getIdEgresado(), egresado);

						daoEgresado = new EgresadoDao();
						numerofichaEgresadoDao=new NumerofichaEgresadoDao();

						try {

							if (daoEgresado.registrar(egresado)) {

								System.out.println("estamos en registrar egresado ");

								msj.info("Egresado registrado satisfactoriamente");

								numeroFichaEgresado = new NumeroFichaEgresado(egresado, numeroFicha);

								if (numerofichaEgresadoDao.registrar(numeroFichaEgresado)) {

									System.out.println("registro ficha y relacion con el egresado ");
								} else {

									System.out.println("No registro numero de ficha");
								}

							} else {

								System.out.println("No se pudo registrar egresado ");

								msj.error("No se pudo registrar al egresado");

							}

						} catch (Exception e) {

							System.out.println(e.getMessage());
							System.out.println("No en formacion");

						}

					}
					if (valorCelda.equals("Estado")) {

						egresadoEs = true;
						contador++;

					}
					if (valorCelda.equals("Ficha de Caracterización:")) {

						nivelFormacion = true;

					}

				}

				// Recorremos el hashMap y mostramos por pantalla el par valor y
				// clave
				Iterator it = mapaEgresados.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry e = (Map.Entry) it.next();
					System.out.println(e.getKey() + " " + e.getValue().toString());

					System.out.println("tamaño del mapa: " + mapaEgresados.size());
				}
			}
				try{
				
				
				if (fil != null)
					System.out.println("cerro el archivo");
					fil.close();
				
			}catch (Exception e) {
				
				e.printStackTrace();
			}

		} catch (

		FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}

	}

	public TipoTitulacion getTipoTitulacion2() {
		return tipoTitulacion2;
	}

	public void setTipoTitulacion2(TipoTitulacion tipoTitulacion2) {
		this.tipoTitulacion2 = tipoTitulacion2;
	}

	public NumeroFichaEgresado getNumeroFichaEgresado() {
		return numeroFichaEgresado;
	}

	public void setNumeroFichaEgresado(NumeroFichaEgresado numeroFichaEgresado) {
		this.numeroFichaEgresado = numeroFichaEgresado;
	}

	public String getNombreFormacion() {
		return nombreFormacion;
	}

	public void setNombreFormacion(String nombreFormacion) {
		this.nombreFormacion = nombreFormacion;
	}

	public NumerofichaEgresadoDao getNumerofichaEgresadoDao() {
		return numerofichaEgresadoDao;
	}

	public void setNumerofichaEgresadoDao(NumerofichaEgresadoDao numerofichaEgresadoDao) {
		this.numerofichaEgresadoDao = numerofichaEgresadoDao;
	}

	public ArrayList<DocumentModel> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(ArrayList<DocumentModel> documentList) {
		FileUploadView.documentList = documentList;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Egresado getEgresado() {
		return egresado;
	}

	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}

}
