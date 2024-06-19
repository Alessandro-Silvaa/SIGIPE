package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.StatusDemanda;
import app.repository.StatusDemandaRepository;

@Service
public class StatusDemandaService {

	@Autowired
	private StatusDemandaRepository statusDemandaRepository;

	public StatusDemanda save(StatusDemanda statusDemanda) {
		return this.statusDemandaRepository.save(statusDemanda);
	}

	public StatusDemanda update(long id, StatusDemanda statusDemanda) {
		statusDemanda.setId(id);
		return this.statusDemandaRepository.save(statusDemanda);
	}

	public List<StatusDemanda> findAll() {
		return this.statusDemandaRepository.findAll();
	}

	public StatusDemanda findById(long idStatusDemanda) {
		return this.statusDemandaRepository.findById(idStatusDemanda).get();
	}

	public StatusDemanda deleteById(long idStatusDemanda) {
		StatusDemanda statusDemanda = findById(idStatusDemanda);
		if(statusDemanda != null) {
			this.statusDemandaRepository.deleteById(idStatusDemanda);
			return statusDemanda;			
		}
		throw new RuntimeException();
	}

}
