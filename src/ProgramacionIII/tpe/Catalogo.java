package ProgramacionIII.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Catalogo {
	private HashMap<String, Libro> librosPorID = new HashMap<>();
	private HashMap<String, List<Libro>> librosPorAutor = new HashMap<>();
	private HashMap<String, List<Libro>> librosPorGenero = new HashMap<>();
	private HashMap<Integer, List<Libro>> librosPorAnio = new HashMap<>();
	
	private List<Libro> listaGreedy = new ArrayList<>();
	private List<Libro> listaBack = new ArrayList<>();
	
	public Catalogo() {
		
	}

	//ADD DE LIBROS POR FILTRO
	public void add(Libro libro) {
		String ID = libro.getID();
		String autor = libro.getAutor();
		String genero = libro.getGenero();
		Integer anio = libro.getAnio();
        librosPorID.put(ID, libro);
        addLibrosPorAutor(autor, libro);
        addLibrosPorGenero(genero, libro);
        addLibrosPorAnio(anio, libro);
    }
	
	private void addLibrosPorAutor(String autor, Libro libro) {
		if (!librosPorAutor.containsKey(autor)) {
			librosPorAutor.put(autor, new ArrayList<>());
        }
		librosPorAutor.get(autor).add(libro);
	}
	
	private void addLibrosPorGenero(String genero, Libro libro) {
		if (!librosPorGenero.containsKey(genero)) {
			librosPorGenero.put(genero, new ArrayList<>());
		}
		librosPorGenero.get(genero).add(libro);
	}
	
	private void addLibrosPorAnio(Integer anio, Libro libro) {
		if (!librosPorAnio.containsKey(anio)) {
			librosPorAnio.put(anio, new ArrayList<>());
        }
		librosPorAnio.get(anio).add(libro);
	}
	/////////////////////////////////////

	// GET DE LIBROS POR FILTRO
	public Libro obtenerLibroPorID(String ID) {
        return librosPorID.get(ID);
    }
	
	public List<Libro> obtenerLibrosPorAutor(String autor) {
        return librosPorAutor.get(autor);
    }
	
	public List<Libro> obtenerLibrosPorGenero(String genero) {
		return librosPorGenero.get(genero);
	}
	
	public List<Libro> obtenerLibrosEnRangoDeAnios(int anioInicio, int anioFin) {
        List<Libro> librosEnRango = new ArrayList<>();
        for (int anio = anioInicio; anio <= anioFin; anio++) {
            if (librosPorAnio.containsKey(anio)) {
                librosEnRango.addAll(librosPorAnio.get(anio));
            }
        }
        return librosEnRango;
    }
	
	///////////////////////////////////////
	
	public Libro seleccionarMejor(List<Libro> libros) {
		double ingresoVenta = 0;
		double ingresoVenta_anterior = 0;
		Libro mejor = null;
		for (Libro L : libros) {
			ingresoVenta = L.getIngresosVenta();
			if(ingresoVenta > ingresoVenta_anterior) {
				mejor = L;
				ingresoVenta_anterior = ingresoVenta;
			}
		}
		return mejor;
	}
	
	public double calcularGananciaTotal(List<Libro> libros) {
		List<String> autores = getAutores(libros);
		double ganancia_total = 0;
		for(Libro L : libros) {
			if(seRepite(autores, L.getAutor())) {
				ganancia_total += L.getIngresosVenta() - L.getCostoPublicacion(0.6);
			}
			else {
				ganancia_total += L.getIngresosVenta() - L.getCostoPublicacion(1.8);
			}
		}
		return ganancia_total;
	}
	
	private List<String> getAutores(List<Libro> libros){
		List<String> lista = new ArrayList<>();
		for(Libro l: libros) {
			lista.add(l.getAutor());
		}
		return lista;
	}
	
	private Boolean seRepite(List<String> lista, String autor) {
		int cont = 0;
		for(int i = 0; i < lista.size(); i++) {
			if(autor.equals(lista.get(i))) {
				cont++;
			}
		}
		if(cont > 1) {
			return true;
		}else {
			return false;
		}
	}
	
	///////////////////////////////////////////////////////////////
	
	//GREEDY
	
	public List<Libro> greedy() {
		greedyMejorPorGenero(librosPorGenero);
		calcularGananciaTotal(listaGreedy);
		return listaGreedy;
	}
	
	private void greedyMejorPorGenero(HashMap<String, List<Libro>> librosPorGenero) {
		listaGreedy.clear();
		for(List<Libro> libros : librosPorGenero.values()) {
			Libro mejor = seleccionarMejor(libros);
			listaGreedy.add(mejor);
		}
	}
	
	////////////////////////////////////////////////////////////////
	
	//BACKTRACKING
	
	public List<Libro> backtracking() {
		List<Libro> solucionParcial = new ArrayList<>();
		Set<String> generos = new HashSet<>(librosPorGenero.keySet());
		backtrackingMejorPorGenero(librosPorGenero, generos, solucionParcial);
		return listaBack;
	}
	
	private void backtrackingMejorPorGenero(HashMap<String, List<Libro>> librosPorGenero, Set<String> generos, List<Libro> solucionParcial) {
	    if (generos.isEmpty()) {
	        if (calcularGananciaTotal(solucionParcial) > calcularGananciaTotal(listaBack)) {
	        	listaBack = new ArrayList(solucionParcial);
	        }
	    } 
	    else {
	        String generoActual = generos.iterator().next();
	        generos.remove(generoActual);
	        
	        List<Libro> librosEnGenero = obtenerLibrosPorGenero(generoActual);

	        for (Libro libro : librosEnGenero) {
	            solucionParcial.add(libro);
	            backtrackingMejorPorGenero(librosPorGenero, generos, solucionParcial);
	            solucionParcial.remove(libro);
	        }
	        generos.add(generoActual);
	    }
	}

}
