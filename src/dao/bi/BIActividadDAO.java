package dao.bi;

import java.util.List;

import dto.ActividadDTO;
import model.Actividad;

public interface BIActividadDAO extends BIGenericDAO<Actividad> {

	public abstract Actividad getByDescrip(String descrip);

	public abstract boolean isCreated(ActividadDTO actividad);

	public abstract List<ActividadDTO> listAllComplete();
	
	public abstract ActividadDTO findComplete(Long id);

	public abstract void create(ActividadDTO actividad);

	public abstract void update(ActividadDTO actividad, Long id);
}