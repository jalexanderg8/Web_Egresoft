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
    // Esta clase es solo para pruebas con excel ***************No realiza ninguna funcionalida en la pagina
public class LeerExcel {

	Workbook wb;
	private  TipoTitulacion tipoTitulacion;
	private TipoTitulacion tipoTitulacion2;
	private  ProgramaFormacion programaFormacion;
	private  NumeroFicha numeroFicha;
	private NumeroFichaEgresado numeroFichaEgresado;
	private  Egresado egresado;
	private  String titulacion;
	private  String nombreFormacion;
	private  String fichaFormacion;
	private EgresadoDao daoEgresado;
    private TipoTitulacionDao tipoTitulacionDao;
	private ProgramaDao programaDao;
	private NumeroFichaDao numeroFichaDao;
	private NumerofichaEgresadoDao numerofichaEgresadoDao;
	
	
	
	
	

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public String getNombreFormacion() {
		return nombreFormacion;
	}

	public void setNombreFormacion(String nombreFormacion) {
		this.nombreFormacion = nombreFormacion;
	}

	public String getFichaFormacion() {
		return fichaFormacion;
	}

	public void setFichaFormacion(String fichaFormacion) {
		this.fichaFormacion = fichaFormacion;
	}

	private String ruta="C:\\ProyectoFinal\\Base de Datos\\Reporte de Aprendices Ficha 1132273 PRUEBA - copia - copia.xls";
	private MessagesView msj = new MessagesView();
	
	
	// public String Importar(File archivo, JTable tablaD){

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
						
									
									
									try{
										
										tipoTitulacion2=tipoTitulacionDao.buscarTipoTitulacion(titulacion);
										if(tipoTitulacion2==null){
											
											tipoTitulacion=new TipoTitulacion(titulacion);
											
											if(tipoTitulacionDao.registrar(tipoTitulacion)){
												
												System.out.println("Registro tipo de titulacion ");
												
												
												programaFormacion=new ProgramaFormacion(tipoTitulacion, nombre);     
												programaDao=new ProgramaDao();
																				
												if(programaDao.registrar(programaFormacion)){
													
													System.out.println("registro nombre Formacion");
													
												}else{
													
													System.out.println("No registro nombre Formacion");
												}
												
											}else{
												
												System.out.println("no registro tipo titulacion");
												
											}
										}else{
																						
												
												programaFormacion=new ProgramaFormacion(tipoTitulacion2, nombre);     
												programaDao=new ProgramaDao();
																				
												if(programaDao.registrar(programaFormacion)){
													
													System.out.println("registro nombre Formacion");
													
												}else{
													
													System.out.println("No registro nombre Formacion");
												}
												
											}										
										
										
										
										numeroFichaDao=new NumeroFichaDao();
										numeroFicha=new NumeroFicha(programaFormacion, fichaFormacion);
										
										if(numeroFichaDao.registrar(numeroFicha)){
											
											
											System.out.println("registro numero de ficha");
										}else{
											
											System.out.println("No registro numero de ficha");
										}
										
									}catch (Exception e) {
										
										
										System.out.println(e.getMessage());
										System.out.println("Excepcion de registro ");
										
									}
									nivelFormacion=false;
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
					numerofichaEgresadoDao=new NumerofichaEgresadoDao();
					
					
					
					try{
						
						
						
						if(daoEgresado.registrar(egresado)){
							
					
							System.out.println("estamos en registrar egresado ");
						
							//msj.info("Egresado registrado satisfactoriamente");
							
							numeroFichaEgresado=new NumeroFichaEgresado(egresado, numeroFicha);
							
							if(numerofichaEgresadoDao.registrar(numeroFichaEgresado)){
								
								
								System.out.println("registro ficha y relacion con el egresado ");
							}else{
								
								System.out.println("No registro numero de ficha");
							}
							
							
						}else{
							
							
							System.out.println("No se pudo registrar egresado ");
							
							//msj.error("No se pudo registrar al egresado");
							
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
			
			try{
				
				
				if (fil != null)
					System.out.println("cerro el archivo");
					fil.close();
				
			}catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
			
		}
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



	public Egresado getEgresado() {
		return egresado;
	}

	public void setEgresado(Egresado egresado) {
		this.egresado = egresado;
	}

	public TipoTitulacion getTipoTitulacion2() {
		return tipoTitulacion2;
	}

	public void setTipoTitulacion2(TipoTitulacion tipoTitulacion2) {
		this.tipoTitulacion2 = tipoTitulacion2;
	}

	
}
