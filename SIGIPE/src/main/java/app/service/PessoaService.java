package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Pessoa;
import app.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa save(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}

	public Pessoa update(long id, Pessoa pessoaNovo) {
		Optional<Pessoa> optPessoa = this.pessoaRepository.findById(id);
		if(optPessoa.isPresent()) {
			pessoaNovo.setId(id);
			
			return this.pessoaRepository.save(pessoaNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa findById(long idPessoa) {
		Optional<Pessoa> optionalPessoa = this.pessoaRepository.findById(idPessoa);
		if (optionalPessoa.isPresent())
			return optionalPessoa.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Pessoa deleteById(long idPessoa) {
		Optional<Pessoa> optionalPessoa = this.pessoaRepository.findById(idPessoa);
		if (optionalPessoa.isPresent()) {
			Pessoa pessoa = optionalPessoa.get();
			
			this.pessoaRepository.deleteById(idPessoa);
			return pessoa;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
