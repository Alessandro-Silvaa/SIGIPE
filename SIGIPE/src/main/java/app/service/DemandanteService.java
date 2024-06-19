package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demandante;
import app.repository.DemandanteRepository;

@Service
public class DemandanteService {

	@Autowired
	private DemandanteRepository demandanteRepository;

	public Demandante save(Demandante demandante) {
		return this.demandanteRepository.save(demandante);
	}

	public Demandante update(long id, Demandante demandante) {
		demandante.setId(id);
		return this.demandanteRepository.save(demandante);
	}

	public List<Demandante> findAll() {
		return this.demandanteRepository.findAll();
	}

	public Demandante findById(long idDemandante) {
		return this.demandanteRepository.findById(idDemandante).get();
	}

	public Demandante deleteById(long idDemandante) {
		Demandante demandante = findById(idDemandante);
		if(demandante != null) {
			this.demandanteRepository.deleteById(idDemandante);
			return demandante;			
		}
		throw new RuntimeException();
	}

}
