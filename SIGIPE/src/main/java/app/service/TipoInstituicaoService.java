package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;
import jakarta.transaction.Transactional;

@Service
public class TipoInstituicaoService {

	@Autowired
	private TipoInstituicaoRepository tipoInstituicaoRepository;

	public TipoInstituicao save(TipoInstituicao tipoInstituicao) {
		return this.tipoInstituicaoRepository.save(tipoInstituicao);
	}

	public TipoInstituicao update(long id, TipoInstituicao tipoInstituicaoNovo) {
		Optional<TipoInstituicao> optTipoInstituicao = this.tipoInstituicaoRepository.findById(id);
		if(optTipoInstituicao.isPresent()) {
			TipoInstituicao tipoInstituicaoOld = optTipoInstituicao.get();
			tipoInstituicaoNovo.setId(id);
			tipoInstituicaoNovo.setInstituicoes(tipoInstituicaoOld.getInstituicoes());
			
			return this.tipoInstituicaoRepository.save(tipoInstituicaoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<TipoInstituicao> findAll() {
		return this.tipoInstituicaoRepository.findAll();
	}

	public TipoInstituicao findById(long idTipoInstituicao) {
		Optional<TipoInstituicao> optionalTipoInstituicao = this.tipoInstituicaoRepository.findById(idTipoInstituicao);
		if (optionalTipoInstituicao.isPresent())
			return optionalTipoInstituicao.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public TipoInstituicao deleteById(long idTipoInstituicao) {
		Optional<TipoInstituicao> optionalTipoInstituicao = this.tipoInstituicaoRepository.findById(idTipoInstituicao);
		if (optionalTipoInstituicao.isPresent()) {
			TipoInstituicao tipoInstituicao = optionalTipoInstituicao.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(tipoInstituicao.getInstituicoes());
			
			this.tipoInstituicaoRepository.deleteById(idTipoInstituicao);
			return tipoInstituicao;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
