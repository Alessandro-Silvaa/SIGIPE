package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.DemandanteDto;
import app.entity.Demandante;
import app.repository.DemandanteRepository;

@Service
public class DemandanteService {

	@Autowired
	private DemandanteRepository repository;

	public DemandanteDto save(DemandanteDto dto) {
		Demandante entidade = new Demandante(dto);
		entidade = this.repository.save(entidade);
		dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), entidade.getTelefone());
		return dto;
	}

	public DemandanteDto update(long id, DemandanteDto dto) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = new Demandante(dto);
			entidade.setId(id);
			entidade = this.repository.save(entidade);
			dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), entidade.getTelefone());
			return dto;
		}
		throw new RuntimeException();
	}

	public List<DemandanteDto> findAll() {
		List<Demandante> listaEntidades = this.repository.findAll();
		List<DemandanteDto> listaDtos = new ArrayList<DemandanteDto>();

		for (Demandante entidade : listaEntidades) {
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), entidade.getTelefone());
			listaDtos.add(dto);
		}
		return listaDtos;
	}

	public DemandanteDto findById(long id) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = optional.get();
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), entidade.getTelefone());
			return dto;
		}
		throw new RuntimeException();
	}

	public DemandanteDto deleteById(long id) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = optional.get();
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), entidade.getTelefone());
			this.repository.deleteById(id);
			return dto;
		}
		throw new RuntimeException();
	}
}
