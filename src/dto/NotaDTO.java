package dto;

import model.Usuario;

public class NotaDTO {
	private Long id;
	private String categoria;
	private String descripcion;
	private Usuario autor;

	public NotaDTO() {
	}

	public NotaDTO(Long id) {
		super();
		this.id = id;
	}

	public NotaDTO(Long id, String categoria, String descripcion, Usuario autor) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

}
