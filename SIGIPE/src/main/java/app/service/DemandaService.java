package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demanda;
import app.repository.DemandaRepository;
import jakarta.transaction.Transactional;

@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;

	public Demanda save(Demanda demanda) {
		return this.demandaRepository.save(demanda);
	}

	public Demanda update(long id, Demanda demandaNovo) {
		Optional<Demanda> optDemanda = this.demandaRepository.findById(id);
		if(optDemanda.isPresent()) {
			Demanda demandaOld = optDemanda.get();
			demandaNovo.setId(id);
			demandaNovo.setInstituicao(demandaOld.getInstituicao());
			demandaNovo.setCursos(demandaOld.getCursos());
			demandaNovo.setDemandante(demandaOld.getDemandante());
			demandaNovo.setGruposInscritos(demandaOld.getGruposInscritos());
			demandaNovo.setGruposSolicitacao(demandaOld.getGruposSolicitacao());
			demandaNovo.setStatus(demandaOld.getStatus());
			demandaNovo.setTurmas(demandaOld.getTurmas());
			
			return this.demandaRepository.save(demandaNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Demanda> findAll() {
		return this.demandaRepository.findAll();
	}

	public Demanda findById(long idDemanda) {
		Optional<Demanda> optionalDemanda = this.demandaRepository.findById(idDemanda);
		if (optionalDemanda.isPresent())
			return optionalDemanda.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Demanda deleteById(long idDemanda) {
		Optional<Demanda> optionalDemanda = this.demandaRepository.findById(idDemanda);
		if (optionalDemanda.isPresent()) {
			Demanda demanda = optionalDemanda.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(demanda.getCursos());
	        Hibernate.initialize(demanda.getDemandante());
	        Hibernate.initialize(demanda.getGruposInscritos());
	        Hibernate.initialize(demanda.getGruposSolicitacao());
	        Hibernate.initialize(demanda.getInstituicao());
	        Hibernate.initialize(demanda.getStatus());
	        Hibernate.initialize(demanda.getTurmas());
			
			this.demandaRepository.deleteById(idDemanda);
			return demanda;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
