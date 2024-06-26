package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import app.entity.Demanda;
import app.entity.TipoInstituicao;
import app.repository.DemandaRepository;
import app.repository.TipoInstituicaoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Instituicao;
import app.repository.InstituicaoRepository;
import jakarta.transaction.Transactional;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private TipoInstituicaoRepository tipoInstituicaoRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	@Transactional
	public Instituicao save(Instituicao instituicao) {
		// Verificar e associar Demanda
		if (instituicao.getDemanda() != null && instituicao.getDemanda().getId() != 0) {
			Optional<Demanda> optDemanda = this.demandaRepository.findById(instituicao.getDemanda().getId());
			if (optDemanda.isPresent()) {
				instituicao.setDemanda(optDemanda.get());
			} else {
				Demanda demanda = this.demandaRepository.save(instituicao.getDemanda());
				instituicao.setDemanda(demanda);
			}
		}

		// Verificar e associar TipoInstituicao
		if (instituicao.getTipo() != null && instituicao.getTipo().getId() != 0) {
			Optional<TipoInstituicao> optTipoInstituicao = this.tipoInstituicaoRepository.findById(instituicao.getTipo().getId());
			if (optTipoInstituicao.isPresent()) {
				instituicao.setTipo(optTipoInstituicao.get());
			} else {
				TipoInstituicao tipoInstituicao = this.tipoInstituicaoRepository.save(instituicao.getTipo());
				instituicao.setTipo(tipoInstituicao);
			}
		}

		return this.instituicaoRepository.save(instituicao);
	}


	@Transactional
	public Instituicao update(long id, Instituicao instituicaoNovo) {
		Optional<Instituicao> optInstituicao = this.instituicaoRepository.findById(id);
		if (optInstituicao.isPresent()) {
			Instituicao instituicaoOld = optInstituicao.get();
			instituicaoNovo.setId(id);

			// Verificar e associar Demanda
			if (instituicaoOld.getDemanda() == null ||
					!Objects.equals(instituicaoNovo.getDemanda().getId(), instituicaoOld.getDemanda().getId())) {
				Optional<Demanda> optDemanda = this.demandaRepository.findById(instituicaoNovo.getDemanda().getId());
				if (optDemanda.isPresent()) {
					instituicaoNovo.setDemanda(optDemanda.get());
				} else {
					Demanda demanda = this.demandaRepository.save(instituicaoNovo.getDemanda());
					instituicaoNovo.setDemanda(demanda);
				}
			} else {
				instituicaoNovo.setDemanda(instituicaoOld.getDemanda());
			}

			// Verificar e associar TipoInstituicao
			if (instituicaoOld.getTipo() == null ||
					!Objects.equals(instituicaoNovo.getTipo().getId(), instituicaoOld.getTipo().getId())) {
				Optional<TipoInstituicao> optTipoInstituicao = this.tipoInstituicaoRepository.findById(instituicaoNovo.getTipo().getId());
				if (optTipoInstituicao.isPresent()) {
					instituicaoNovo.setTipo(optTipoInstituicao.get());
				} else {
					TipoInstituicao tipoInstituicao = this.tipoInstituicaoRepository.save(instituicaoNovo.getTipo());
					instituicaoNovo.setTipo(tipoInstituicao);
				}
			} else {
				instituicaoNovo.setTipo(instituicaoOld.getTipo());
			}

			// Salvar a instituição atualizada
			return this.instituicaoRepository.save(instituicaoNovo);
		}
		throw new RuntimeException("Instituição não encontrada para o ID: " + id);
	}

	public List<Instituicao> findAll() {
		return this.instituicaoRepository.findAll();
	}

	public Instituicao findById(long idInstituicao) {
		Optional<Instituicao> optionalInstituicao = this.instituicaoRepository.findById(idInstituicao);
		if (optionalInstituicao.isPresent())
			return optionalInstituicao.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Instituicao deleteById(long idInstituicao) {
		Optional<Instituicao> optionalInstituicao = this.instituicaoRepository.findById(idInstituicao);
		if (optionalInstituicao.isPresent()) {
			Instituicao instituicao = optionalInstituicao.get();
			
			// Inicialize as coleções necessárias
	        //Hibernate.initialize(instituicao.getDemandantes());
	        Hibernate.initialize(instituicao.getDemanda());
	        Hibernate.initialize(instituicao.getTipo());
			
			this.instituicaoRepository.deleteById(idInstituicao);
			return instituicao;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
