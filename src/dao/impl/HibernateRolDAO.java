package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.bi.BIRolDAO;
import dto.RolDTO;
import dto.FactoryDTO;
import model.Rol;

public class HibernateRolDAO extends HibernateGenericDAO<Rol> implements BIRolDAO {

	public HibernateRolDAO() {
		super(Rol.class);
	}

	public List<RolDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToRolArrayListDTO(super.listAll());
	}

	public RolDTO findComplete(Long id) {
		Rol rol = (Rol) super.find(id);
		if (rol != null) {
			return FactoryDTO.getFactoryDTO().convertToRolDTO(rol);
		} else {
			return null;
		}
	}

	public boolean isCreated(RolDTO rol) {
		Query q = this.getEntityManager().createQuery("SELECT r FROM rol r WHERE r.nombre = :nombre");
		q.setParameter("nombre", rol.getDescripcion());
		return (!q.getResultList().isEmpty());
	}

	public Rol getByDescrip(String descrip) {
		Query q = this.getEntityManager().createQuery("SELECT r FROM rol r WHERE r.descripcion = :descrip");
		q.setParameter("descrip", descrip);
		return (Rol) q.getSingleResult();
	}

}
