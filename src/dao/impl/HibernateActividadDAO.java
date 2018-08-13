package dao.impl;

import model.Actividad;

import java.util.List;

import javax.persistence.Query;

import dao.bi.BIActividadDAO;
import dto.ActividadDTO;
import dto.FactoryDTO;

public class HibernateActividadDAO extends HibernateGenericDAO<Actividad> implements BIActividadDAO {

	public HibernateActividadDAO() {
		super(Actividad.class);
	}

	public Actividad getByDescrip(String descrip) {
		Query q = this.getEntityManager().createQuery("SELECT a FROM Actividad a WHERE a.descripcion = :descrip");
		q.setParameter("descrip", descrip);
		return (Actividad) q.getSingleResult();
	}

	public boolean isCreated(ActividadDTO actividad) {
		Query q = this.getEntityManager().createQuery("SELECT a FROM Actividad a WHERE a.descripcion = :descrip");
		q.setParameter("descrip", actividad.getDescripcion());
		System.out.println(!q.getResultList().isEmpty());
		return (!q.getResultList().isEmpty());
	}

	public List<ActividadDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToActividadArrayListDTO(super.listAll());
	}

	public ActividadDTO findComplete(Long id) {
		Actividad actividad = (Actividad) super.find(id);
		if (actividad != null) {
			return FactoryDTO.getFactoryDTO().convertToActividadDTO(actividad);
		} else {
			return null;
		}
	}

	@Override
	public void create(ActividadDTO actividadDTO) {
		Actividad actividad = FactoryDTO.getFactoryDTO().convertToActividad(actividadDTO);
		super.create(actividad);
	}

	@Override
	public void update(ActividadDTO actividadDTO, Long id) {
		Actividad actividad = FactoryDTO.getFactoryDTO().convertToActividad(actividadDTO);
		actividad.setId(id);
		super.update(actividad);

	}

}