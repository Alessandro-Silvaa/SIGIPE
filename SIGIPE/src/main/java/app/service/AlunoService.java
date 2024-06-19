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

	public Aluno save(Aluno aluno) {
		return this.alunoRepository.save(aluno);
	}

	public Aluno update(long id, Aluno aluno) {
		aluno.setId(id);
		return this.alunoRepository.save(aluno);
	}

	public List<Aluno> findAll() {
		return this.alunoRepository.findAll();
	}

	public Aluno findById(long idAluno) {
		return this.alunoRepository.findById(idAluno).get();
	}

	public Aluno deleteById(long idAluno) {
		Aluno aluno = findById(idAluno);
		if(aluno != null) {
			this.alunoRepository.deleteById(idAluno);
			return aluno;			
		}
		throw new RuntimeException();
	}

}
