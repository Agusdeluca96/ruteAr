package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actividad {
	@Id
	@GeneratedValue
	@Column(name = "actividad_id")
	private Long id;
	private String descripcion;

	public Actividad(){
		super();
	}
	
	public Actividad(String descrip){
		this.descripcion = descrip;
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
