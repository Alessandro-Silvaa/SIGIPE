package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import app.entity.Curso;
import app.entity.Grupo;
import app.entity.Turma;
import app.repository.CursoRepository;
import app.repository.GrupoRepository;
import app.repository.TurmaRepository;
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

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Transactional
	public Aluno save(Aluno aluno) {
		// Verificar e associar Turma
		if (aluno.getTurma() != null && aluno.getTurma().getId() != 0) {
			Optional<Turma> optTurma = this.turmaRepository.findById(aluno.getTurma().getId());
			if (optTurma.isPresent()) {
				aluno.setTurma(optTurma.get());
			} else {
				Turma turma = this.turmaRepository.save(aluno.getTurma());
				aluno.setTurma(turma);
			}
		}

		// Verificar e associar Curso
		if (aluno.getCurso() != null && aluno.getCurso().getId() != 0) {
			Optional<Curso> optCurso = this.cursoRepository.findById(aluno.getCurso().getId());
			if (optCurso.isPresent()) {
				aluno.setCurso(optCurso.get());
			} else {
				Curso curso = this.cursoRepository.save(aluno.getCurso());
				aluno.setCurso(curso);
			}
		}

		// Verificar e associar Grupo
		if (aluno.getGrupo() != null && aluno.getGrupo().getId() != 0) {
			Optional<Grupo> optGrupo = this.grupoRepository.findById(aluno.getGrupo().getId());
			if (optGrupo.isPresent()) {
				aluno.setGrupo(optGrupo.get());
			} else {
				Grupo grupo = this.grupoRepository.save(aluno.getGrupo());
				aluno.setGrupo(grupo);
			}
		}

		return this.alunoRepository.save(aluno);
	}



	@Transactional
	public Aluno update(long id, Aluno alunoNovo) {
		Optional<Aluno> optAluno = this.alunoRepository.findById(id);
		if (optAluno.isPresent()) {
			Aluno alunoOld = optAluno.get();
			alunoNovo.setId(id);

			// Verificar e associar Turma
			if (alunoNovo.getTurma() != null) {

					Optional<Turma> optTurma = this.turmaRepository.findById(alunoNovo.getTurma().getId());
					if (optTurma.isPresent()) {
						alunoNovo.setTurma(optTurma.get());
					} else {
						throw new RuntimeException("Turma não encontrada");
					}
				} else {
					alunoNovo.setTurma(alunoOld.getTurma());
				}

			// Verificar e associar Curso
			if (alunoNovo.getCurso() != null) {
					Optional<Curso> optCurso = this.cursoRepository.findById(alunoNovo.getCurso().getId());
					if (optCurso.isPresent()) {
						alunoNovo.setCurso(optCurso.get());
					} else {

						throw new RuntimeException("Curso não encontrado");
					}
				}
				else {
					alunoNovo.setCurso(alunoOld.getCurso());
				}

			// Verificar e associar Grupo
			if (alunoNovo.getGrupo() != null) {
					Optional<Grupo> optGrupo = this.grupoRepository.findById(alunoNovo.getGrupo().getId());
					if (optGrupo.isPresent()) {
						alunoNovo.setGrupo(optGrupo.get());
					} else {
						throw new RuntimeException("Grupo não encontrado");
					}
				}
				else {
					alunoNovo.setGrupo(alunoOld.getGrupo());
				}

			// Salvar o aluno atualizado
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
