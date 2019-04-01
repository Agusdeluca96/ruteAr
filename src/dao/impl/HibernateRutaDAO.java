package dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import dao.FactoryDAO;
import dao.bi.BICalificacionDAO;
import dao.bi.BINotaDAO;
import dao.bi.BIRutaDAO;
import dao.bi.BIUsuarioDAO;
import dao.bi.BIActividadDAO;
import dto.CalificacionDTO;
import dto.FactoryDTO;
import dto.NotaDTO;
import dto.RutaDTO;
import model.Actividad;
import model.Calificacion;
import model.Nota;
import model.Ruta;
import model.Usuario;

public class HibernateRutaDAO extends HibernateGenericDAO<Ruta> implements BIRutaDAO {

	private BINotaDAO notaDAO = FactoryDAO.getFactoryDAO().getNotaDAO();
	private BICalificacionDAO calificacionDAO = FactoryDAO.getFactoryDAO().getCalificacionDAO();
	private BIUsuarioDAO usuarioDAO = FactoryDAO.getFactoryDAO().getUsuarioDAO();
	private BIActividadDAO actividadDAO = FactoryDAO.getFactoryDAO().getActividadDAO();

	public HibernateRutaDAO() {
		super(Ruta.class);
	}

	@Override
	public List<RutaDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToRutaArrayListDTO(this.listAll(), true);
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
				(Usuario) usuarioDAO.find(rutaDTO.getCreador().getId()),
				(Actividad) actividadDAO.find(rutaDTO.getActividad().getId()));
		ruta.setId(id);
		super.update(ruta);

	}

	@Override
	public RutaDTO create(RutaDTO rutaDTO) {
		Usuario usuario = (Usuario) usuarioDAO.find(rutaDTO.getCreador().getId());
		Actividad actividad = (Actividad) actividadDAO.find(rutaDTO.getActividad().getId());
		Ruta ruta = FactoryDTO.getFactoryDTO().convertToRuta(rutaDTO, usuario, actividad);
		ruta = super.create(ruta);
		usuario.addRutaAgregada(ruta);
		return FactoryDTO.getFactoryDTO().convertToRutaDTO(ruta, false);
	}

	@Override
	public boolean rateRuta(Long id, CalificacionDTO calificacionDTO) {
		Ruta ruta = (Ruta) super.find(id);
		Usuario usuario = (Usuario) usuarioDAO.find(calificacionDTO.getUsuario().getId());
		if ((ruta != null) && (usuario != null) && (calificacionDTO.getValor() >= 0)
				&& (calificacionDTO.getValor() < 6)) {
			Calificacion calificacion = new Calificacion(calificacionDTO.getValor(), usuario);
			calificacionDAO.create(calificacion);
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
			String query = "SELECT u FROM usuario u INNER JOIN u.rutasRecorridas ruta WHERE ruta = :ruta";
			TypedQuery<Usuario> q = this.getEntityManager().createQuery(query, Usuario.class);
			q.setParameter("ruta", ruta);
			return q.getResultList().size() + 1; // Le agrego 1 para contar al creador de la ruta, ya que en la query
													// solo cuento los usuarios que la marcaron como recorrida
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public boolean addNotaRuta(Long id, NotaDTO notaDTO) {
		Ruta ruta = (Ruta) this.find(id);
		if (ruta != null) {
			notaDTO = notaDAO.create(notaDTO);
			Nota nota = (Nota) notaDAO.find(notaDTO.getId());
			ruta.addNota(nota);
			this.update(ruta);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean addFotoRuta(Long id, byte[] foto) {
		Ruta ruta = (Ruta) this.find(id);
		if (ruta != null) {
			ruta.addFoto(foto);
			this.update(ruta);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean setRecorridoRuta(Long id, byte[] kml) {
		Ruta ruta = (Ruta) this.find(id);
		if (ruta != null) {
			ruta.setRecorrido(kml);
			this.update(ruta);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void delete(Long id) {
		Ruta ruta = (Ruta) this.find(id);
		super.delete(ruta);
		Usuario usuario = ruta.getCreador();
		usuario.getRutasAgregadas().remove(ruta);
	}

	@Override
	public boolean canDelete(Long id) {
		Ruta ruta = (Ruta) this.find(id);
		if (ruta != null) {		
			if ((ruta.getCalificaciones().isEmpty()) && (ruta.getNotas().isEmpty())
					&& (this.cantUsuariosRecorrieron(ruta) <= 1)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean deleteFoto(Long id, Integer indexFoto) {
		Ruta ruta = (Ruta) this.find(id);
		if (ruta != null) {
			ruta.getFotos().remove(indexFoto);
			super.update(ruta);
			return true;
		} else { 
			return false;
		}
	}

}