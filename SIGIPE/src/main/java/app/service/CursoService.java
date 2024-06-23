package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Curso;
import app.repository.CursoRepository;
import jakarta.transaction.Transactional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	public Curso save(Curso curso) {
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
