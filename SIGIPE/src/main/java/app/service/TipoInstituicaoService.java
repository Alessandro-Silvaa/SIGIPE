package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;

@Service
public class TipoInstituicaoService {

	@Autowired
	private TipoInstituicaoRepository tipoInstituicaoRepository;

	public TipoInstituicao save(TipoInstituicao tipoInstituicao) {
		return this.tipoInstituicaoRepository.save(tipoInstituicao);
	}

	public TipoInstituicao update(long id, TipoInstituicao tipoInstituicao) {
		tipoInstituicao.setId(id);
		return this.tipoInstituicaoRepository.save(tipoInstituicao);
	}

	public List<TipoInstituicao> findAll() {
		return this.tipoInstituicaoRepository.findAll();
	}

	public TipoInstituicao findById(long idTipoInstituicao) {
		return this.tipoInstituicaoRepository.findById(idTipoInstituicao).get();
	}

	public TipoInstituicao deleteById(long idTipoInstituicao) {
		Optional<TipoInstituicao> optionalTipo = this.tipoInstituicaoRepository.findById(idTipoInstituicao);
		if(optionalTipo.isPresent()) {
			TipoInstituicao tipo = optionalTipo.get();
			this.tipoInstituicaoRepository.deleteById(idTipoInstituicao);
			return tipo;
		}
		throw new RuntimeException();
	}

}
