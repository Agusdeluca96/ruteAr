package dao.impl;

import dao.bi.BIRutaDAO;
import model.Ruta;

public class HibernateRutaDAO extends HibernateGenericDAO<Ruta> implements BIRutaDAO {

	public HibernateRutaDAO() {
		super(Ruta.class);
	}

}
