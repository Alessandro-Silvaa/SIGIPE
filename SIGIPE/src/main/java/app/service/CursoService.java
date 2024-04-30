package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Curso;
import app.repository.CursoRepository;
import jakarta.validation.Valid;

@Service
public class CursoService {
	@Autowired
	CursoRepository cursoRepository;

	public List<Curso> findAll() {
		List<Curso> lista = this.cursoRepository.findAll();
		if (lista == null)
			throw new RuntimeException("Não há cursos cadastrados");
		return lista;
	}

	public Curso findById(long id) {
		Optional<Curso> optionalCurso = this.cursoRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if (optionalCurso == null)
			throw new RuntimeException("Curso não encontrado");
		return optionalCurso.get();
	}

	public void deleteById(long id) {
		if (findById(id) != null)
			this.cursoRepository.deleteById(id);
	}

	public void save(Curso curso) {
		if (curso == null)
			throw new RuntimeException("Chamada inválida");
		this.cursoRepository.save(curso);
	}

	public void update(@Valid long id, Curso curso) {
		if (curso == null)
			throw new RuntimeException("Chamada inválida");
		if (findById(id) != null) {
			curso.setIdCurso(id);
			this.cursoRepository.save(curso);
		}
	}
}
