package app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.StatusDemanda;
import app.repository.StatusDemandaRepository;
import jakarta.transaction.Transactional;

@Service
public class StatusDemandaService {

	@Autowired
	private StatusDemandaRepository statusDemandaRepository;

	public StatusDemanda save(StatusDemanda statusDemanda) {
		return this.statusDemandaRepository.save(statusDemanda);
	}

	@Transactional
	public StatusDemanda update(long id, StatusDemanda statusDemandaNovo) {
		Optional<StatusDemanda> optStatusDemanda = this.statusDemandaRepository.findById(id);
		if (optStatusDemanda.isPresent()) {
			StatusDemanda statusDemandaOld = optStatusDemanda.get();
			statusDemandaNovo.setId(id);

			System.out.println(statusDemandaNovo.getId());
			System.out.println(statusDemandaNovo.getNome());

			statusDemandaNovo.setDemandas(statusDemandaOld.getDemandas());

			return this.statusDemandaRepository.save(statusDemandaNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}


	public List<StatusDemanda> findAll() {
		return this.statusDemandaRepository.findAll();
	}

	public StatusDemanda findById(long idStatusDemanda) {
		Optional<StatusDemanda> optionalStatusDemanda = this.statusDemandaRepository.findById(idStatusDemanda);
		if (optionalStatusDemanda.isPresent())
			return optionalStatusDemanda.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public StatusDemanda deleteById(long idStatusDemanda) {
		Optional<StatusDemanda> optionalStatusDemanda = this.statusDemandaRepository.findById(idStatusDemanda);
		if (optionalStatusDemanda.isPresent()) {
			StatusDemanda statusDemanda = optionalStatusDemanda.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(statusDemanda.getDemandas());
			
			this.statusDemandaRepository.deleteById(idStatusDemanda);
			return statusDemanda;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
