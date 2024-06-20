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
	private TipoInstituicaoRepository tipoInstituicaoRepository;

	public TipoInstituicaoDto save(TipoInstituicaoDto dto) {
		TipoInstituicao entidade = new TipoInstituicao(dto);
		entidade = this.tipoInstituicaoRepository.save(entidade);
		dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
		return dto;
	}

	public TipoInstituicaoDto update(long id, TipoInstituicaoDto dto) {
		TipoInstituicao entidade = new TipoInstituicao(dto);
		entidade.setId(id);
		entidade = this.tipoInstituicaoRepository.save(entidade);
		dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
		return dto;
	}

	public List<TipoInstituicaoDto> findAll() {
		List<TipoInstituicao> listaEntidades = this.tipoInstituicaoRepository.findAll();
		List<TipoInstituicaoDto> listaDtos = new ArrayList<TipoInstituicaoDto>();

		for (TipoInstituicao entidade : listaEntidades) {
			TipoInstituicaoDto dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			listaDtos.add(dto);
		}
		return listaDtos;
	}

	public TipoInstituicaoDto findById(long idTipoInstituicao) {
		Optional<TipoInstituicao> optionalTipo = this.tipoInstituicaoRepository.findById(idTipoInstituicao);
		if (optionalTipo.isPresent()) {
			TipoInstituicao entidade = optionalTipo.get();
			TipoInstituicaoDto dto = new TipoInstituicaoDto(entidade.getId(), entidade.getNome());
			return dto;
		}
		throw new RuntimeException();
	}

	public TipoInstituicaoDto deleteById(long idTipoInstituicao) {
		Optional<TipoInstituicao> optionalTipo = this.tipoInstituicaoRepository.findById(idTipoInstituicao);
		if (optionalTipo.isPresent()) {
			TipoInstituicao tipo = optionalTipo.get();
			TipoInstituicaoDto dto = new TipoInstituicaoDto(tipo.getId(), tipo.getNome());
			this.tipoInstituicaoRepository.deleteById(idTipoInstituicao);
			return dto;
		}
		throw new RuntimeException();
	}

}
