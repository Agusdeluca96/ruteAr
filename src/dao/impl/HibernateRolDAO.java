package dao.impl;

import dao.bi.BIRolDAO;
import model.Rol;

public class HibernateRolDAO extends HibernateGenericDAO<Rol> implements BIRolDAO {

	public HibernateRolDAO() {
		super(Rol.class);
	}

}
