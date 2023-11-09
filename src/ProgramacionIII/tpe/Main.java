package ProgramacionIII.tpe;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		String path = "datasets/biblioteca2.csv";
		CSVReader reader = new CSVReader(path);
		Catalogo catalogo = reader.read();
		
		int i = pedirEntero();

		switch(i) {
		  case 1:
			String ID = pedirID();    
		    imprimirInfo(catalogo.obtenerLibroPorID(ID));
		    break;
		  case 2:
			String autor = pedirAutor();
		    imprimirLista(catalogo.obtenerLibrosPorAutor(autor));
		    break;
		  case 3:
			int[] añosIngresados = pedirCronologia();
			int año1 = añosIngresados[0];
			int año2 = añosIngresados[1];
		    imprimirLista(catalogo.obtenerLibrosEnRangoDeAnios(año1, año2));
		    break;
		  case 4:
			int numero = pedirRecomendacion();
			if(numero == 1) {
				imprimirLista(catalogo.greedy());
				System.out.println(catalogo.calcularGananciaTotal(catalogo.greedy()));
			}
			else {
				imprimirLista(catalogo.backtracking());
				System.out.println(catalogo.calcularGananciaTotal(catalogo.backtracking()));
			}
			break;
		  default:
		    System.out.println("Ingresó un número inválido");
		}
		
		
	}
	
	public static void imprimirLista(List<Libro> libros) {
		for(Libro l : libros) {
			System.out.println(l.getTitulo());
		}
	}
	
	public static void imprimirInfo(Libro libro) {
		System.out.println("ID: " + libro.getID());
		System.out.println("Titulo: " + libro.getTitulo());
		System.out.println("Autor: " + libro.getAutor());
		System.out.println("Paginas: " + libro.getPaginas());
		System.out.println("Año: " + libro.getAnio());
		System.out.println("Ejemplares: " + libro.getEjemplares());
		System.out.println("Genero: " + libro.getGenero());
	}
	
	public static int pedirEntero() {
		int numero = 0;
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese 1 para: Obtener la información completa de un libro dado su identificador único.");
            System.out.println("Ingrese 2 para: Obtener todos los libros existentes en el catálogo para un autor elegido.");
            System.out.println("Ingrese 3 para: Obtener todos los libros publicados entre dos años de publicación dados.");
            System.out.println("Ingrese 4 para: Recomendar listado.");
            numero = new Integer(entrada.readLine());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return numero;
	}
	
	public static String pedirID() {
		String ID = " ";
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el ID de su libro");
            ID = new String(entrada.readLine());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return ID;
	}
	
	public static String pedirAutor() {
		String autor = " ";
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el nombre de su autor");
            autor = new String(entrada.readLine());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return autor;
	}
	
	public static int[] pedirCronologia() {
		int[] años = new int[2];
	    try {
	        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Ingrese el primer año");
	        años[0] = new Integer(entrada.readLine());
	        System.out.println("Ingrese el segundo año");
	        años[1] = new Integer(entrada.readLine());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return años;
	}
	
	public static int pedirRecomendacion() {
		int numero = 0;
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese 1 para: Greedy");
            System.out.println("Ingrese 2 para: Backtracking.");
            numero = new Integer(entrada.readLine());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return numero;
	}

}
