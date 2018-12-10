package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import dao.FactoryDAO;
import dao.bi.*;
import dto.NotaDTO;
import dto.RutaDTO;
import model.Nota;
import model.Ruta;

@Path("/ruta")

public class RutaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BIRutaDAO rutaDAO = FactoryDAO.getFactoryDAO().getRutaDAO();
	private BINotaDAO notaDAO = FactoryDAO.getFactoryDAO().getNotaDAO();

	@GET
	@Path("/listAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaDTO> listAll() {
		return rutaDAO.listAllComplete();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long id) {
		RutaDTO ruta = rutaDAO.findComplete(id);
		if (ruta != null) {
			return Response.ok().entity(ruta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se encontró la ruta").build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(RutaDTO ruta) {
		if (!rutaDAO.isCreated(ruta)) {
			rutaDAO.create(ruta);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(RutaDTO rutaDTO, @PathParam("id") Long id) {
		Ruta ruta = (Ruta) rutaDAO.find(id);
		if (ruta != null) {
			rutaDAO.update(rutaDTO, id);
			return Response.ok().entity(rutaDTO).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response remove(@PathParam("id") Long id) {
		Ruta ruta = (Ruta) rutaDAO.find(id);
		if (ruta != null) {
			rutaDAO.delete(ruta);
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe una ruta con ese Id").build();
		}
	}
	
	@POST
	@Path("/{id}/nota")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNote(@PathParam("id") Long id, NotaDTO notaDTO) {
		Ruta ruta = (Ruta) rutaDAO.find(id);
		if (ruta != null) {
			//Nota nota = notaDAO.getByDescrip(notaDTO.getDescripcion());
			//if(nota != null) {
				//return Response.status(Response.Status.CONFLICT).entity("Ya existe otra nota para esta ruta con una descripcion identica.").build();
			//}else {
				notaDAO.create(notaDTO);
				Nota nota = notaDAO.getByDescrip(notaDTO.getDescripcion());
				ruta.addNota(nota);
				rutaDAO.update(ruta);
				return Response.status(Response.Status.CREATED).build();
			//}
		} else {
			return Response.status(Response.Status.CONFLICT).entity("Ruta inexistente").build();
		}
	}
}