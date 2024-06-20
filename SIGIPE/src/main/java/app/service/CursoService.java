package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.CursoDto;
import app.entity.Curso;
import app.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	public CursoDto save(CursoDto dto) {
		Curso entidade = new Curso(dto);
		entidade = this.repository.save(entidade);
		dto = new CursoDto(entidade.getId(), entidade.getNome(), entidade.getQuantidadePeriodos());
		return dto;
	}

	public CursoDto update(long id, CursoDto dto) {
		Optional<Curso> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Curso entidade = new Curso(dto);
			entidade.setId(id);
			entidade = this.repository.save(entidade);
			dto = new CursoDto(entidade.getId(), entidade.getNome(), entidade.getQuantidadePeriodos());
			return dto;
		}
		throw new RuntimeException();
	}

	public List<CursoDto> findAll() {
		List<Curso> listaEntidades = this.repository.findAll();
		List<CursoDto> listaDtos = new ArrayList<CursoDto>();

		for (Curso entidade : listaEntidades) {
			CursoDto dto = new CursoDto(entidade.getId(), entidade.getNome(), entidade.getQuantidadePeriodos());
			listaDtos.add(dto);
		}
		return listaDtos;
	}

	public CursoDto findById(long id) {
		Optional<Curso> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Curso entidade = optional.get();
			CursoDto dto = new CursoDto(entidade.getId(), entidade.getNome(), entidade.getQuantidadePeriodos());
			return dto;
		}
		throw new RuntimeException();
	}

	public CursoDto deleteById(long id) {
		Optional<Curso> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Curso entidade = optional.get();
			CursoDto dto = new CursoDto(entidade.getId(), entidade.getNome(), entidade.getQuantidadePeriodos());
			this.repository.deleteById(id);
			return dto;
		}
		throw new RuntimeException();
	}
}
