package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import dao.FactoryDAO;
import dao.bi.*;
import dto.RutaDTO;
import dto.UsuarioDTO;
import model.Usuario;

@Path("/usuario")

public class UsuarioResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BIUsuarioDAO usuarioDAO = FactoryDAO.getFactoryDAO().getUsuarioDAO();
	private String mensaje;

	@GET
	@Path("/listAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioDTO> listAll() {
		return usuarioDAO.listAllIncomplete();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id) {
		UsuarioDTO usuario = usuarioDAO.findIncomplete(id);
		if (usuarioDAO.isCreated(usuario)) {
			return Response.ok().entity(usuario).build();
		} else {
			mensaje = "No se encontró el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(UsuarioDTO usuario) {
		if (!usuarioDAO.isCreated(usuario)) {
			usuarioDAO.create(usuario);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UsuarioDTO usuario, @PathParam("id") Long id) {
		if (usuarioDAO.isCreated(usuario)) {
			usuarioDAO.update(usuario, id);
			return Response.ok().entity(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response remove(@PathParam("id") Long id) {
		Usuario usuario = (Usuario) usuarioDAO.find(id);
		if (usuario != null) {
			usuarioDAO.delete(usuario);
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe un usuario con ese Id").build();
		}
	}

	@PUT
	@Path("{id}/habilitar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response habilitar(@PathParam("id") Long id) {
		UsuarioDTO usuario = usuarioDAO.findIncomplete(id);
		if (usuarioDAO.isCreated(usuario)) {
			UsuarioDTO usuarioRta = usuarioDAO.habilitar(id);
			return Response.ok().entity(usuarioRta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@PUT
	@Path("{id}/deshabilitar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deshabilitar(@PathParam("id") Long id) {
		UsuarioDTO usuario = usuarioDAO.findIncomplete(id);
		if (usuarioDAO.isCreated(usuario)) {
			UsuarioDTO usuarioRta = usuarioDAO.deshabilitar(id);
			return Response.ok().entity(usuarioRta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UsuarioDTO usuarioValidar) {
		if (usuarioDAO.isCreated(usuarioValidar)) {
			UsuarioDTO usuario = usuarioDAO.findByUsuario(usuarioValidar.getUsuario());
			if (usuario.getContrasena().equals(usuarioValidar.getContrasena()) && usuario.getHabilitado()) {
				return Response.status(Response.Status.CREATED).entity(usuario).build();
			} else {
				if (!usuario.getHabilitado()) {
					return Response.status(Response.Status.UNAUTHORIZED)
							.entity("Usuario no habilitado para ingresar al sistema").build();
				} else {
					return Response.status(Response.Status.FORBIDDEN).entity("Contrasena incorrecta").build();
				}
			}
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("El usuario ingresado no se encuentra registrado en el sistema").build();
		}
	}

	@GET
 @Path("{id}/rutasAgregadas")
 @Produces(MediaType.APPLICATION_JSON)
 public Response findAddedRoutes(@PathParam("id") Long id) {
  UsuarioDTO usuario = usuarioDAO.findComplete(id);
  if (usuarioDAO.isCreated(usuario)) {
   List<RutaDTO> rutasAgregadas = usuario.getRutasAgregadas();
   return Response.ok().entity(rutasAgregadas).build();
  } else {
   mensaje = "No se encontró el usuario";
   return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
  }
 }

	@GET
	@Path("{id}/rutasRecorridas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findKnownRoutes(@PathParam("id") Long id) {
		UsuarioDTO usuario = usuarioDAO.findComplete(id);
		if (usuarioDAO.isCreated(usuario)) {
			List<RutaDTO> rutasRecorridas = usuario.getRutasRecorridas();
			return Response.ok().entity(rutasRecorridas).build();
		} else {
			mensaje = "No se encontró el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@GET
	@Path("{id}/completo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findComplete(@PathParam("id") Long id) {
		UsuarioDTO usuario = usuarioDAO.findComplete(id);
		if (usuarioDAO.isCreated(usuario)) {
			return Response.ok().entity(usuario).build();
		} else {
			mensaje = "No se encontró el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}