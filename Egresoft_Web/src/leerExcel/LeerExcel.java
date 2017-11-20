package leerExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
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
import org.primefaces.model.UploadedFile;

import bean.EgresadoBean;
import dao.EgresadoDao;
import dao.NumeroFichaDao;
import dao.ProgramaDao;
import dao.TipoTitulacionDao;
import entidades.Egresado;
import entidades.NumeroFicha;
import entidades.ProgramaFormacion;
import entidades.TipoTitulacion;
import mensajes.MessagesView;

@ManagedBean
@RequestScoped

public class LeerExcel {

	Workbook wb;
	private EgresadoBean egresadoBean;
	Set<ProgramaFormacion> programaFormaciones = null;
	Set<NumeroFicha> numeroFichas = null;
	private static TipoTitulacion tipoTitulacion;
	private static ProgramaFormacion programaFormacion;
	private static NumeroFicha numeroFicha;
	EgresadoDao daoEgresado;
    TipoTitulacionDao tipoTitulacionDao;
	ProgramaDao programaDao;
	NumeroFichaDao numeroFichaDao;
	Egresado egresado;
	private File file;
	private static String titulacion;
	private static String nombreFormacion;
	private static String fichaFormacion;
	

	private String ruta="C:\\ProyectoFinal\\Base de Datos\\Reporte de Aprendices Ficha 1132273 PRUEBA - copia - copia.xls";
	private MessagesView msj = new MessagesView();
	
	
	
	// public String Importar(File archivo, JTable tablaD){
	public static String getNombreFormacion() {
		return nombreFormacion;
	}

	public static void setNombreFormacion(String nombreFormacion) {
		LeerExcel.nombreFormacion = nombreFormacion;
	}


	public static String getTitulacion() {
		return titulacion;
	}

	public static void setTitulacion(String titulacion) {
		LeerExcel.titulacion = titulacion;
	}

	public EgresadoBean getEgresadoBean() {
		return egresadoBean;
	}

	public void setEgresadoBean(EgresadoBean egresadoBean) {
		this.egresadoBean = egresadoBean;
	}
	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}

	public void Importar() {
		
		// DefaultTableModel modeloT = new DefaultTableModel();
		// tablaD.setModel(modeloT);
		// tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		HashMap<Long, Egresado> mapaEgresados = new HashMap<Long, Egresado>();
		
		
		
		try {
			
			FileInputStream fil = new FileInputStream(
					new File(ruta));

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
			//int indiceColumna = -1;
			String valorCelda = "";
			int contador = 0;
			int contador1= 0;

			boolean egresadoEs = false;
			boolean nivelFormacion=false;
			boolean salir=true;
			while (filaIterator.hasNext()&&salir) {
				indiceFila++;
				

				Egresado egresado = new Egresado();
				Row fila = (Row) filaIterator.next();
				Iterator columnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[1000];
				int indiceColumna = -1;

				while (columnaIterator.hasNext()&&salir) {
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
							
							if(contador==1){
								
								egresadoEs=false;
								salir=false;
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
									
									contador=1;
								}
							}
							
						}
						if(nivelFormacion){
							
							if (celda != null) {

								switch (contador1) {
								
								case (1):
									
									String string = celda.getStringCellValue();
								   System.out.println(string);
								   String[] parts = string.split("-");
								   
								  String numero=parts[0].trim();
								  String nombre=parts[1].trim();
								  String tipo=parts[2].trim();
								   								   
									System.out.println(tipo);	
									System.out.println(nombre);	
									System.out.println(numero);	
									
									titulacion=tipo;
									nombreFormacion=nombre;
									fichaFormacion=numero;
									
									tipoTitulacionDao =new TipoTitulacionDao();
									programaDao=new ProgramaDao();
									
									tipoTitulacion=new TipoTitulacion();
									tipoTitulacion.setTipoTitulacion(titulacion);
									
									
									programaFormacion=new ProgramaFormacion();
									programaFormacion.setNombreFormacion(nombreFormacion);
									programaFormacion.setTipoTitulacion(tipoTitulacion);
									
									
									programaFormaciones=new HashSet();
									programaFormaciones.add(programaFormacion);
									
									tipoTitulacion.getProgramaFormacions();
									
									try{
										
										
										
										if(tipoTitulacionDao.registrar(tipoTitulacion)){
											
											System.out.println("Registro tipo de titulacion ");
											
											
											nivelFormacion=false;
											
										}else{
											
											System.out.println("no registro tipo titulacion");
											nivelFormacion=false;
										}
										
										   
										
																		
										if(programaDao.registrar(programaFormacion)){
											
											System.out.println("registro nombre Formacion");
											
										}else{
											
											
											System.out.println("No registro nombre Formacion");
										}
										
										
									}catch (Exception e) {
										
										
										System.out.println(e.getMessage());
										System.out.println("Excepcion de registro ");
										
									}
									
									break;
								
								default:
									contador1++;
								}
						}
						
					}
					
				}
				// if(indiceFila!=0)modeloT.addRow(listaColumna);
					

					System.out.println("Estos son los datos del egresado en el mapa: "+egresado.toString());
					
				if (egresadoEs&&valorCelda.equals("EN FORMACION")) {
					mapaEgresados.put(egresado.getIdEgresado(), egresado);
					
					daoEgresado=new EgresadoDao();
					numeroFichaDao=new NumeroFichaDao();
					
					
					
					
					
					
					numeroFichas=new HashSet();
					numeroFichas.add(numeroFicha);
					
					programaFormacion.getNumeroFichas();
					
					numeroFicha=new NumeroFicha();
					numeroFicha.setEgresado(egresado);
					numeroFicha.setNumeroDeFicha(fichaFormacion);
					numeroFicha.setProgramaFormacion(programaFormacion);
					
					egresado.getNumeroFichas();
						
						
					
					
					
					try{
						
						
						
						if(daoEgresado.registrar(egresado)){
							
					
							System.out.println("estamos en registrar egresado ");
							
							
							msj.info("Egresado registrado satisfactoriamente");
							
						}else{
							
							
							System.out.println("No se pudo registrar egresado ");
							msj.error("No se pudo registrar al egresado");
							
						}
						
						
						
						
						
                         if(numeroFichaDao.registrar(numeroFicha)){
							
							System.out.println("registro numero de ficha ");

						}else {
							
							System.out.println("no registro numero de ficha ");

						}
					
						
					}catch(Exception e){
						
						System.out.println(e.getMessage());
						System.out.println("No en formacion");
						
						
					}
					
				
				}
				if (valorCelda.equals("Estado")) {

					egresadoEs = true;
					contador++;
					
				}
				if(valorCelda.equals("Ficha de Caracterización:")){
					
					nivelFormacion=true;
					
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
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public static void main(String args[]) throws IOException {

		LeerExcel leerExcel = new LeerExcel();

		leerExcel.Importar();
		
	}

	

}
