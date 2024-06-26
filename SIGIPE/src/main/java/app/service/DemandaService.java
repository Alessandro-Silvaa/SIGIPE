package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import app.entity.Demandante;
import app.entity.Instituicao;
import app.entity.StatusDemanda;
import app.repository.DemandanteRepository;
import app.repository.InstituicaoRepository;
import app.repository.StatusDemandaRepository;
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

	@Autowired
	InstituicaoRepository instituicaoRepository;

	@Autowired
	DemandanteRepository demandanteRepository;

	@Autowired
	StatusDemandaRepository statusDemandaRepository;

	public Demanda save(Demanda demanda) {
		return this.demandaRepository.save(demanda);
	}

	@Transactional
	public Demanda update(long id, Demanda demandaNovo) {
		Optional<Demanda> optDemanda = this.demandaRepository.findById(id);
		if (optDemanda.isPresent()) {
			Demanda demandaOld = optDemanda.get();
			demandaNovo.setId(id);

			try {
				// Verificação e atualização da Instituição
				if (demandaNovo.getInstituicao() !=  null&&
						(demandaOld.getInstituicao() == null ||
								!Objects.equals(demandaNovo.getInstituicao().getId(), demandaOld.getInstituicao().getId()))) {
					Optional<Instituicao> optInstituicao = this.instituicaoRepository.findById(demandaNovo.getInstituicao().getId());
					demandaNovo.setInstituicao(optInstituicao.orElseThrow(() ->
							new RuntimeException("Instituição não encontrada com ID: " + demandaNovo.getInstituicao().getId())));
				} else {
					demandaNovo.setInstituicao(demandaOld.getInstituicao());
				}

				// Verificação e atualização do Demandante
				if (demandaNovo.getDemandante() != null &&
						(demandaOld.getDemandante() == null ||
								!Objects.equals(demandaNovo.getDemandante().getId(), demandaOld.getDemandante().getId()))) {
					Optional<Demandante> optDemandante = this.demandanteRepository.findById(demandaNovo.getDemandante().getId());
					demandaNovo.setDemandante(optDemandante.orElseThrow(() ->
							new RuntimeException("Demandante não encontrado com ID: " + demandaNovo.getDemandante().getId())));
				} else {
					demandaNovo.setDemandante(demandaOld.getDemandante());
				}

				// Verificação e atualização do Status
				if (demandaNovo.getStatus() != null &&
						(demandaOld.getStatus() == null ||
								!Objects.equals(demandaNovo.getStatus().getId(), demandaOld.getStatus().getId()))) {
					Optional<StatusDemanda> optStatusDemanda = this.statusDemandaRepository.findById(demandaNovo.getStatus().getId());
					demandaNovo.setStatus(optStatusDemanda.orElseThrow(() ->
							new RuntimeException("Status de Demanda não encontrado com ID: " + demandaNovo.getStatus().getId())));
				} else {
					demandaNovo.setStatus(demandaOld.getStatus());
				}

				// Manter as associações existentes
				demandaNovo.setCursos(demandaOld.getCursos());
				demandaNovo.setGruposInscritos(demandaOld.getGruposInscritos());
				demandaNovo.setGruposSolicitacao(demandaOld.getGruposSolicitacao());
				demandaNovo.setTurmas(demandaOld.getTurmas());

				return this.demandaRepository.save(demandaNovo);
			} catch (RuntimeException e) {
				throw new RuntimeException("Erro ao atualizar a demanda: " + e.getMessage());
			}
		}
		throw new RuntimeException("Demanda não encontrada com ID: " + id);
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
