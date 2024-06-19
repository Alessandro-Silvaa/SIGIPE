package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.CoordenadorCurso;
import app.repository.CoordenadorCursoRepository;

@Service
public class CoordenadorCursoService {

	@Autowired
	private CoordenadorCursoRepository coordenadorCursoRepository;

	public CoordenadorCurso save(CoordenadorCurso coordenadorCurso) {
		return this.coordenadorCursoRepository.save(coordenadorCurso);
	}

	public CoordenadorCurso update(long id, CoordenadorCurso coordenadorCurso) {
		coordenadorCurso.setId(id);
		return this.coordenadorCursoRepository.save(coordenadorCurso);
	}

	public List<CoordenadorCurso> findAll() {
		return this.coordenadorCursoRepository.findAll();
	}

	public CoordenadorCurso findById(long idCoordenadorCurso) {
		return this.coordenadorCursoRepository.findById(idCoordenadorCurso).get();
	}

	public CoordenadorCurso deleteById(long idCoordenadorCurso) {
		CoordenadorCurso coordenadorCurso = findById(idCoordenadorCurso);
		if(coordenadorCurso != null) {
			this.coordenadorCursoRepository.deleteById(idCoordenadorCurso);
			return coordenadorCurso;			
		}
		throw new RuntimeException();
	}

}
