package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RutaDTO {
	private Long id;
	private String nombre;
	private String descripcion;
	private String privacidad;
	private String formato;
	private Double distancia;
	private String dificultad;
	private Double tiempo;
	private Date fecha;
	private byte[] recorrido;
	private List<byte[]> fotos;
	private UsuarioDTO creador;
	private List<CalificacionDTO> calificaciones;
	private Float puntaje;
	private Integer usuarios;
	private List<NotaDTO> notas;
	private ActividadDTO actividad;
	private Boolean isRecorrida;

	public RutaDTO() {
		super();
	}

	public RutaDTO(Long id, String nombre, String descripcion, String privacidad, byte[] recorrido,
			List<byte[]> fotos, String formato,
			Double distancia, String dificultad, Double tiempo, Date fecha, UsuarioDTO creador,
			ActividadDTO actividad, Float puntaje, Integer usuarios) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.privacidad = privacidad;
		this.formato = formato;
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.creador = creador;
		this.actividad = actividad;
		this.puntaje = puntaje;
		this.usuarios = usuarios;
		this.recorrido = recorrido;
		this.fotos = fotos;
		this.calificaciones = new ArrayList<CalificacionDTO>();
		this.notas = new ArrayList<NotaDTO>();
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

	public String getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<CalificacionDTO> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionDTO> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<NotaDTO> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaDTO> notas) {
		this.notas = notas;
	}

	public UsuarioDTO getCreador() {
		return creador;
	}

	public void setCreador(UsuarioDTO creador) {
		this.creador = creador;
	}
	
	public byte[] getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(byte[] recorrido) {
		this.recorrido = recorrido;
	}

	public List<byte[]> getFotos() {
		return fotos;
	}

	public void setFotos(List<byte[]> fotos) {
		this.fotos = fotos;
	}

	public ActividadDTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadDTO actividad) {
		this.actividad = actividad;
	}

	public Float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Float puntaje) {
		this.puntaje = puntaje;
	}

	public Integer getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Integer usuarios) {
		this.usuarios = usuarios;
	}

	public Boolean getIsRecorrida() {
		return isRecorrida;
	}

	public void setIsRecorrida(Boolean isRecorrida) {
		this.isRecorrida = isRecorrida;
	}

}