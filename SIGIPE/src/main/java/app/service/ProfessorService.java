package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Professor;
import app.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Professor save(Professor professor) {
		return this.professorRepository.save(professor);
	}

	public Professor update(long id, Professor professor) {
		professor.setId(id);
		return this.professorRepository.save(professor);
	}

	public List<Professor> findAll() {
		return this.professorRepository.findAll();
	}

	public Professor findById(long idProfessor) {
		return this.professorRepository.findById(idProfessor).get();
	}

	public Professor deleteById(long idProfessor) {
		Professor professor = findById(idProfessor);
		if(professor != null) {
			this.professorRepository.deleteById(idProfessor);
			return professor;			
		}
		throw new RuntimeException();
	}

}
