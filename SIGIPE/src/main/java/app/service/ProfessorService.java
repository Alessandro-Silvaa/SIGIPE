package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.Curso;
import app.entity.Turma;
import app.repository.CursoRepository;
import app.repository.TurmaRepository;
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

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Transactional
	public Professor save(Professor professor) {
		// Verificar e associar Turma
		if (professor.getTurma() != null && professor.getTurma().getId() != 0) {
			Turma turma = turmaRepository.findById(professor.getTurma().getId())
					.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
			professor.setTurma(turma);
		}

		// Verificar e associar Curso
		if (professor.getCurso() != null && professor.getCurso().getId() != 0) {
			Curso curso = cursoRepository.findById(professor.getCurso().getId())
					.orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
			professor.setCurso(curso);
		}

		return professorRepository.save(professor);
	}

	@Transactional
	public Professor update(long id, Professor professorNovo) {
		Optional<Professor> optProfessor = this.professorRepository.findById(id);
		if (optProfessor.isPresent()) {
			Professor professorOld = optProfessor.get();

			// Atualiza os campos do professor antigo com os novos valores
			professorOld.setNome(professorNovo.getNome());
			professorOld.setCpf(professorNovo.getCpf());

			// Verifica e associa Turma existente
			if (professorNovo.getTurma() != null && professorNovo.getTurma().getId() != 0) {
				Turma turma = turmaRepository.findById(professorNovo.getTurma().getId())
						.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
				professorOld.setTurma(turma);
			} else {
				professorOld.setTurma(null); // Limpa a associação se não houver nova turma
			}

			// Verifica e associa Curso existente
			if (professorNovo.getCurso() != null && professorNovo.getCurso().getId() != 0) {
				Curso curso = cursoRepository.findById(professorNovo.getCurso().getId())
						.orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
				professorOld.setCurso(curso);
			} else {
				professorOld.setCurso(null); // Limpa a associação se não houver novo curso
			}

			return this.professorRepository.save(professorOld);
		}
		throw new RuntimeException("Id não encontrado.");
	}


	public List<Professor> findAll() {

		List<Professor> lista = this.professorRepository.findAll();
		lista.forEach((l) -> {
			System.out.println(l.getCurso().getNome());
		});
		return lista;
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
