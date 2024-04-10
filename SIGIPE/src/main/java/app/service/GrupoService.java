package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;


public class GrupoService {

	@Autowired
	public GrupoRepository grupoRepository;

	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	public Grupo findById(long id) {
		return this.grupoRepository.findById(id).get();
	}

	public void save(Grupo grupo) {
		this.grupoRepository.save(grupo);
	}
	
	public void update(long id, Grupo grupo) {
		grupo.setIdGrupo(id);
		this.grupoRepository.save(grupo);
	}
	public String delete(long id) {
		String idString = Integer.toString((int) id);
		this.grupoRepository.deleteById(id);
		return idString;
	}
}
