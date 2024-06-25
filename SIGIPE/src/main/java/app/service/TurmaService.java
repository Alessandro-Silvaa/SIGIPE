package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Curso;
import app.entity.Turma;
import app.repository.CursoRepository;
import app.repository.TurmaRepository;
import jakarta.transaction.Transactional;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	public Turma save(Turma turma) {
		return this.turmaRepository.save(turma);
	}

	@Transactional
	public Turma update(long id, Turma turmaNovo) {
		Optional<Turma> optTurma = this.turmaRepository.findById(id);
		if (optTurma.isPresent()) {
			Turma turmaOld = optTurma.get();
			turmaNovo.setId(id);

			System.out.println(turmaNovo.getCurso().getId());
			System.out.println(turmaNovo.getCurso().getNome());
			System.out.println(turmaNovo.getCurso().getQuantidadePeriodos());

			if (turmaOld.getCurso() == null ||
					!Objects.equals(turmaNovo.getCurso().getId(), turmaOld.getCurso().getId())) {
				Optional<Curso> optCurso = this.cursoRepository.findById(turmaNovo.getCurso().getId());
				if (optCurso.isPresent()) {
					turmaNovo.setCurso(optCurso.get());
				} else {
					Curso curso = this.cursoRepository.save(turmaNovo.getCurso());
					turmaNovo.setCurso(curso);
				}
			} else {
				turmaNovo.setCurso(turmaOld.getCurso());
			}

			turmaNovo.setAlunos(turmaOld.getAlunos());
			turmaNovo.setDemandas(turmaOld.getDemandas());
			turmaNovo.setProfessores(turmaOld.getProfessores());

			return this.turmaRepository.save(turmaNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Turma> findAll() {
		return this.turmaRepository.findAll();
	}

	public Turma findById(long idTurma) {
		Optional<Turma> optionalTurma = this.turmaRepository.findById(idTurma);
		if (optionalTurma.isPresent())
			return optionalTurma.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Turma deleteById(long idTurma) {
		Optional<Turma> optionalTurma = this.turmaRepository.findById(idTurma);
		if (optionalTurma.isPresent()) {
			Turma turma = optionalTurma.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(turma.getAlunos());
	        Hibernate.initialize(turma.getProfessores());
	        Hibernate.initialize(turma.getDemandas());
	        Hibernate.initialize(turma.getCurso());
			
			this.turmaRepository.deleteById(idTurma);
			return turma;
		}
		throw new RuntimeException("Id não encontrado.");
	}
	
	public List<Turma> gerarTurmas(long idCurso) {
		Optional<Curso> optionalCurso = this.cursoRepository.findById(idCurso);
		if(optionalCurso.isPresent()) {
			Curso curso = optionalCurso.get();
			ArrayList<Turma> turmas = new ArrayList<Turma>();
			for(int i = 1; i <= curso.getQuantidadePeriodos(); i++) {
				Turma turma = new Turma();
				turma.setPeriodoCurso(i);
				turma.setCurso(curso);
				turmas.add(turma);
			}
			return this.turmaRepository.saveAll(turmas);
		}
		throw new RuntimeException();
	}
}
