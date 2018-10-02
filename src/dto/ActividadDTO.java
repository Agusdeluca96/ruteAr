package dto;

public class ActividadDTO {
	private Long id;
	private String nombre;
	private String descripcion;

	public ActividadDTO() {
	}

	public ActividadDTO(Long id) {
		super();
		this.id = id;
	}

	public ActividadDTO(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
