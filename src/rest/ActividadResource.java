package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import dao.FactoryDAO;
import dao.bi.*;
import dto.ActividadDTO;
import model.Actividad;

@Path("/actividad")

public class ActividadResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BIActividadDAO actividadDAO = FactoryDAO.getFactoryDAO().getActividadDAO();

	@GET
	@Path("/listAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ActividadDTO> listAll() {
		return actividadDAO.listAllComplete();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id) {
		ActividadDTO actividad = actividadDAO.findComplete(id);
		if (actividad != null) {
			return Response.ok().entity(actividad).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el usuario").build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ActividadDTO actividad) {
		if (!actividadDAO.isCreated(actividad)) {
			actividadDAO.create(actividad);
			return Response.status(Response.Status.CREATED).build();
		} else {
			System.out.println("Ya existe esa actividad");
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modify(ActividadDTO actividadDTO, @PathParam("id") Long id) {
		Actividad actividad = (Actividad) actividadDAO.find(id);
		if (actividad != null) {
			actividadDAO.update(actividadDTO, id);
			return Response.ok().entity(actividadDTO).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response remove(@PathParam("id") Long id) {
		Actividad actividad = (Actividad) actividadDAO.find(id);
		if (actividad != null) {
			actividadDAO.delete(actividad);
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe una actividad con ese Id").build();
		}
	}
}