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

	public String save(Professor professor) {
		this.professorRepository.save(professor);
		return professor.getNome()+ " salvo com sucesso";
	}

	public String update(long id, Professor professor) {
		professor.setIdPessoa(id);
		this.professorRepository.save(professor);
		return professor.getNome()+ " atualizado com sucesso";
	}

	public List<Professor> findAll(){
		return this.professorRepository.findAll();
	}

	public Professor findById(long idProfessor) {

		Professor professor = this.professorRepository.findById(idProfessor).get();
		return professor;

	}

	public String deleteById(long idProfessor) {
		this.professorRepository.deleteById(idProfessor);
		return "Professor deletado com sucesso!";

	}

}
