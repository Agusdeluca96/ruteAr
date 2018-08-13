package dto;

import java.util.Date;
import java.util.List;

public class UsuarioDTO {

	private Long id;
	private String usuario;
	private String contrasena;
	private String apellido;
	private String nombre;
	private String domicilio;
	private Date fechaNacimiento;
	private String sexo;
	private String email;
	private RolDTO rol;
	private List<RutaDTO> rutasRecorridas;
	private Boolean habilitado;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String usuario, String contrasena, String apellido, String nombre, String domicilio,
			Date fechaNacimiento, String sexo, String email, RolDTO rol, Boolean habilitado) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.email = email;
		this.rol = rol;
		this.rutasRecorridas = null;
		this.habilitado = habilitado;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public List<RutaDTO> getRutasRecorridas() {
		return rutasRecorridas;
	}

	public void setRutasRecorridas(List<RutaDTO> rutasRecorridas) {
		this.rutasRecorridas = rutasRecorridas;
	}

}
