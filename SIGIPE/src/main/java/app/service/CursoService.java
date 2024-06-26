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
	private ProfessorRepository professorRepository;

	@Autowired
	private CoordenadorCursoRepository coordenadorCursoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	@Transactional
	public Curso save(Curso curso) {
		// Verificar e associar Alunos
		if (curso.getAlunos() != null && !curso.getAlunos().isEmpty()) {
			for (Aluno aluno : curso.getAlunos()) {
				if (aluno.getId() != 0) {
					Optional<Aluno> optAluno = this.alunoRepository.findById(aluno.getId());
					if (optAluno.isPresent()) {
						aluno = optAluno.get();
					} else {
						aluno = this.alunoRepository.save(aluno);
					}
				} else {
					aluno = this.alunoRepository.save(aluno);
				}
			}
		}

		// Verificar e associar Professores
		if (curso.getProfessores() != null && !curso.getProfessores().isEmpty()) {
			for (Professor professor : curso.getProfessores()) {
				if (professor.getId() != 0) {
					Optional<Professor> optProfessor = this.professorRepository.findById(professor.getId());
					if (optProfessor.isPresent()) {
						professor = optProfessor.get();
					} else {
						professor = this.professorRepository.save(professor);
					}
				} else {
					professor = this.professorRepository.save(professor);
				}
			}
		}

		// Verificar e associar Coordenadores
		if (curso.getCoordenadores() != null && !curso.getCoordenadores().isEmpty()) {
			for (CoordenadorCurso coordenador : curso.getCoordenadores()) {
				if (coordenador.getId() != 0) {
					Optional<CoordenadorCurso> optCoordenador = this.coordenadorCursoRepository.findById(coordenador.getId());
					if (optCoordenador.isPresent()) {
						coordenador = optCoordenador.get();
					} else {
						coordenador = this.coordenadorCursoRepository.save(coordenador);
					}
				} else {
					coordenador = this.coordenadorCursoRepository.save(coordenador);
				}
			}
		}

		// Verificar e associar Turmas
		if (curso.getTurmas() != null && !curso.getTurmas().isEmpty()) {
			for (Turma turma : curso.getTurmas()) {
				if (turma.getId() != 0) {
					Optional<Turma> optTurma = this.turmaRepository.findById(turma.getId());
					if (optTurma.isPresent()) {
						turma = optTurma.get();
					} else {
						turma = this.turmaRepository.save(turma);
					}
				} else {
					turma = this.turmaRepository.save(turma);
				}
			}
		}

		// Verificar e associar Demandas
		if (curso.getDemandas() != null && !curso.getDemandas().isEmpty()) {
			for (Demanda demanda : curso.getDemandas()) {
				if (demanda.getId() != 0) {
					Optional<Demanda> optDemanda = this.demandaRepository.findById(demanda.getId());
					if (optDemanda.isPresent()) {
						demanda = optDemanda.get();
					} else {
						demanda = this.demandaRepository.save(demanda);
					}
				} else {
					demanda = this.demandaRepository.save(demanda);
				}
			}
		}

		return this.cursoRepository.save(curso);
	}

	public Curso update(long id, Curso cursoNovo) {
		Optional<Curso> optCurso = this.cursoRepository.findById(id);
		if(optCurso.isPresent()) {
			Curso cursoOld = optCurso.get();
			cursoNovo.setId(id);
			cursoNovo.setAlunos(cursoOld.getAlunos());
			cursoNovo.setProfessores(cursoOld.getProfessores());
			cursoNovo.setCoordenadores(cursoOld.getCoordenadores());
			cursoNovo.setDemandas(cursoOld.getDemandas());
			cursoNovo.setTurmas(cursoOld.getTurmas());
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
