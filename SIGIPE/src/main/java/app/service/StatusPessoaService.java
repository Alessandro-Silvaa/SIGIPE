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

	public String save(StatusPessoa statusPessoa) {
		this.statusPessoaRepository.save(statusPessoa);
		return statusPessoa.getNome()+ " salvo com sucesso";
	}

	public String update(long id, StatusPessoa statusPessoa) {
		statusPessoa.setIdStatusPessoa(id);
		this.statusPessoaRepository.save(statusPessoa);
		return statusPessoa.getNome()+ " atualizado com sucesso";
	}

	public List<StatusPessoa> findAll(){
		return this.statusPessoaRepository.findAll();
	}

	public StatusPessoa findById(long idStatusPessoa) {

		StatusPessoa statusPessoa = this.statusPessoaRepository.findById(idStatusPessoa).get();
		return statusPessoa;

	}

	public String deleteById(long idStatusPessoa) {
		this.statusPessoaRepository.deleteById(idStatusPessoa);
		return "StatusPessoa deletado com sucesso!";

	}

}
