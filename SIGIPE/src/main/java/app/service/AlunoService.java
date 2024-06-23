package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Aluno;
import app.repository.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno save(Aluno aluno) {
		return this.alunoRepository.save(aluno);
	}

	public Aluno update(long id, Aluno alunoNovo) {
		Optional<Aluno> optAluno = this.alunoRepository.findById(id);
		if(optAluno.isPresent()) {
			Aluno alunoOld = optAluno.get();
			alunoNovo.setId(id);
			alunoNovo.setCurso(alunoOld.getCurso());
			alunoNovo.setGrupo(alunoOld.getGrupo());
			alunoNovo.setTurma(alunoOld.getTurma());
			
			return this.alunoRepository.save(alunoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Aluno> findAll() {
		return this.alunoRepository.findAll();
	}

	public Aluno findById(long idAluno) {
		Optional<Aluno> optionalAluno = this.alunoRepository.findById(idAluno);
		if (optionalAluno.isPresent())
			return optionalAluno.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Aluno deleteById(long idAluno) {
		Optional<Aluno> optionalAluno = this.alunoRepository.findById(idAluno);
		if (optionalAluno.isPresent()) {
			Aluno aluno = optionalAluno.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(aluno.getCurso());
	        Hibernate.initialize(aluno.getGrupo());
	        Hibernate.initialize(aluno.getTurma());
			
			this.alunoRepository.deleteById(idAluno);
			return aluno;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
