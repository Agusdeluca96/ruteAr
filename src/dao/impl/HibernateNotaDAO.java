package dao.impl;

import dao.bi.BINotaDAO;
import model.Nota;

public class HibernateNotaDAO extends HibernateGenericDAO<Nota> implements BINotaDAO {

	public HibernateNotaDAO() {
		super(Nota.class);
	}

}
