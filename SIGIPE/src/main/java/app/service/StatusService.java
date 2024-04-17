package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Status;
import app.repository.StatusRepository;
import jakarta.validation.Valid;

@Service
public class StatusService {
	@Autowired
	StatusRepository statusRepository;

	public List<Status> findAll() {
		List<Status> lista = this.statusRepository.findAll();
		if (lista == null)
			throw new RuntimeException("Não há statuss cadastrados");
		return lista;
	}

	public Status findById(long id) {
		Optional<Status> optionalStatus = this.statusRepository.findById(id);
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

	public void save(Status status) {
		if (status == null)
			throw new RuntimeException("Chamada inválida");
		this.statusRepository.save(status);
	}

	public void update(@Valid long id, Status status) {
		if (status == null)
			throw new RuntimeException("Chamada inválida");
		if (findById(id) != null) {
			status.setIdStatus(id);
			this.statusRepository.save(status);
		}
	}
}
