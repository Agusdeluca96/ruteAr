package dao.bi;

import dto.NotaDTO;
import model.Nota;

public interface BINotaDAO extends BIGenericDAO<Nota> {

	void create(NotaDTO nota);
	
	public abstract Nota getByDescrip(String descrip);
	
}
