package dao.bi;

import java.util.List;

import javax.persistence.EntityManager;

public interface BIGenericDAO<T> {

	public abstract EntityManager getEntityManager();

	public abstract T create(T t);

	public abstract T update(T t);

	public abstract void delete(Object o);

	public abstract Object find(Long id);

	public abstract List<T> listAll();

	public abstract boolean contains(Object o);

}
