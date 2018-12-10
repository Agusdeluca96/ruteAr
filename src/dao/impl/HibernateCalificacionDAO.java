package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.FactoryDAO;
import dao.bi.BICalificacionDAO;
import dto.CalificacionDTO;
import dto.FactoryDTO;
import model.Calificacion;
import model.Usuario;

public class HibernateCalificacionDAO extends HibernateGenericDAO<Calificacion> implements BICalificacionDAO {

	public HibernateCalificacionDAO() {
		super(Calificacion.class);
	}

	public List<CalificacionDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToCalificacionArrayListDTO(super.listAll());
	}

	public CalificacionDTO findComplete(Long id) {
		Calificacion calificacion = (Calificacion) super.find(id);
		if (calificacion != null) {
			return FactoryDTO.getFactoryDTO().convertToCalificacionDTO(calificacion);
		} else {
			return null;
		}
	}

	public boolean isCreated(CalificacionDTO calificacion) {
		return false;
		/*
		 * Query q = this.getEntityManager().
		 * createQuery("SELECT c FROM Calificacion c WHERE c.usuario = :usuario");
		 * q.setParameter("usuario",
		 * FactoryDAO.getFactoryDAO().getUsuarioDAO().find(calificacion.getUsuario().
		 * getId())); return (!q.getResultList().isEmpty());
		 */
	}

	@Override
	public void create(CalificacionDTO calificacionDTO) {
		Usuario usuario = (Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO()
				.find(calificacionDTO.getUsuario().getId());
		calificacionDTO.setUsuario(FactoryDTO.getFactoryDTO().convertToUsuarioDTO(usuario, false));
		Calificacion calificacion = FactoryDTO.getFactoryDTO().convertToCalificacion(calificacionDTO);
		super.create(calificacion);
	}
}