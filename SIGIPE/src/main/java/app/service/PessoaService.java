package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Pessoa;
import app.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa save(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}

	public Pessoa update(long id, Pessoa pessoa) {
		pessoa.setId(id);
		return this.pessoaRepository.save(pessoa);
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa findById(long idPessoa) {
		return this.pessoaRepository.findById(idPessoa).get();
	}

	public Pessoa deleteById(long idPessoa) {
		Pessoa pessoa = findById(idPessoa);
		if(pessoa != null) {
			this.pessoaRepository.deleteById(idPessoa);
			return pessoa;			
		}
		throw new RuntimeException();
	}

}
