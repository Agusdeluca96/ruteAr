package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Privacidad {
	@JsonProperty("privada")
	PRIVADA,
	@JsonProperty("publica")
	PUBLICA
}
