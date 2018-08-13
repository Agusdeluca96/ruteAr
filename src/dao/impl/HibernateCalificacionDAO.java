package dao.impl;

import dao.bi.BICalificacionDAO;
import model.Calificacion;

public class HibernateCalificacionDAO extends HibernateGenericDAO<Calificacion> implements BICalificacionDAO {

	public HibernateCalificacionDAO() {
		super(Calificacion.class);
	}

}
