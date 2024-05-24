package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Aluno;
import app.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public String save(Aluno aluno) {
		this.alunoRepository.save(aluno);
		return aluno.getNome()+ " salvo com sucesso";
	}

	public String update(long id, Aluno aluno) {
		aluno.setIdPessoa(id);
		this.alunoRepository.save(aluno);
		return aluno.getNome()+ " atualizado com sucesso";
	}

	public List<Aluno> findAll(){
		return this.alunoRepository.findAll();
	}

	public Aluno findById(long idAluno) {

		Aluno aluno = this.alunoRepository.findById(idAluno).get();
		return aluno;

	}

	public String deleteById(long idAluno) {
		this.alunoRepository.deleteById(idAluno);
		return "Aluno deletado com sucesso!";

	}

}
