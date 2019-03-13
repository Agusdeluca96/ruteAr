package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@Entity(name = "ruta")
public class Ruta {
	@Id
	@GeneratedValue
	@Column(name = "ruta_id")
	private Long id;
	private String nombre;
	private String descripcion;
	private Enum<Privacidad> privacidad;
	private String recorrido;
	private Enum<Formato> formato;
	private Double distancia;
	private Enum<Dificultad> dificultad;
	private Double tiempo;
	private Date fecha;
	@OneToMany
	@JoinColumn(name = "foto_id")
	private List<Foto> fotos;
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario creador;
	@OneToMany
	@JoinColumn(name = "ruta_id")
	private List<Calificacion> calificaciones;
	@OneToMany
	@JoinColumn(name = "ruta_id")
	private List<Nota> notas;
	@ManyToOne(optional = false)
	@JoinColumn(name = "actividad_id")
	private Actividad actividad;

	public Ruta() {
		super();
	}

	public Ruta(String nombre, String descripcion, Enum<Privacidad> privacidad, String recorrido, Enum<Formato> formato,
			Double distancia, Enum<Dificultad> dificultad, Double tiempo, Date fecha, List<Foto> fotos, Usuario creador,
			Actividad actividad) {
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

	public Enum<Privacidad> getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(Enum<Privacidad> privacidad) {
		this.privacidad = privacidad;
	}

	public String getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}

	public Enum<Formato> getFormato() {
		return formato;
	}

	public void setFormato(Enum<Formato> formato) {
		this.formato = formato;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Enum<Dificultad> getDificultad() {
		return dificultad;
	}

	public void setDificultad(Enum<Dificultad> dificultad) {
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
		return (float) (total / this.calificaciones.size());
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
	
	public boolean isRatedByUser(Usuario usuario) {
		Iterator<Calificacion> it = this.calificaciones.iterator();
		while(it.hasNext()) {
			if(it.next().getUsuario().getId() == usuario.getId()) {
				return true;
			}
		}
		return false;
	}

}
