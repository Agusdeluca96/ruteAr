package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.*;

public class RutaDTO {
	private Long id;
	private String nombre;
	private String descripcion;
	//private Enum<Privacidad> privacidad;
	private String privacidad;
	private String recorrido;
	//private Enum<Formato> formato;
	private String formato;
	private Double distancia;
	//private Enum<Dificultad> dificultad;
	private String dificultad;
	private Double tiempo;
	private Date fecha;
	private List<Foto> fotos;
	private Usuario creador;
	private List<Calificacion> calificaciones;
	private Float calificacionPromedio;
	private Integer cantUsuariosRecorrieron;
	private List<Nota> notas;
	private Actividad actividad;

	public RutaDTO() {
		super();
	}

	public RutaDTO(Long id, String nombre, String descripcion, String privacidad, String recorrido,
			String formato, Double distancia, String dificultad, Double tiempo, Date fecha,
			List<Foto> fotos, Usuario creador, Actividad actividad, Float calificacionPromedio, Integer cantUsuariosRecorrieron) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.privacidad = privacidad;
		this.recorrido = recorrido;
		this.formato = formato;
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.fotos = fotos;
		this.creador = creador;
		this.actividad = actividad;
		this.calificacionPromedio = calificacionPromedio;
		this.cantUsuariosRecorrieron = cantUsuariosRecorrieron;
		this.calificaciones = new ArrayList<Calificacion>();
		this.notas = new ArrayList<Nota>();
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

	public String getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
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

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public Float getCalificacion() {
		Iterator<Calificacion> it = this.calificaciones.iterator();
		int total = 0;
		while (it.hasNext()) {
			total += it.next().getValor();
		}
		return (float) 1;
		// return (float) (total / this.calificaciones.size());
	}

	public void addCalificacion(Calificacion calificacion) {
		this.calificaciones.add(calificacion);
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public void addNota(Nota nota) {
		this.notas.add(nota);
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Float getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(Float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}

	
	
}