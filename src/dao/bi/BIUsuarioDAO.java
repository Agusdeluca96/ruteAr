package dao.bi;

import java.util.List;

import dto.UsuarioDTO;
import model.Usuario;

public interface BIUsuarioDAO extends BIGenericDAO<Usuario> {

	public abstract List<UsuarioDTO> listAllIncomplete();

	public abstract UsuarioDTO findIncomplete(Long id);

	public abstract boolean isCreated(UsuarioDTO usuario);

	public abstract void create(UsuarioDTO usuario);

	public abstract void update(UsuarioDTO usuario, Long id);

	public abstract UsuarioDTO habilitar(Long id);

	public abstract UsuarioDTO deshabilitar(Long id);
}
