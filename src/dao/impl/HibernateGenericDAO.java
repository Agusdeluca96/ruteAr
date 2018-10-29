package dao.impl;

import java.util.List;

import javax.persistence.*;

import dao.bi.BIGenericDAO;

@SuppressWarnings("rawtypes")
public abstract class HibernateGenericDAO<T> implements BIGenericDAO<T> {
	private Class type;
	protected static EntityManager em;

	public HibernateGenericDAO(Class t) {
		this.type = t;
	}

	@Override
	public EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ruteAr");
			em = emf.createEntityManager();
		}
		return em;
	}

	@Override
	public T create(T t) {
		EntityTransaction tx = this.getEntityManager().getTransaction();
		tx.begin();
		this.getEntityManager().persist(t);
		this.getEntityManager().flush();
		tx.commit();
		return null;
	}

	@Override
	public T update(T t) {
		EntityTransaction tx = this.getEntityManager().getTransaction();
		tx.begin();
		this.getEntityManager().merge(t);
		tx.commit();
		return null;
	}

	@Override
	public void delete(Object object) {
		EntityTransaction tx = this.getEntityManager().getTransaction();
		tx.begin();
		this.getEntityManager().remove(object);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object find(Long id) {
		return this.getEntityManager().find(this.type, id);
	}

	@Override
	public boolean contains(Object object) {
		return this.getEntityManager().contains(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		return this.getEntityManager().createQuery("FROM " + type.getCanonicalName()).getResultList();
	}

}