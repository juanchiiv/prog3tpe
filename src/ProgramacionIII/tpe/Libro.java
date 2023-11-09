package ProgramacionIII.tpe;

import java.time.Year;

public class Libro {
	private String ID;
	private String titulo;
	private String autor; 
	private Integer paginas;
	private Integer anio; 
	private Double ejemplares; 
	private String genero;
	
	private Integer anioActual;
	
	public Libro(String ID, String titulo, String autor, Integer paginas, Integer anio, Double ejemplares, String genero) {
		this.ID = ID;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.anio = anio;
		this.ejemplares = ejemplares;
		this.genero = genero;
		
		this.anioActual = Year.now().getValue();
	}

	public String getID() {
		return ID;
	}

	public String getAutor() {
		return autor;
	}
	
	public String getGenero() {
		return genero;
	}

	public Integer getAnio() {
		return anio;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public Integer getPaginas() {
		return paginas;
	}
	
	public double getCostoPublicacion(double factor) {
		//costo_publicación: #páginas * 1.5 + (<año_actual>-<año_publicacion> * factor)
		return (paginas * 1.5 + ((anioActual - anio) * factor));
	}
	
	public double getIngresosVenta() {
		//ingresos_ventas: #ejemplares_vendidos * 0.9 + (<año_actual>-<año_publicacion> * 1.2)
		return (ejemplares * 0.9 + ((anioActual - anio) * 1.2));
	}
	
	public Integer getAnioActual() {
		return anioActual;
	}

	public double getEjemplares() {
		// TODO Auto-generated method stub
		return ejemplares;
	}
	
	
}
