package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.StatusPessoa;
import app.repository.StatusPessoaRepository;

@Service
public class StatusPessoaService {

	@Autowired
	private StatusPessoaRepository statusPessoaRepository;

	public StatusPessoa save(StatusPessoa statusPessoa) {
		return this.statusPessoaRepository.save(statusPessoa);
	}

	public StatusPessoa update(long id, StatusPessoa statusPessoa) {
		statusPessoa.setId(id);
		return this.statusPessoaRepository.save(statusPessoa);
	}

	public List<StatusPessoa> findAll() {
		return this.statusPessoaRepository.findAll();
	}

	public StatusPessoa findById(long idStatusPessoa) {
		return this.statusPessoaRepository.findById(idStatusPessoa).get();
	}

	public StatusPessoa deleteById(long idStatusPessoa) {
		StatusPessoa statusPessoa = findById(idStatusPessoa);
		if(statusPessoa != null) {
			this.statusPessoaRepository.deleteById(idStatusPessoa);
			return statusPessoa;			
		}
		throw new RuntimeException();
	}

}
