package model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
			include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@Type(value = Administrador.class, name = "Administrador"),
	@Type(value = Basico.class, name = "Basico")
	})
@Entity(name = "rol")
public abstract class Rol {
	@javax.persistence.Id
	@GeneratedValue
	@Column(name = "rol_id")
	private Long id;
	private String descripcion;

	protected Rol(){
	}
	
	protected Rol(String descrip){
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
