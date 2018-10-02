package dao.bi;

import java.util.List;
import dto.RolDTO;
import model.Rol;

public interface BIRolDAO extends BIGenericDAO<Rol> {

	public abstract List<RolDTO> listAllComplete();

	public abstract RolDTO findComplete(Long id);

	public abstract boolean isCreated(RolDTO rol);

	public abstract Rol getByDescrip(String descrip);

}
