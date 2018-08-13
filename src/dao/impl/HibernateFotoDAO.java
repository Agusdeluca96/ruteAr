package dao.impl;

import dao.bi.BIFotoDAO;
import model.Foto;

public class HibernateFotoDAO extends HibernateGenericDAO<Foto> implements BIFotoDAO {

	public HibernateFotoDAO() {
		super(Foto.class);
	}

}
