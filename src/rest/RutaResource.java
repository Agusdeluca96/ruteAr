package rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.sun.jersey.multipart.FormDataParam;

import dao.FactoryDAO;
import dao.bi.*;
import dto.CalificacionDTO;
import dto.NotaDTO;
import dto.RutaDTO;
import model.Ruta;

@Path("/ruta")

public class RutaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private BIRutaDAO rutaDAO = FactoryDAO.getFactoryDAO().getRutaDAO();

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
			ruta = rutaDAO.create(ruta);
			return Response.status(Response.Status.CREATED).entity(ruta).build();
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
		if (rutaDAO.canDelete(id)) {
			rutaDAO.delete(id);
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity(
					"Esta Ruta no puede ser eliminada ya que posee notas, calificaciones u otros usuarios la marcaron como recorrida")
					.build();
		}
	}

	@POST
	@Path("/{id}/nota")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNote(@PathParam("id") Long id, NotaDTO notaDTO) {
		if (rutaDAO.addNotaRuta(id, notaDTO)) {
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"msj\": \"La Nota no pudo ser creada, por favor intente nuevamente.\"}").build();
		}
	}
	
	@POST
	@Path("/{id}/foto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addFoto(@PathParam("id") Long id, @FormDataParam("foto") File fileInputStream) {
		try {
			byte[] foto = Files.readAllBytes(fileInputStream.toPath());
			if (rutaDAO.addFotoRuta(id, foto)) {
				return Response.status(Response.Status.CREATED).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/{id}/calificacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(@PathParam("id") Long id, CalificacionDTO calificacionDTO) {
		if (rutaDAO.rateRuta(id, calificacionDTO)) {
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"msj\": \"La Calificacion no pudo ser creada, por favor intente nuevamente.\"}").build();
		}
	}
	
	@POST
	@Path("/{id}/kml")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addKml(@PathParam("id") Long id, @FormDataParam("foto") File fileInputStream) {
		try {
			byte[] kml = Files.readAllBytes(fileInputStream.toPath());
			if (rutaDAO.setRecorridoRuta(id, kml)) {
				return Response.status(Response.Status.CREATED).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/{id}/kml")
	@Produces(MediaType.TEXT_PLAIN)
	public Response kmlget(@PathParam("id") Long id){
		RutaDTO ruta = rutaDAO.findComplete(id);
		if (ruta.getRecorrido() != null){
			return Response.ok().entity(ruta.getRecorrido()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}