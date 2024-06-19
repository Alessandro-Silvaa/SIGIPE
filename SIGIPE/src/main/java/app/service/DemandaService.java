package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demanda;
import app.repository.DemandaRepository;

@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;

	public Demanda save(Demanda demanda) {
		return this.demandaRepository.save(demanda);
	}

	public Demanda update(long id, Demanda demanda) {
		demanda.setId(id);
		return this.demandaRepository.save(demanda);
	}

	public List<Demanda> findAll() {
		return this.demandaRepository.findAll();
	}

	public Demanda findById(long idDemanda) {
		return this.demandaRepository.findById(idDemanda).get();
	}

	public Demanda deleteById(long idDemanda) {
		Demanda demanda = findById(idDemanda);
		if(demanda != null) {
			this.demandaRepository.deleteById(idDemanda);
			return demanda;			
		}
		throw new RuntimeException();
	}

}
