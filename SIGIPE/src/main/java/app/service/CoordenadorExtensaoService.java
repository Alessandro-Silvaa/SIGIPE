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

	public String save(CoordenadorExtensao coordenadorExtensao) {
		this.coordenadorExtensaoRepository.save(coordenadorExtensao);
		return coordenadorExtensao.getNome()+ " salvo com sucesso";
	}

	public String update(long id, CoordenadorExtensao coordenadorExtensao) {
		coordenadorExtensao.setIdPessoa(id);
		this.coordenadorExtensaoRepository.save(coordenadorExtensao);
		return coordenadorExtensao.getNome()+ " atualizado com sucesso";
	}

	public List<CoordenadorExtensao> findAll(){
		return this.coordenadorExtensaoRepository.findAll();
	}

	public CoordenadorExtensao findById(long idCoordenadorExtensao) {

		CoordenadorExtensao coordenadorExtensao = this.coordenadorExtensaoRepository.findById(idCoordenadorExtensao).get();
		return coordenadorExtensao;

	}

	public String deleteById(long idCoordenadorExtensao) {
		this.coordenadorExtensaoRepository.deleteById(idCoordenadorExtensao);
		return "CoordenadorExtensao deletado com sucesso!";

	}

}
