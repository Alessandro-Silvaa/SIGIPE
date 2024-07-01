package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Instituicao;
import app.entity.TipoInstituicao;
import app.repository.DemandaRepository;
import app.repository.InstituicaoRepository;
import app.repository.TipoInstituicaoRepository;
import jakarta.transaction.Transactional;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Autowired
	private TipoInstituicaoRepository tipoInstituicaoRepository;


	public Instituicao save(Instituicao instituicao) {

		String nome = instituicao.getNome();

		return this.instituicaoRepository.save(instituicao);
	}


	@Transactional
	public Instituicao update(Instituicao instituicao,long id) {
		Optional<Instituicao> optInstituicao = this.instituicaoRepository.findById(id);
		if (optInstituicao.isPresent()) {
			Instituicao existingInstituicao = optInstituicao.get();
			// Atualizar os campos necessários
			existingInstituicao.setNome(instituicao.getNome());
			existingInstituicao.setCidade(instituicao.getCidade());
			existingInstituicao.setTipo(instituicao.getTipo());
			return this.instituicaoRepository.save(existingInstituicao);
		} else {
			throw new RuntimeException("Instituição não encontrada.");
		}
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
	        Hibernate.initialize(instituicao.getTipo());
			
			this.instituicaoRepository.deleteById(idInstituicao);
			return instituicao;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
