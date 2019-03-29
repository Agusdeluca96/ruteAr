package dao.bi;

import java.util.List;

import dto.RutaDTO;
import dto.UsuarioDTO;
import model.Usuario;

public interface BIUsuarioDAO extends BIGenericDAO<Usuario> {

	public abstract Usuario getByUsername(String username);

	public abstract List<UsuarioDTO> listAllIncomplete();

	public abstract UsuarioDTO findIncomplete(Long id);

	public abstract UsuarioDTO findComplete(Long id);

	public abstract boolean isCreated(UsuarioDTO usuario);

	public abstract void create(UsuarioDTO usuario);

	public abstract void update(UsuarioDTO usuario, Long id);

	public abstract UsuarioDTO habilitar(Long id);

	public abstract UsuarioDTO deshabilitar(Long id);

	public abstract UsuarioDTO findByUsuario(String usuario);

	public abstract boolean hasKnownRoute(Long id, Long idRuta);

	public abstract void addKnownRoute(Long id, RutaDTO ruta);

	public abstract List<RutaDTO> listAllRoutesToDiscover(UsuarioDTO usuario);
}