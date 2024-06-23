package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.CoordenadorExtensao;
import app.repository.CoordenadorExtensaoRepository;
import jakarta.transaction.Transactional;

@Service
public class CoordenadorExtensaoService {

	@Autowired
	private CoordenadorExtensaoRepository coordenadorExtensaoRepository;

	public CoordenadorExtensao save(CoordenadorExtensao coordenadorExtensao) {
		return this.coordenadorExtensaoRepository.save(coordenadorExtensao);
	}

	public CoordenadorExtensao update(long id, CoordenadorExtensao coordenadorExtensaoNovo) {
		Optional<CoordenadorExtensao> optCoordenadorExtensao = this.coordenadorExtensaoRepository.findById(id);
		if(optCoordenadorExtensao.isPresent()) {
			coordenadorExtensaoNovo.setId(id);
			
			return this.coordenadorExtensaoRepository.save(coordenadorExtensaoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<CoordenadorExtensao> findAll() {
		return this.coordenadorExtensaoRepository.findAll();
	}

	public CoordenadorExtensao findById(long idCoordenadorExtensao) {
		Optional<CoordenadorExtensao> optionalCoordenadorExtensao = this.coordenadorExtensaoRepository.findById(idCoordenadorExtensao);
		if (optionalCoordenadorExtensao.isPresent())
			return optionalCoordenadorExtensao.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public CoordenadorExtensao deleteById(long idCoordenadorExtensao) {
		Optional<CoordenadorExtensao> optionalCoordenadorExtensao = this.coordenadorExtensaoRepository.findById(idCoordenadorExtensao);
		if (optionalCoordenadorExtensao.isPresent()) {
			CoordenadorExtensao coordenadorExtensao = optionalCoordenadorExtensao.get();
			
			this.coordenadorExtensaoRepository.deleteById(idCoordenadorExtensao);
			return coordenadorExtensao;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
