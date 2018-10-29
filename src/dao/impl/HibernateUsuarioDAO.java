package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.FactoryDAO;
import dao.bi.BIUsuarioDAO;
import dto.FactoryDTO;
import dto.UsuarioDTO;
import model.Rol;
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
}