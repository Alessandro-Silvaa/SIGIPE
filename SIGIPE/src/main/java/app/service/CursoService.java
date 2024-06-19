package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Curso;
import app.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	public Curso save(Curso curso) {
		return this.cursoRepository.save(curso);
	}

	public Curso update(long id, Curso curso) {
		curso.setId(id);
		return this.cursoRepository.save(curso);
	}

	public List<Curso> findAll() {
		return this.cursoRepository.findAll();
	}

	public Curso findById(long id) {
		return this.cursoRepository.findById(id).get();
	}

	public Curso deleteById(long id) {
		Optional<Curso> optionalCurso = this.cursoRepository.findById(id);
		if (optionalCurso.isPresent()) {
			Curso curso = optionalCurso.get();
			this.cursoRepository.deleteById(id);
			return curso;
		}
		throw new RuntimeException("Curso not found with id: " + id);
	}
}
