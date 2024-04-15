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
		List<Demanda> lista = this.demandaRepository.findAll();
		if(lista==null)
			throw new RuntimeException("Não há demandas cadastradas");
		return lista;
	}

	public Demanda findById(long id) {
		Optional<Demanda> optionalDemanda = this.demandaRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if(optionalDemanda==null)
			throw new RuntimeException("Demanda não encontrada");
		return optionalDemanda.get();
	}

	public void deleteById(long id) {
		if(findById(id)!=null)
			this.demandaRepository.deleteById(id);
	}

	public void save(Demanda demanda) {
		if(demanda == null)
			throw new RuntimeException("Chamada inválida");
		this.demandaRepository.save(demanda);
	}
}
