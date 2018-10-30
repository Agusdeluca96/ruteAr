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
		List<RutaDTO> rutasRecorridasDTO = this.convertToRutaArrayListDTO(usuario.getRutasRecorridas(), false);
		List<RutaDTO> rutasAgregadasDTO = this.convertToRutaArrayListDTO(usuario.getRutasAgregadas(), false);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getUsuario(), usuario.getContrasena(),
				usuario.getApellido(), usuario.getNombre(), usuario.getDomicilio(), usuario.getFechaNacimiento(),
				usuario.getSexo().toString(), usuario.getEmail(), rolDTO, rutasRecorridasDTO, rutasAgregadasDTO,
				usuario.isHabilitado());
		/*
		 * if (completo) {
		 * usuarioDTO.setRutasRecorridas(this.convertToRutaArrayListDTO(usuario.
		 * getRutasRecorridas(), completo)); }
		 */
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
		Usuario usuario = new Usuario(usuarioDTO.getUsuario(), usuarioDTO.getContrasena(), usuarioDTO.getApellido(),
				usuarioDTO.getNombre(), usuarioDTO.getDomicilio(), usuarioDTO.getFechaNacimiento(),
				Sexo.valueOf(usuarioDTO.getSexo()), usuarioDTO.getEmail(), rol);
		return usuario;
	}

	// Metodos para entidad Ruta
	// Convertir de Model en DTO

	public List<RutaDTO> convertToRutaArrayListDTO(List<Ruta> rutasRecorridas, boolean completo) {
		List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
		for (Ruta ruta : rutasRecorridas) {
			rutasDTO.add(this.convertToRutaDTO(ruta, false));
		}
		return rutasDTO;
	}

	public RutaDTO convertToRutaDTO(Ruta ruta, boolean completo) {
		RutaDTO rutaDTO = new RutaDTO(ruta.getId(), ruta.getNombre(), ruta.getDescripcion(), ruta.getPrivacidad(),
				ruta.getRecorrido(), ruta.getFormato(), ruta.getDistancia(), ruta.getDificultad(), ruta.getTiempo(),
				ruta.getFecha(), ruta.getFotos(), null, ruta.getActividad());
		if (completo) {
			// rutaDTO.setCalificaciones(this.convertToCalificacionesArrayListDTO(ruta.getCalificaciones(),
			// completo));
		}
		return rutaDTO;
	}

	// Metodos para entidad Actividad
	// Convertir de Model en DTO

	public ActividadDTO convertToActividadDTO(Actividad actividad) {
		ActividadDTO actividadDTO = new ActividadDTO(actividad.getId(), actividad.getNombre(),
				actividad.getDescripcion());
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
		Actividad actividad = new Actividad(actividadDTO.getNombre(), actividadDTO.getDescripcion());
		return actividad;
	}

	// Metodos para entidad Rol
	// Convertir de Model en DTO

	public RolDTO convertToRolDTO(Rol rol) {
		RolDTO rolDTO = new RolDTO(rol.getId(), rol.getDescripcion());
		return rolDTO;
	}

	public List<RolDTO> convertToRolArrayListDTO(List<Rol> roles) {
		List<RolDTO> rolesDTO = new ArrayList<RolDTO>();
		for (Rol rol : roles) {
			rolesDTO.add(this.convertToRolDTO(rol));
		}
		return rolesDTO;
	}

	public Rol convertToRol(RolDTO rolDTO) {
  if (rolDTO.getDescripcion() == "Usuario basico del sistema") {
   Basico rol = new Basico(rolDTO.getDescripcion());
   return rol;
  } else {
   Administrador rol = new Administrador(rolDTO.getDescripcion());
   return rol;
  }
 }

	// Metodos para entidad Calificacion
	// Convertir de Model en DTO

	public CalificacionDTO convertToCalificacionDTO(Calificacion calificacion) {
		CalificacionDTO calificacionDTO = new CalificacionDTO(calificacion.getId(), calificacion.getValor(),
				this.convertToUsuarioDTO(calificacion.getUsuario(), false));
		return calificacionDTO;
	}

	public List<CalificacionDTO> convertToCalificacionArrayListDTO(List<Calificacion> calificaciones) {
		List<CalificacionDTO> calificacionesDTO = new ArrayList<CalificacionDTO>();
		for (Calificacion calificacion : calificaciones) {
			calificacionesDTO.add(this.convertToCalificacionDTO(calificacion));
		}
		return calificacionesDTO;
	}

	// Convertir de DTO en Model

	public Calificacion convertToCalificacion(CalificacionDTO calificacionDTO) {
		Calificacion calificacion = new Calificacion(calificacionDTO.getValor(), this.convertToUsuario(
				(calificacionDTO.getUsuario()), this.convertToRol(calificacionDTO.getUsuario().getRol())));
		return calificacion;
	}

}