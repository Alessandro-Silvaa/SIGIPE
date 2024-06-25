package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import app.entity.Instituicao;
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
	private DemandanteRepository demandanteRepository;

	@Autowired
	InstituicaoRepository instituicaoRepository;

	public Demandante save(Demandante demandante) {
		return this.demandanteRepository.save(demandante);
	}

	@Transactional
	public Demandante update(long id, Demandante demandanteNovo) {
		Optional<Demandante> optDemandante = this.demandanteRepository.findById(id);
		if (optDemandante.isPresent()) {
			Demandante demandanteOld = optDemandante.get();
			demandanteNovo.setId(id);

			System.out.println(demandanteNovo.getInstituicao().getId());
			System.out.println(demandanteNovo.getInstituicao().getNome());

			if (demandanteOld.getInstituicao() == null ||
					!Objects.equals(demandanteNovo.getInstituicao().getId(), demandanteOld.getInstituicao().getId())) {
				Optional<Instituicao> optInstituicao = this.instituicaoRepository.findById(demandanteNovo.getInstituicao().getId());
				if (optInstituicao.isPresent()) {
					demandanteNovo.setInstituicao(optInstituicao.get());
				} else {
					Instituicao instituicao = this.instituicaoRepository.save(demandanteNovo.getInstituicao());
					demandanteNovo.setInstituicao(instituicao);
				}
			} else {
				demandanteNovo.setInstituicao(demandanteOld.getInstituicao());
			}

			demandanteNovo.setDemandas(demandanteOld.getDemandas());

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
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(demandante.getDemandas());
	        Hibernate.initialize(demandante.getInstituicao());
			
			this.demandanteRepository.deleteById(idDemandante);
			return demandante;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
