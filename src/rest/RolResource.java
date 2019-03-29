package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import dao.FactoryDAO;
import dao.bi.*;
import dto.RolDTO;

@Path("/rol")

public class RolResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BIRolDAO rolDAO = FactoryDAO.getFactoryDAO().getRolDAO();

	@GET
	@Path("/listAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> listAll() {
		return rolDAO.listAllComplete();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id) {
		RolDTO rol = rolDAO.findComplete(id);
		if (rol != null) {
			return Response.ok().entity(rol).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el rol").build();
		}
	}

	/*
	 * @PUT
	 * 
	 * @Path("{id}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response update(ActividadDTO
	 * actividadDTO, @PathParam("id") Long id) { Actividad actividad = (Actividad)
	 * actividadDAO.find(id); if (actividad != null) {
	 * actividadDAO.update(actividadDTO, id); return
	 * Response.ok().entity(actividadDTO).build(); } else { return
	 * Response.status(Response.Status.NOT_FOUND).entity("[]").build(); } }
	 * 
	 * @DELETE
	 * 
	 * @Path("{id}")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public Response remove(@PathParam("id") Long
	 * id) { Actividad actividad = (Actividad) actividadDAO.find(id); if (actividad
	 * != null) { actividadDAO.delete(actividad); return
	 * Response.noContent().build(); } else { return
	 * Response.status(Response.Status.NOT_FOUND).
	 * entity("No existe una actividad con ese Id").build(); } }
	 */
}