package dao.impl;

import java.util.List;

import dao.FactoryDAO;
import dao.bi.BIRutaDAO;
import dto.FactoryDTO;
import dto.RutaDTO;
import model.Actividad;
import model.Rol;
import model.Ruta;
import model.Usuario;

public class HibernateRutaDAO extends HibernateGenericDAO<Ruta> implements BIRutaDAO {

	public HibernateRutaDAO() {
		super(Ruta.class);
	}

	@Override
	public List<RutaDTO> listAllComplete() {
		return FactoryDTO.getFactoryDTO().convertToRutaArrayListDTO(super.listAll(), true);
	}

	@Override
	public RutaDTO findComplete(Long id) {
		Ruta ruta = (Ruta) super.find(id);
		if (ruta != null) {
			return FactoryDTO.getFactoryDTO().convertToRutaDTO(ruta, true);
		} else {
			return null;
		}
	}

	@Override
	public boolean isCreated(RutaDTO ruta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(RutaDTO rutaDTO, Long id) {
		Ruta ruta = FactoryDTO.getFactoryDTO().convertToRuta(rutaDTO,
				(Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO().find(rutaDTO.getCreador().getId()), (Actividad) FactoryDAO.getFactoryDAO().getActividadDAO().find(rutaDTO.getActividad().getId()) );
		ruta.setId(id);
		super.update(ruta);

	}

	@Override
	public void create(RutaDTO rutaDTO) {
		Usuario usuario = (Usuario) FactoryDAO.getFactoryDAO().getUsuarioDAO().find(rutaDTO.getCreador().getId());
		Actividad actividad = (Actividad) FactoryDAO.getFactoryDAO().getActividadDAO().find(rutaDTO.getActividad().getId());
		Ruta ruta = FactoryDTO.getFactoryDTO().convertToRuta(rutaDTO, usuario, actividad);
		super.create(ruta);

	}

}