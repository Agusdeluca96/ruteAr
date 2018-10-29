package model;

import javax.persistence.*;

@Entity(name = "nota")
public class Nota {
	@Id
	@GeneratedValue
	@Column(name = "nota_id")
	private Long id;
	private Enum<Categoria> categoria;
	private String descripcion;
	@ManyToOne(optional = false)
	@JoinColumn(name="usuario_id")
	private Usuario autor;

	public Nota() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enum<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(Enum<Categoria> categoria) {
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
