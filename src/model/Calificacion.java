package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "calificacion")
public class Calificacion {
	@Id
	@GeneratedValue
	@Column(name = "calificacion_id")
	private Long id;
	public Integer valor;
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;

	public Calificacion() {
		super();
	}

	public Calificacion(Integer valor, Usuario usuario) {
		super();
		this.valor = valor;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}