package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demandante;
import app.repository.DemandanteRepository;

@Service
public class DemandanteService{
	
	@Autowired
	DemandanteRepository demandanteRepository;
	
	public List<Demandante>findAll(){
		
		return this.demandanteRepository.findAll();
	}
	
	public Demandante findById(long id){
		
		return this.demandanteRepository.findById(id).get();
		
	}
	
	public void save(Demandante demandante) {
		
		this.demandanteRepository.save(demandante);
	}
	
	public void update(long id, Demandante demandante) {
		demandante.setIdDemandante(id);
		this.demandanteRepository.save(demandante);
	}
	
	public String delete(long id) {
		String nome = findById(id).getNome();
		this.demandanteRepository.deleteById(id);
		return nome;
	}
	
}