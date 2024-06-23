package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Professor;
import app.repository.ProfessorRepository;
import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Professor save(Professor professor) {
		return this.professorRepository.save(professor);
	}

	public Professor update(long id, Professor professorNovo) {
		Optional<Professor> optProfessor = this.professorRepository.findById(id);
		if(optProfessor.isPresent()) {
			Professor professorOld = optProfessor.get();
			professorNovo.setId(id);
			professorNovo.setCurso(professorOld.getCurso());
			professorNovo.setTurma(professorOld.getTurma());
			
			return this.professorRepository.save(professorNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Professor> findAll() {
		return this.professorRepository.findAll();
	}

	public Professor findById(long idProfessor) {
		Optional<Professor> optionalProfessor = this.professorRepository.findById(idProfessor);
		if (optionalProfessor.isPresent())
			return optionalProfessor.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Professor deleteById(long idProfessor) {
		Optional<Professor> optionalProfessor = this.professorRepository.findById(idProfessor);
		if (optionalProfessor.isPresent()) {
			Professor professor = optionalProfessor.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(professor.getCurso());
	        Hibernate.initialize(professor.getTurma());
			
			this.professorRepository.deleteById(idProfessor);
			return professor;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
