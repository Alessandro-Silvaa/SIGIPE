package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.CoordenadorExtensao;
import app.repository.CoordenadorExtensaoRepository;

@Service
public class CoordenadorExtensaoService {

	@Autowired
	private CoordenadorExtensaoRepository coordenadorExtensaoRepository;

	public CoordenadorExtensao save(CoordenadorExtensao coordenadorExtensao) {
		return this.coordenadorExtensaoRepository.save(coordenadorExtensao);
	}

	public CoordenadorExtensao update(long id, CoordenadorExtensao coordenadorExtensao) {
		coordenadorExtensao.setId(id);
		return this.coordenadorExtensaoRepository.save(coordenadorExtensao);
	}

	public List<CoordenadorExtensao> findAll() {
		return this.coordenadorExtensaoRepository.findAll();
	}

	public CoordenadorExtensao findById(long idCoordenadorExtensao) {
		return this.coordenadorExtensaoRepository.findById(idCoordenadorExtensao).get();
	}

	public CoordenadorExtensao deleteById(long idCoordenadorExtensao) {
		CoordenadorExtensao coordenadorExtensao = findById(idCoordenadorExtensao);
		if(coordenadorExtensao != null) {
			this.coordenadorExtensaoRepository.deleteById(idCoordenadorExtensao);
			return coordenadorExtensao;			
		}
		throw new RuntimeException();
	}
}
