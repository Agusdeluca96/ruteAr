package dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import dao.FactoryDAO;
import dao.bi.BIRutaDAO;
import dto.CalificacionDTO;
import dto.FactoryDTO;
import dto.RutaDTO;
import dto.UsuarioDTO;
import model.Actividad;
import model.Calificacion;
import model.Rol;
import model.Ruta;
import model.Usuario;

public class HibernateRutaDAO extends HibernateGenericDAO<Ruta> implements BIRutaDAO {

	public HibernateRutaDAO() {
		super(Ruta.class);
	}

	@Override
	public List<RutaDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToRutaArrayListDTO(super.listAll(), true);
	}

	@Override
	public RutaDTO findComplete(Long id) {
		Ruta ruta = (Ruta) super.find(id);
		if (ruta != null) {
			return FactoryDTO.getFactoryDTO().convertToRutaDTO(ruta, true);
		} else {
			return null;
		}
	}

	@Override
	public boolean isCreated(RutaDTO ruta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(RutaDTO rutaDTO, Long id) {
		Ruta ruta = FactoryDTO.getFactoryDTO().convertToRuta(rutaDTO,
				(Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO().find(rutaDTO.getCreador().getId()),
				(Actividad) FactoryDAO.getFactoryDAO().getActividadDAO().find(rutaDTO.getActividad().getId()));
		ruta.setId(id);
		super.update(ruta);

	}

	@Override
	public void create(RutaDTO rutaDTO) {
		Usuario usuario = (Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO().find(rutaDTO.getCreador().getId());
		Actividad actividad = (Actividad) FactoryDAO.getFactoryDAO().getActividadDAO()
				.find(rutaDTO.getActividad().getId());
		Ruta ruta = FactoryDTO.getFactoryDTO().convertToRuta(rutaDTO, usuario, actividad);
		super.create(ruta);

	}

	@Override
	public boolean rateRuta(Long id, CalificacionDTO calificacionDTO) {
		Ruta ruta = (Ruta) super.find(id);
		Usuario usuario = (Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO()
				.find(calificacionDTO.getUsuario().getId());
		if ((ruta != null) && (usuario != null) && (calificacionDTO.getValor() >= 0)
				&& (calificacionDTO.getValor() < 6)) {
			Calificacion calificacion = new Calificacion(calificacionDTO.getValor(), usuario);
			FactoryDAO.getFactoryDAO().getCalificacionDAO().create(calificacion);
			ruta.addCalificacion(calificacion);
			super.update(ruta);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Integer cantUsuariosRecorrieron(Ruta ruta) {
		try {
//			String query = "SELECT u FROM usuario u INNER JOIN u.rutasRecorridas ruta WHERE ruta = :ruta";
			String query = "SELECT u FROM usuario u INNER JOIN u.rutasRecorridas ruta WHERE ruta = :ruta";
			TypedQuery<Usuario> q = this.getEntityManager().createQuery(query, Usuario.class);
			q.setParameter("ruta", ruta);
			Integer cant = q.getResultList().size();
			return cant;
		} catch (NoResultException nre) {
			return null;
		}
	}

}