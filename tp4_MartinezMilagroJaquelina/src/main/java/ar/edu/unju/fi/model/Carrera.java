package ar.edu.unju.fi.model;

public class Carrera {
	private Integer codigo;
	private String nombre;
	private Integer cantAños;
	private String estado;

	public Carrera() {
		
	}

	public Carrera(Integer codigo, String nombre, Integer cantAños, String estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantAños = cantAños;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantAños() {
		return cantAños;
	}

	public void setCantAños(Integer cantAños) {
		this.cantAños = cantAños;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carrera [codigo=" + codigo + ", nombre=" + nombre + ", cantAños=" + cantAños + ", estado=" + estado
				+ "]";
	}
	
	
}
