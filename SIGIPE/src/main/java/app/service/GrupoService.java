package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;
import jakarta.transaction.Transactional;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo save(Grupo grupo) {
		return this.grupoRepository.save(grupo);
	}

	public Grupo update(long id, Grupo grupoNovo) {
		Optional<Grupo> optGrupo = this.grupoRepository.findById(id);
		if(optGrupo.isPresent()) {
			Grupo grupoOld = optGrupo.get();
			grupoNovo.setId(id);
			grupoNovo.setAlunos(grupoOld.getAlunos());
			grupoNovo.setDemandaInscrita(grupoOld.getDemandaInscrita());
			grupoNovo.setDemandaSolicitada(grupoOld.getDemandaSolicitada());
			
			return this.grupoRepository.save(grupoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	public Grupo findById(long idGrupo) {
		Optional<Grupo> optionalGrupo = this.grupoRepository.findById(idGrupo);
		if (optionalGrupo.isPresent())
			return optionalGrupo.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Grupo deleteById(long idGrupo) {
		Optional<Grupo> optionalGrupo = this.grupoRepository.findById(idGrupo);
		if (optionalGrupo.isPresent()) {
			Grupo grupo = optionalGrupo.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(grupo.getAlunos());
	        Hibernate.initialize(grupo.getDemandaInscrita());
	        Hibernate.initialize(grupo.getDemandaSolicitada());
			
			this.grupoRepository.deleteById(idGrupo);
			return grupo;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
