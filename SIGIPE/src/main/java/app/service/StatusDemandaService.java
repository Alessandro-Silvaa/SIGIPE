package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.StatusDemanda;
import app.repository.StatusDemandaRepository;
import jakarta.validation.Valid;

@Service
public class StatusDemandaService {
	@Autowired
	StatusDemandaRepository statusRepository;

	public List<StatusDemanda> findAll() {
		List<StatusDemanda> lista = this.statusRepository.findAll();
		if (lista == null)
			throw new RuntimeException("Não há statuss cadastrados");
		return lista;
	}

	public StatusDemanda findById(long id) {
		Optional<StatusDemanda> optionalStatus = this.statusRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if (optionalStatus == null)
			throw new RuntimeException("Status não encontrado");
		return optionalStatus.get();
	}

	public void deleteById(long id) {
		if (findById(id) != null)
			this.statusRepository.deleteById(id);
	}

	public void save(StatusDemanda status) {
		if (status == null)
			throw new RuntimeException("Chamada inválida");
		this.statusRepository.save(status);
	}

	public void update(@Valid long id, StatusDemanda status) {
		if (status == null)
			throw new RuntimeException("Chamada inválida");
		if (findById(id) != null) {
			status.setIdStatusDemanda(id);
			this.statusRepository.save(status);
		}
	}
}
