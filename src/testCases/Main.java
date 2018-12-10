package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import dao.FactoryDAO;
import model.*;

public class Main {
	public static void main(String[] args) {
		// Rol a = new Rol("Usuario del sistema");
		// FactoryDAO.getRolDAO().create(a);

		// Se crea un usuario administrador
		Administrador rolAdmin = new Administrador("Administrador");
		FactoryDAO.getFactoryDAO().getRolDAO().create(rolAdmin);

		Usuario admin = new Usuario("Agusdeluca96", "puntito", "De Luca", "Agustin", "14b N'959", new Date(),
				Sexo.MASCULINO, "agusdeluca96@gmail.com", rolAdmin);
		FactoryDAO.getFactoryDAO().getUsuarioDAO().create(admin);

		// El usuario administrador creado agrega una serie de actividades al sistema
		Actividad motociclismo = new Actividad("Motociclismo", "Motociclismo");
		FactoryDAO.getFactoryDAO().getActividadDAO().create(motociclismo);
		Actividad ciclismo = new Actividad("Ciclismo", "Ciclismo");
		FactoryDAO.getFactoryDAO().getActividadDAO().create(ciclismo);
		Actividad alpinismo = new Actividad("Alpinismo", "");
		FactoryDAO.getFactoryDAO().getActividadDAO().create(alpinismo);
		Actividad senderismo = new Actividad("Senderismo", "Senderismo");
		FactoryDAO.getFactoryDAO().getActividadDAO().create(senderismo);

		// Se recupera la actividad con la descripcion "Ciclismo"
		Actividad ciclismoRecuperado = FactoryDAO.getFactoryDAO().getActividadDAO().getByDescrip("Ciclismo");

		// Se modifica el nombre y la descripcion de la actividad por Cicloturismo
		ciclismoRecuperado.setNombre("Cicloturismo");
		ciclismoRecuperado.setDescripcion("Cicloturismo");
		FactoryDAO.getFactoryDAO().getActividadDAO().update(ciclismoRecuperado);

		// Se elimina la actividad Alpinismo
		FactoryDAO.getFactoryDAO().getActividadDAO().delete(alpinismo);

		// Se crea un usuario basico para el sistema
		Basico rolBasico = new Basico("Usuario Basico");
		FactoryDAO.getFactoryDAO().getRolDAO().create(rolBasico);

		Usuario usuarioBasico1 = new Usuario("Ramicortes", "puntito", "Cortes", "Ramiro", "132 y 413", new Date(),
				Sexo.MASCULINO, "ramirocortes@gmail.com", rolBasico);
		FactoryDAO.getFactoryDAO().getUsuarioDAO().create(usuarioBasico1);

		// Se crea otro usuario basico para el sistema
		Usuario usuarioBasico2 = new Usuario("Rodripait", "puntito", "Pait", "Rodrigo", "1 y 48", new Date(),
				Sexo.MASCULINO, "rodripait@gmail.com", rolBasico);
		FactoryDAO.getFactoryDAO().getUsuarioDAO().create(usuarioBasico2);

		// El usuario basico creado agrega una ruta al sistema
		Ruta ruta = new Ruta("Ruta Nacional 40",
				"Carretera de Argentina cuyo recorrido se extiende desde el Cabo Vírgenes, Santa Cruz hasta  La Quiaca, en Jujuy",
				Privacidad.PUBLICA, "/files/r04593848.kml", Formato.SOLO_IDA, 5194.00, Dificultad.DIFICIL, 52.5,
				new Date(), new ArrayList<Foto>(), usuarioBasico1, motociclismo);
		FactoryDAO.getFactoryDAO().getRutaDAO().create(ruta);
		// usuarioBasico1.addRutaAgregada(ruta);
		// usuarioBasico1.addRutaRecorrida(ruta);
		// FactoryDAO.getFactoryDAO().getUsuarioDAO().update(usuarioBasico1);

		Ruta ruta2 = new Ruta("Ruta 36", "Carretera de Argentina que se extiende desde Cordoba hasta Rosario",
				Privacidad.PUBLICA, "/files/r04593848.kml", Formato.SOLO_IDA, 5194.00, Dificultad.DIFICIL, 52.5,
				new Date(), new ArrayList<Foto>(), usuarioBasico1, motociclismo);
		// usuarioBasico1.addRutaAgregada(ruta2);
		FactoryDAO.getFactoryDAO().getRutaDAO().create(ruta2);

		Ruta ruta3 = new Ruta("Ruta 11",
				"Carretera de Argentina que se extiende desde Capital Federal hasta Mar de Ajo", Privacidad.PUBLICA,
				"/files/r04593848.kml", Formato.SOLO_IDA, 5194.00, Dificultad.DIFICIL, 52.5, new Date(),
				new ArrayList<Foto>(), usuarioBasico1, motociclismo);
		// usuarioBasico1.addRutaRecorrida(ruta3);
		FactoryDAO.getFactoryDAO().getRutaDAO().create(ruta3);

		// Se recuperan todas las actividades del sistema y se listan en pantalla
		List<Actividad> actividades = FactoryDAO.getFactoryDAO().getActividadDAO().listAll();
		for (Object act : actividades) {
			System.out.println("Actividad: " + ((Actividad) act).getDescripcion());
		}
		// Se recupera la ruta con id 1 y se muestra la dificultad
		Ruta rutaRecuperada = (Ruta) FactoryDAO.getFactoryDAO().getRutaDAO().find((long) 1);
		System.out.println(rutaRecuperada.getDificultad());

		// Probando git
		
		// Se crean 2 calificaciones
		/*
		 * Calificacion calificacion1 = new Calificacion(5, usuarioBasico1);
		 * FactoryDAO.getFactoryDAO().getCalificacionDAO().create(calificacion1);
		 * Calificacion calificacion2 = new Calificacion(4, usuarioBasico2);
		 * FactoryDAO.getFactoryDAO().getCalificacionDAO().create(calificacion2);
		 */

		// Se agregan 2 calificaciones a las rutas
		/*
		 * rutaRecuperada.addCalificacion(calificacion1);
		 * rutaRecuperada.addCalificacion(calificacion2);
		 * FactoryDAO.getFactoryDAO().getRutaDAO().update(rutaRecuperada);
		 */
	}
}