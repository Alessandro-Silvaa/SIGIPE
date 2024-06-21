package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Curso;
import app.entity.Turma;
import app.repository.CursoRepository;
import app.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	public Turma save(Turma turma) {
		return this.turmaRepository.save(turma);
	}

	public Turma update(long id, Turma turma) {
		turma.setId(id);
		return this.turmaRepository.save(turma);
	}

	public List<Turma> findAll() {
		return this.turmaRepository.findAll();
	}

	public Turma findById(long idTurma) {
		return this.turmaRepository.findById(idTurma).get();
	}

	public Turma deleteById(long idTurma) {
		Turma turma = findById(idTurma);
		if(turma != null) {
			this.turmaRepository.deleteById(idTurma);
			return turma;			
		}
		throw new RuntimeException();
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
