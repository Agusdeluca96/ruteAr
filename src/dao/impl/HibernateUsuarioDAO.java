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

	@Override
	public List<UsuarioDTO> listAllIncomplete() {
		return FactoryDTO.getFactoryDTO().convertToUsuarioArrayListDTO(super.listAll(), false);
	}

	@Override
	public UsuarioDTO findIncomplete(Long id) {
		return FactoryDTO.getFactoryDTO().convertToUsuarioDTO((Usuario) super.find(id), false);
	}

	@Override
	public boolean isCreated(UsuarioDTO usuario) {
		Query q = this.getEntityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario");
		q.setParameter("usuario", usuario.getUsuario());
		return (!q.getResultList().isEmpty());
	}

	public void create(UsuarioDTO usuarioDTO) {
		Usuario usuario = FactoryDTO.getFactoryDTO().convertToUsuario(usuarioDTO,
				(Rol) FactoryDAO.getFactoryDAO().getRolDAO().find((long) 1));
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
}
