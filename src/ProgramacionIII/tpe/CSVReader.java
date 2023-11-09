package ProgramacionIII.tpe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

	private String path;
	
	public CSVReader(String path) {
		this.path = path;
	}
	
	public Catalogo read() {
		Catalogo catalogo = new Catalogo();
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y asi
		ArrayList<String[]> lines = this.readContent();
		
		//Primera linea con la cantidad de libros.
		Integer libros = Integer.parseInt(lines.get(0)[0].trim());
		lines.remove(0);
		
		lines.remove(0); //Borramos la l�nea de t�tulos.
		
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String ID = line[0].trim();
			String titulo = line[1].trim();
			String autor = line[2].trim();
			Integer paginas = Integer.parseInt(line[3].trim());
			Integer anio = Integer.parseInt(line[4].trim());
			Double ejemplares = Double.parseDouble(line[5].trim());
			String genero = line[6].trim();
			
			Libro libro = new Libro(ID, titulo, autor, paginas, anio, ejemplares, genero);
			catalogo.add(libro);
		}
		return catalogo;
		
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}

}
