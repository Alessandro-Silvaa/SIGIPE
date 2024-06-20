package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.TipoInstituicaoDto;
import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;

@Service
public class TipoInstituicaoService {

	@Autowired
	private TipoInstituicaoRepository repository;

	public TipoInstituicaoDto save(TipoInstituicaoDto dto) {
		TipoInstituicao entidade = new TipoInstituicao(dto);
		entidade = this.repository.save(entidade);
		dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
		return dto;
	}

	public TipoInstituicaoDto update(long id, TipoInstituicaoDto dto) {
		Optional<TipoInstituicao> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			TipoInstituicao entidade = new TipoInstituicao(dto);
			entidade.setId(id);
			entidade = this.repository.save(entidade);
			dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			return dto;
		}
		throw new RuntimeException();
	}

	public List<TipoInstituicaoDto> findAll() {
		List<TipoInstituicao> listaEntidades = this.repository.findAll();
		List<TipoInstituicaoDto> listaDtos = new ArrayList<TipoInstituicaoDto>();

		for (TipoInstituicao entidade : listaEntidades) {
			TipoInstituicaoDto dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			listaDtos.add(dto);
		}
		return listaDtos;
	}

	public TipoInstituicaoDto findById(long id) {
		Optional<TipoInstituicao> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			TipoInstituicao entidade = optional.get();
			TipoInstituicaoDto dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			return dto;
		}
		throw new RuntimeException();
	}

	public TipoInstituicaoDto deleteById(long id) {
		Optional<TipoInstituicao> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			TipoInstituicao entidade = optional.get();
			TipoInstituicaoDto dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			this.repository.deleteById(id);
			return dto;
		}
		throw new RuntimeException();
	}
}
