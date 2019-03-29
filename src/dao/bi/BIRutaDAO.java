package dao.bi;

import java.util.List;

import dto.CalificacionDTO;
import dto.NotaDTO;
import dto.RutaDTO;
import model.Ruta;

public interface BIRutaDAO extends BIGenericDAO<Ruta> {

	List<RutaDTO> listAllComplete();

	RutaDTO findComplete(Long id);

	boolean isCreated(RutaDTO ruta);

	void update(RutaDTO rutaDTO, Long id);

	RutaDTO create(RutaDTO ruta);

	void delete(Long id);

	boolean rateRuta(Long id, CalificacionDTO calificacionDTO);

	Integer cantUsuariosRecorrieron(Ruta ruta);

	boolean addNotaRuta(Long id, NotaDTO notaDTO);

	boolean canDelete(Long id);

	boolean addFotoRuta(Long id, byte[] foto);

	boolean setRecorridoRuta(Long id, byte[] kml);

}