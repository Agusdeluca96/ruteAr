package dao.bi;

import java.util.List;

import dto.CalificacionDTO;
import model.Calificacion;

public interface BICalificacionDAO extends BIGenericDAO<Calificacion> {

	public abstract List<CalificacionDTO> listAllComplete();

	public abstract CalificacionDTO findComplete(Long id);

	public abstract boolean isCreated(CalificacionDTO calificacion);

	public abstract void create(CalificacionDTO calificacion);

}