package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "actividad")
public class Actividad {
	@Id
	@GeneratedValue
	@Column(name = "actividad_id")
	private Long id;
	private String nombre;
	private String descripcion;

	public Actividad() {
		super();
	}

	public Actividad(String nombre, String descrip) {
		this.nombre = nombre;
		this.descripcion = descrip;
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
