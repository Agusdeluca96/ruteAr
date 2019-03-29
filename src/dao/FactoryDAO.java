package dao;

import dao.bi.*;
import dao.impl.*;

public class FactoryDAO {
	protected static FactoryDAO factoryDAO;
	protected static BIActividadDAO actividadDAO;
	protected static BIRolDAO rolDAO;
	protected static BICalificacionDAO calificacionDAO;
	protected static BINotaDAO notaDAO;
	protected static BIRutaDAO rutaDAO;
	protected static BIUsuarioDAO usuarioDAO;

	public static FactoryDAO getFactoryDAO() {
		if (factoryDAO == null) {
			factoryDAO = new FactoryDAO();
		}
		return factoryDAO;
	}

	public BIActividadDAO getActividadDAO() {
		if (actividadDAO == null) {
			actividadDAO = new HibernateActividadDAO();
		}
		return actividadDAO;
	}

	public BIRolDAO getRolDAO() {
		if (rolDAO == null) {
			rolDAO = new HibernateRolDAO();
		}
		return rolDAO;
	}

	public BICalificacionDAO getCalificacionDAO() {
		if (calificacionDAO == null) {
			calificacionDAO = new HibernateCalificacionDAO();
		}
		return calificacionDAO;
	}

	public BINotaDAO getNotaDAO() {
		if (notaDAO == null) {
			notaDAO = new HibernateNotaDAO();
		}
		return notaDAO;
	}

	public BIRutaDAO getRutaDAO() {
		if (rutaDAO == null) {
			rutaDAO = new HibernateRutaDAO();
		}
		return rutaDAO;
	}

	public BIUsuarioDAO getUsuarioDAO() {
		if (usuarioDAO == null) {
			usuarioDAO = new HibernateUsuarioDAO();
		}
		return usuarioDAO;
	}

}
