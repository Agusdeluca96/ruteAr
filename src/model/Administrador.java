package model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Administrador extends Rol {

	public Administrador() {
		super();
	}

	@JsonCreator
	public Administrador(@JsonProperty("description") String descrip) {
		super(descrip);
	}
}
