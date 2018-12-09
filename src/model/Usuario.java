package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name = "usuario_id")
	private Long id;
	private String usuario;
	private String contrasena;
	private String apellido;
	private String nombre;
	private String domicilio;
	private Date fechaNacimiento;
	private Enum<Sexo> sexo;
	private String email;
	@ManyToOne(optional = false)
	@JoinColumn(name = "rol_id")
	private Rol rol;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_ruta", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "ruta_id", referencedColumnName = "ruta_id"))
	private List<Ruta> rutasRecorridas;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private List<Ruta> rutasAgregadas;
	private Boolean habilitado;

	public Usuario() {
		super();
	}

	public Usuario(String usuario, String contrasena, String apellido, String nombre, String domicilio,
			Date fechaNacimiento, Enum<Sexo> sexo, String email, Rol rol) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.email = email;
		this.rol = rol;
		this.rutasRecorridas = new ArrayList<Ruta>();
		this.rutasAgregadas = new ArrayList<Ruta>();
		this.habilitado = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Enum<Sexo> getSexo() {
		return sexo;
	}

	public void setSexo(Enum<Sexo> sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Ruta> getRutasRecorridas() {
		return rutasRecorridas;
	}

	public void setRutasRecorridas(List<Ruta> rutasRecorridas) {
		this.rutasRecorridas = rutasRecorridas;
	}

	public void addRutaRecorrida(Ruta ruta) {
		this.rutasRecorridas.add(ruta);
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void addRutaAgregada(Ruta ruta) {
		this.rutasAgregadas.add(ruta);

	}

	public List<Ruta> getRutasAgregadas() {
		return this.rutasAgregadas;
	}
}