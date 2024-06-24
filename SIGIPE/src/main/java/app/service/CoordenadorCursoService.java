package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.CoordenadorCurso;
import app.entity.Curso;
import app.repository.CoordenadorCursoRepository;
import app.repository.CursoRepository;
import jakarta.transaction.Transactional;

@Service
public class CoordenadorCursoService {

	@Autowired
	private CoordenadorCursoRepository coordenadorCursoRepository;

	@Autowired
	private CursoRepository cursoRespository;

	private Logger logger = LoggerFactory.getLogger(CoordenadorCursoService.class);

	@Transactional
	public CoordenadorCurso save(CoordenadorCurso coordenadorCurso) {
		logger.debug("CoordenadorCurso recebido para salvar: " + coordenadorCurso);
		Optional<Curso> optCurso = this.cursoRespository.findById(coordenadorCurso.getCurso().getId());
		if(optCurso.isPresent()) {
			coordenadorCurso.setCurso(optCurso.get());
			coordenadorCurso = this.coordenadorCursoRepository.save(coordenadorCurso);
			return coordenadorCurso;
		}
		throw new RuntimeException("Curso não encontrado");
	}


	@Transactional
	public CoordenadorCurso update(long id, CoordenadorCurso coordenadorCursoNovo) {
		Optional<CoordenadorCurso> optCoordenadorCurso = this.coordenadorCursoRepository.findById(id);
		if (optCoordenadorCurso.isPresent()) {
			CoordenadorCurso coordenadorCursoOld = optCoordenadorCurso.get();
			coordenadorCursoNovo.setId(id);

			System.out.println(coordenadorCursoNovo.getCurso().getId());
			System.out.println(coordenadorCursoNovo.getCurso().getNome());
			System.out.println(coordenadorCursoNovo.getCurso().getQuantidadePeriodos());

			if (coordenadorCursoOld.getCurso() == null ||
					!Objects.equals(coordenadorCursoNovo.getCurso().getId(), coordenadorCursoOld.getCurso().getId())) {
				Optional<Curso> optCurso = this.cursoRespository.findById(coordenadorCursoNovo.getCurso().getId());
				if (optCurso.isPresent()) {
					coordenadorCursoNovo.setCurso(optCurso.get());
				} else {
					Curso curso = this.cursoRespository.save(coordenadorCursoNovo.getCurso());
					coordenadorCursoNovo.setCurso(curso);
				}
			} else {
				coordenadorCursoNovo.setCurso(coordenadorCursoOld.getCurso());
			}

			return this.coordenadorCursoRepository.save(coordenadorCursoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}


	public List<CoordenadorCurso> findAll() {
		return this.coordenadorCursoRepository.findAll();
	}

	@Transactional
	public CoordenadorCurso findById(long idCoordenadorCurso) {
		Optional<CoordenadorCurso> optionalCoordenadorCurso = this.coordenadorCursoRepository.findById(idCoordenadorCurso);
		if (optionalCoordenadorCurso.isPresent())
			return optionalCoordenadorCurso.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public CoordenadorCurso deleteById(long idCoordenadorCurso) {
		Optional<CoordenadorCurso> optionalCoordenadorCurso = this.coordenadorCursoRepository.findById(idCoordenadorCurso);
		if (optionalCoordenadorCurso.isPresent()) {
			CoordenadorCurso coordenadorCurso = optionalCoordenadorCurso.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(coordenadorCurso.getCurso());
			
			this.coordenadorCursoRepository.deleteById(idCoordenadorCurso);
			return coordenadorCurso;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
