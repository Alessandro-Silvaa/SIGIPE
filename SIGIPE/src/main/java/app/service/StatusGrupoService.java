package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.StatusGrupo;
import app.repository.StatusGrupoRepository;

@Service
public class StatusGrupoService {

	@Autowired
	private StatusGrupoRepository statusGrupoRepository;

	public StatusGrupo save(StatusGrupo statusGrupo) {
		return this.statusGrupoRepository.save(statusGrupo);
	}

	public StatusGrupo update(long id, StatusGrupo statusGrupo) {
		statusGrupo.setId(id);
		return this.statusGrupoRepository.save(statusGrupo);
	}

	public List<StatusGrupo> findAll() {
		return this.statusGrupoRepository.findAll();
	}

	public StatusGrupo findById(long idStatusGrupo) {
		return this.statusGrupoRepository.findById(idStatusGrupo).get();
	}

	public StatusGrupo deleteById(long idStatusGrupo) {
		StatusGrupo statusGrupo = findById(idStatusGrupo);
		if(statusGrupo != null) {
			this.statusGrupoRepository.deleteById(idStatusGrupo);
			return statusGrupo;			
		}
		throw new RuntimeException();
	}

}
