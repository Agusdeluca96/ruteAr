package dao.bi;

import java.util.List;

import dto.CalificacionDTO;
import dto.RutaDTO;
import dto.UsuarioDTO;
import model.Ruta;

public interface BIRutaDAO extends BIGenericDAO<Ruta> {

	List<RutaDTO> listAllComplete();

	RutaDTO findComplete(Long id);

	boolean isCreated(RutaDTO ruta);

	void update(RutaDTO rutaDTO, Long id);

	void create(RutaDTO ruta);

	boolean rateRuta(Long id, CalificacionDTO calificacionDTO);

	Integer cantUsuariosRecorrieron(Ruta ruta);

}