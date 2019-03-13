package dao.bi;

import java.util.List;

import dto.CalificacionDTO;
import dto.RutaDTO;
import model.Calificacion;
import model.Nota;
import model.Ruta;
import model.Usuario;

public interface BICalificacionDAO extends BIGenericDAO<Calificacion> {

	public abstract List<CalificacionDTO> listAllComplete();

	public abstract CalificacionDTO findComplete(Long id);

	public abstract boolean isCreated(CalificacionDTO calificacion);

	public abstract Long create(CalificacionDTO calificacion);
	
	public abstract Calificacion getByUserAndRoute(Usuario usuario, Ruta ruta);

}