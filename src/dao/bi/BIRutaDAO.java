package dao.bi;

import java.util.List;

import dto.RutaDTO;
import model.Ruta;

public interface BIRutaDAO extends BIGenericDAO<Ruta> {

	List<RutaDTO> listAllComplete();

	RutaDTO findComplete(Long id);

	boolean isCreated(RutaDTO ruta);

	void update(RutaDTO rutaDTO, Long id);

	void create(RutaDTO ruta);

}