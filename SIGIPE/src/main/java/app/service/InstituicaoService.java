package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Instituicao;
import app.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	public Instituicao save(Instituicao instituicao) {
		return this.instituicaoRepository.save(instituicao);
	}

	public Instituicao update(long id, Instituicao instituicao) {
		instituicao.setId(id);
		return this.instituicaoRepository.save(instituicao);
	}

	public List<Instituicao> findAll() {
		return this.instituicaoRepository.findAll();
	}

	public Instituicao findById(long idInstituicao) {
		return this.instituicaoRepository.findById(idInstituicao).get();
	}

	public Instituicao deleteById(long idInstituicao) {
		Instituicao instituicao = findById(idInstituicao);
		if(instituicao != null) {
			this.instituicaoRepository.deleteById(idInstituicao);
			return instituicao;			
		}
		throw new RuntimeException();
	}

}
