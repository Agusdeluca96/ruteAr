package dao.impl;

import javax.persistence.Query;

import dao.FactoryDAO;
import dao.bi.BINotaDAO;
import dto.FactoryDTO;
import dto.NotaDTO;
import model.Nota;
import model.Usuario;

public class HibernateNotaDAO extends HibernateGenericDAO<Nota> implements BINotaDAO {

	public HibernateNotaDAO() {
		super(Nota.class);
	}
	
	@Override
	public void create(NotaDTO notaDTO) {
		Usuario usuario = (Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO().find(notaDTO.getAutor().getId());
		Nota nota = FactoryDTO.getFactoryDTO().convertToNota(notaDTO, usuario);
		super.create(nota);
	}
	
	public Nota getByDescrip(String descrip) {
		Query q = this.getEntityManager().createQuery("SELECT n FROM nota n WHERE n.descripcion = :descripcion");
		q.setParameter("descripcion", descrip);
		return (Nota) q.getSingleResult();
	}

}
