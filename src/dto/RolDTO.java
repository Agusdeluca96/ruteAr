package dto;

public class RolDTO {

	private Long id;
	private String descripcion;

	public RolDTO() {
	}

	public RolDTO(Long id) {
		super();
		this.id = id;
	}

	public RolDTO(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
