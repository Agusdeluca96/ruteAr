package dao.impl;

import java.util.List;

import dao.bi.BIRutaDAO;
import dto.FactoryDTO;
import dto.RutaDTO;
import model.Actividad;
import model.Ruta;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void create(RutaDTO ruta) {
		// TODO Auto-generated method stub

	}

}