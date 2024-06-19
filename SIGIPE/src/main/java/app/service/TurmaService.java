package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Turma;
import app.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

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

}
