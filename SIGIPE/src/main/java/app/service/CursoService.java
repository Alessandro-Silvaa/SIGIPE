package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.*;
import app.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CoordenadorCursoRepository coordenadorCursoRepository;

	@Autowired
	private TurmaRepository turmasRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	public Curso save(Curso curso) {return this.cursoRepository.save(curso);}

	@Transactional
	public Curso update(long id, Curso cursoNovo) {
		Optional<Curso> optCurso = this.cursoRepository.findById(id);
		if (optCurso.isPresent()) {
			Curso cursoOld = optCurso.get();
			cursoNovo.setId(id);
			/*
			// Verifica e associa Turma existente
			if (cursoNovo.getTurmas() != null && cursoNovo.getTurmas().isEmpty()) {
				List<Turma> turmas = turmasRepository.findById(cursoNovo.getTurmas().isEmpty())
						.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
				cursoNovo.setTurmas(turmas);
			} else {
				cursoNovo.setTurmas(null); // Limpa a associação se não houver nova turmas
			}

			// Verifica e associa Curso existente
			if (cursoNovo.getCurso() != null && cursoNovo.getCurso().getId() != 0) {
				Curso curso = cursoRepository.findById(cursoNovo.getCurso().getId())
						.orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
				cursoOld.setCurso(curso);
			} else {
				cursoOld.setCurso(null); // Limpa a associação se não houver novo curso
			}
*/
			return this.cursoRepository.save(cursoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}


	public List<Curso> findAll() {
		return this.cursoRepository.findAll();
	}

	public Curso findById(long id) {
		return this.cursoRepository.findById(id).get();
	}

	@Transactional
	public Curso deleteById(long id) {
		Optional<Curso> optionalCurso = this.cursoRepository.findById(id);
		if (optionalCurso.isPresent()) {
			Curso curso = optionalCurso.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(curso.getTurmas());
	        Hibernate.initialize(curso.getAlunos());
	        Hibernate.initialize(curso.getProfessores());
	        Hibernate.initialize(curso.getCoordenadores());
	        Hibernate.initialize(curso.getDemandas());
			
			this.cursoRepository.deleteById(id);
			return curso;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
