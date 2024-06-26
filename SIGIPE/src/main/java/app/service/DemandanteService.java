package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import app.entity.Demanda;
import app.entity.Instituicao;
import app.repository.DemandaRepository;
import app.repository.InstituicaoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demandante;
import app.repository.DemandanteRepository;
import jakarta.transaction.Transactional;

@Service
public class DemandanteService {

	@Autowired
	DemandanteRepository demandanteRepository;

	@Autowired
	private DemandaRepository demandaRepository;


	public Demandante save(Demandante demandante) {
		return this.demandanteRepository.save(demandante);
	}

	@Transactional
	public Demandante update(long id, Demandante demandanteNovo) {
		Optional<Demandante> optDemandante = this.demandanteRepository.findById(id);
		if (optDemandante.isPresent()) {
			Demandante demandanteOld = optDemandante.get();
			demandanteNovo.setId(id);

			// Verificar e associar Demanda
			if (demandanteOld.getDemanda() == null ||
					!Objects.equals(demandanteNovo.getDemanda().getId(), demandanteOld.getDemanda().getId())) {
				Optional<Demanda> optDemanda = this.demandaRepository.findById(demandanteNovo.getDemanda().getId());
				if (optDemanda.isPresent()) {
					demandanteNovo.setDemanda(optDemanda.get());
				} else {
					Demanda demanda = this.demandaRepository.save(demandanteNovo.getDemanda());
					demandanteNovo.setDemanda(demanda);
				}
			} else {
				demandanteNovo.setDemanda(demandanteOld.getDemanda());
			}

			return this.demandanteRepository.save(demandanteNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Demandante> findAll() {
		return this.demandanteRepository.findAll();
	}

	public Demandante findById(long idDemandante) {
		Optional<Demandante> optionalDemandante = this.demandanteRepository.findById(idDemandante);
		if (optionalDemandante.isPresent())
			return optionalDemandante.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Demandante deleteById(long idDemandante) {
		Optional<Demandante> optionalDemandante = this.demandanteRepository.findById(idDemandante);
		if (optionalDemandante.isPresent()) {
			Demandante demandante = optionalDemandante.get();

			// Inicializar as coleções necessárias
			Hibernate.initialize(demandante.getDemanda());

			// Deletar o Demandante
			this.demandanteRepository.deleteById(idDemandante);
			return demandante;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
