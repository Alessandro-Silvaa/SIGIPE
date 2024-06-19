package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo save(Grupo grupo) {
		return this.grupoRepository.save(grupo);
	}

	public Grupo update(long id, Grupo grupo) {
		grupo.setId(id);
		return this.grupoRepository.save(grupo);
	}

	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	public Grupo findById(long idGrupo) {
		return this.grupoRepository.findById(idGrupo).get();
	}

	public Grupo deleteById(long idGrupo) {
		Grupo grupo = findById(idGrupo);
		if(grupo != null) {
			this.grupoRepository.deleteById(idGrupo);
			return grupo;			
		}
		throw new RuntimeException();
	}

}
