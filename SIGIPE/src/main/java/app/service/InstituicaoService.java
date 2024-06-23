package app.service;

import java.util.List;
import java.util.Optional;

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

	public Instituicao save(Instituicao instituicao) {
		return this.instituicaoRepository.save(instituicao);
	}

	public Instituicao update(long id, Instituicao instituicaoNovo) {
		Optional<Instituicao> optInstituicao = this.instituicaoRepository.findById(id);
		if(optInstituicao.isPresent()) {
			Instituicao instituicaoOld = optInstituicao.get();
			instituicaoNovo.setId(id);
			instituicaoNovo.setDemandas(instituicaoOld.getDemandas());
			instituicaoNovo.setDemandantes(instituicaoOld.getDemandantes());
			instituicaoNovo.setTipo(instituicaoOld.getTipo());
			
			return this.instituicaoRepository.save(instituicaoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
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
	        Hibernate.initialize(instituicao.getDemandantes());
	        Hibernate.initialize(instituicao.getDemandas());
	        Hibernate.initialize(instituicao.getTipo());
			
			this.instituicaoRepository.deleteById(idInstituicao);
			return instituicao;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
