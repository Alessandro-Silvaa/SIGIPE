package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demanda;
import app.repository.DemandaRepository;

@Service
public class DemandaService {
	@Autowired
	DemandaRepository demandaRepository;

	public List<Demanda> findAll() {
		return this.demandaRepository.findAll();
	}

	public Demanda findById(long id) {
		Optional<Demanda> optionalDemanda = this.demandaRepository.findById(id);
		if(optionalDemanda==null)
			return null;
		return optionalDemanda.get();
	}

	public boolean deleteById(long id) {
		if(findById(id)==null)
			return false;
		this.demandaRepository.deleteById(id);
		return true;
	}
}
