package model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Basico extends Rol {

	public Basico() {
		super();
	}

	@JsonCreator
	public Basico(@JsonProperty("description") String descrip) {
		super(descrip);
	}

}
