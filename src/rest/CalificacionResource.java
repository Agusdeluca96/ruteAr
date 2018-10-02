package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import dao.FactoryDAO;
import dao.bi.*;
import dto.CalificacionDTO;

@Path("/calificacion")

public class CalificacionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BICalificacionDAO calificacionDAO = FactoryDAO.getFactoryDAO().getCalificacionDAO();

	@GET
	@Path("/listAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CalificacionDTO> listAll() {
		return calificacionDAO.listAllComplete();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id) {
		CalificacionDTO calificacion = calificacionDAO.findComplete(id);
		if (calificacion != null) {
			return Response.ok().entity(calificacion).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se encontró la calificacion").build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(CalificacionDTO calificacion) {
		if (!calificacionDAO.isCreated(calificacion)) {
			calificacionDAO.create(calificacion);
			return Response.status(Response.Status.CREATED).build();
		} else {
			System.out.println("Ya existe esa calificacion");
			return Response.status(Response.Status.CONFLICT).build();
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