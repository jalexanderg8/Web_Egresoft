package leerExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entidades.Egresado;

public class LeerExcel {

	Workbook wb;

	// public String Importar(File archivo, JTable tablaD){

	public String Importar() {
		String respuesta = "No se pudo realizar la importación.";
		// DefaultTableModel modeloT = new DefaultTableModel();
		// tablaD.setModel(modeloT);
		// tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		HashMap<Long, Egresado> mapaEgresados = new HashMap<Long, Egresado>();
		try {
			FileInputStream file = new FileInputStream(
					new File("C:\\ProyectoFinal\\Base de Datos\\Reporte de Aprendices Ficha 1132273 PRUEBA.xls"));

			// wb = WorkbookFactory.create(new
			// FileInputStream(archivo));//CREAMOS UNA REPRESENTACIÓN DE HOJA
			// EXCEL
			wb = WorkbookFactory.create(file);// CREAMOS UNA REPRESENTACIÓN DE
												// HOJA EXCEL

			Sheet hoja = wb.getSheetAt(0);// SELECCIONAMOS LA HOJA DE LA
											// POSICIÓN -> 0 DEL LIBRO
			// Row row = hoja.getRow(13);

			Iterator filaIterator = hoja.rowIterator();
			int indiceFila = -1;
			//int indiceColumna = -1;
			String valorCelda = "";
			int contador = 0;

			boolean empezarLeer = false;
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

						}
						if (empezarLeer) {
								
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
								}
							}
						}
						
					}
					
				}
				// if(indiceFila!=0)modeloT.addRow(listaColumna);

				System.out.println("Estos son los datos del egresado en el mapa: "+egresado.toString());
				if (empezarLeer) {
					mapaEgresados.put(egresado.getIdEgresado(), egresado);
				}

				if (valorCelda.equals("Estado")) {

					empezarLeer = true;
					contador++;
					
				}
				if(contador==7){
					contador=1;
				}
				
			}

			respuesta = "Importación exitosa";

			// Recorremos el hashMap y mostramos por pantalla el par valor y
			// clave
			Iterator it = mapaEgresados.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				System.out.println(e.getKey() + " " + e.getValue().toString());

				System.out.println("tamaño del mapa: " + mapaEgresados.size());
			}

		} catch (

		FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}

	public static void main(String args[]) throws IOException {

		LeerExcel leerExcel = new LeerExcel();

		leerExcel.Importar();
	}

}
