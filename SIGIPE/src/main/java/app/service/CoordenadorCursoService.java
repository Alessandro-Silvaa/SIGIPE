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

	public String save(CoordenadorCurso coordenadorCurso) {
		this.coordenadorCursoRepository.save(coordenadorCurso);
		return coordenadorCurso.getNome()+ " salvo com sucesso";
	}

	public String update(long id, CoordenadorCurso coordenadorCurso) {
		coordenadorCurso.setIdPessoa(id);
		this.coordenadorCursoRepository.save(coordenadorCurso);
		return coordenadorCurso.getNome()+ " atualizado com sucesso";
	}

	public List<CoordenadorCurso> findAll(){
		return this.coordenadorCursoRepository.findAll();
	}

	public CoordenadorCurso findById(long idCoordenadorCurso) {

		CoordenadorCurso coordenadorCurso = this.coordenadorCursoRepository.findById(idCoordenadorCurso).get();
		return coordenadorCurso;

	}

	public String deleteById(long idCoordenadorCurso) {
		this.coordenadorCursoRepository.deleteById(idCoordenadorCurso);
		return "CoordenadorCurso deletado com sucesso!";

	}

}
