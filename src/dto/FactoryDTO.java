package dto;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class FactoryDTO {

	private static FactoryDTO factoryDTO;

	public static FactoryDTO getFactoryDTO() {
		if (factoryDTO == null) {
			factoryDTO = new FactoryDTO();
		}
		return factoryDTO;
	}

	// Metodos para entidad Usuario
	// Convertir de Model en DTO

	public UsuarioDTO convertToUsuarioDTO(Usuario usuario, boolean completo) {
		RolDTO rolDTO = new RolDTO(usuario.getRol().getId(), usuario.getRol().getDescripcion());
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getUsuario(), usuario.getContrasena(),
				usuario.getApellido(), usuario.getNombre(), usuario.getDomicilio(), usuario.getFechaNacimiento(),
				usuario.getSexo().toString(), usuario.getEmail(), rolDTO, usuario.isHabilitado());
		if (completo) {
			usuarioDTO.setRutasRecorridas(this.convertToRutaArrayListDTO(usuario.getRutasRecorridas(), completo));
		}
		return usuarioDTO;
	}

	public List<UsuarioDTO> convertToUsuarioArrayListDTO(List<Usuario> usuarios, boolean completo) {
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(this.convertToUsuarioDTO(usuario, completo));
		}
		return usuariosDTO;
	}

	// Convertir de DTO en Model

	public Usuario convertToUsuario(UsuarioDTO usuarioDTO, Rol rol) {
		Usuario usuario = new Usuario(usuarioDTO.getUsuario(), usuarioDTO.getContrasena(),
				usuarioDTO.getApellido(), usuarioDTO.getNombre(), usuarioDTO.getDomicilio(),
				usuarioDTO.getFechaNacimiento(), Sexo.valueOf(usuarioDTO.getSexo()), usuarioDTO.getEmail(), rol);
		return usuario;
	}

	// Metodos para entidad Ruta
	// Convertir de Model en DTO

	private List<RutaDTO> convertToRutaArrayListDTO(List<Ruta> rutasRecorridas, boolean completo) {
		return new ArrayList<RutaDTO>();
	}

	// Metodos para entidad Actividad
	// Convertir de Model en DTO

	public ActividadDTO convertToActividadDTO(Actividad actividad) {
		ActividadDTO actividadDTO = new ActividadDTO(actividad.getId(), actividad.getDescripcion());
		return actividadDTO;
	}

	public List<ActividadDTO> convertToActividadArrayListDTO(List<Actividad> actividades) {
		List<ActividadDTO> actividadesDTO = new ArrayList<ActividadDTO>();
		for (Actividad actividad : actividades) {
			actividadesDTO.add(this.convertToActividadDTO(actividad));
		}
		return actividadesDTO;
	}

	// Convertir de DTO en Model

	public Actividad convertToActividad(ActividadDTO actividadDTO) {
		Actividad actividad = new Actividad(actividadDTO.getDescripcion());
		return actividad;
	}

}
