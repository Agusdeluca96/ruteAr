package dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.FactoryDAO;
import dao.bi.BIUsuarioDAO;
import dto.FactoryDTO;
import dto.RutaDTO;
import dto.UsuarioDTO;
import model.Dificultad;
import model.Formato;
import model.Privacidad;
import model.Rol;
import model.Ruta;
import model.Usuario;

public class HibernateUsuarioDAO extends HibernateGenericDAO<Usuario> implements BIUsuarioDAO {

	public HibernateUsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario getByUsername(String username) {
		Query q = this.getEntityManager().createQuery("SELECT u FROM usuario u WHERE u.usuario = :usuario");
		q.setParameter("usuario", username);
		return (Usuario) q.getSingleResult();
	}

	@Override
	public List<UsuarioDTO> listAllIncomplete() {
		return FactoryDTO.getFactoryDTO().convertToUsuarioArrayListDTO(super.listAll(), false);
	}

	@Override
	public UsuarioDTO findIncomplete(Long id) {
		return FactoryDTO.getFactoryDTO().convertToUsuarioDTO((Usuario) super.find(id), false);
	}

	@Override
	public UsuarioDTO findComplete(Long id) {
		return FactoryDTO.getFactoryDTO().convertToUsuarioDTO((Usuario) super.find(id), true);
	}

	@Override
	public boolean isCreated(UsuarioDTO usuario) {
		Query q = this.getEntityManager().createQuery("SELECT u FROM usuario u WHERE u.usuario = :usuario");
		q.setParameter("usuario", usuario.getUsuario());
		return (!q.getResultList().isEmpty());
	}

	public void create(UsuarioDTO usuarioDTO) {
		Rol rol = (Rol) FactoryDAO.getFactoryDAO().getRolDAO().getByDescrip(usuarioDTO.getRol().getDescripcion());
		Usuario usuario = FactoryDTO.getFactoryDTO().convertToUsuario(usuarioDTO, rol);
		super.create(usuario);
	}

	public void update(UsuarioDTO usuarioDTO, Long id) {
		Usuario usuario = FactoryDTO.getFactoryDTO().convertToUsuario(usuarioDTO,
				(Rol) FactoryDAO.getFactoryDAO().getRolDAO().find(usuarioDTO.getRol().getId()));
		usuario.setId(id);
		super.update(usuario);
	}

	public UsuarioDTO habilitar(Long id) {
		Usuario usuario = (Usuario) super.find(id);
		usuario.setHabilitado(true);
		return FactoryDTO.getFactoryDTO().convertToUsuarioDTO(usuario, false);
	}

	public UsuarioDTO deshabilitar(Long id) {
		Usuario usuario = (Usuario) super.find(id);
		usuario.setHabilitado(false);
		return FactoryDTO.getFactoryDTO().convertToUsuarioDTO(usuario, false);
	}

	@Override
	public UsuarioDTO findByUsuario(String usuario) {
		Query q = this.getEntityManager().createQuery("SELECT u FROM usuario u WHERE u.usuario = :usuario");
		q.setParameter("usuario", usuario);
		if (!q.getResultList().isEmpty()) {
			return FactoryDTO.getFactoryDTO().convertToUsuarioDTO((Usuario) q.getSingleResult(), false);
		} else {
			return null;
		}
	}

	@Override
	public boolean hasKnownRoute(Long id, Long idRuta) {
		try {
			Ruta ruta = (Ruta) FactoryDAO.getFactoryDAO().getRutaDAO().find(idRuta);
			String query = "SELECT u FROM usuario u INNER JOIN u.rutasRecorridas ruta WHERE ruta = :ruta and u.id = :id";
			TypedQuery<Usuario> q = this.getEntityManager().createQuery(query, Usuario.class);
			q.setParameter("ruta", ruta);
			q.setParameter("id", id);
			Integer cant = q.getResultList().size();
			return cant > 0;
		} catch (NoResultException nre) {
			return false;
		}
	}

	@Override
	public void addKnownRoute(Long id, RutaDTO rutaDTO) {
		Usuario usuario = (Usuario) this.find(id);
		Ruta ruta = (Ruta) FactoryDAO.getFactoryDAO().getRutaDAO().find(rutaDTO.getId());
		usuario.addRutaRecorrida(ruta);
		this.update(usuario);
	}

	@Override
	public List<RutaDTO> listAllRoutesToDiscover(UsuarioDTO usuarioDTO, String actividad, String formato, String dificultad) {
		Usuario usuario = (Usuario) this.find(usuarioDTO.getId());
		String query = "SELECT DISTINCT r FROM ruta r " 
				+ "LEFT JOIN r.actividad a "
				+ "WHERE r.creador != :usuario "
				+ "AND r.privacidad = :privacidad";
		
		if(actividad != null) {
			query += " AND a.id = :actividadId ";
		}
		
		if(formato != null) {
			query += " AND r.formato = :formato ";
		}
		
		if(dificultad != null) {
			query += " AND r.dificultad = :dificultad ";
		}

		TypedQuery<Ruta> q = this.getEntityManager().createQuery(query, Ruta.class);
		q.setParameter("usuario", usuario);
		q.setParameter("privacidad", Privacidad.PUBLICA);		
		
		if(actividad != null) {
			q.setParameter("actividadId", Long.valueOf(actividad));
		}
		
		if(formato != null) {
			q.setParameter("formato", Formato.valueOf(formato));
		}
		
		if(dificultad != null) {
			q.setParameter("dificultad", Dificultad.valueOf(dificultad));
		}
		
		return FactoryDTO.getFactoryDTO().convertToRutaArrayListDTO(q.getResultList(), true);
	}
}