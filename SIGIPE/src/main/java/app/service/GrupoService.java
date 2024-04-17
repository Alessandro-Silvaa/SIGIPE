package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;
import jakarta.validation.Valid;

@Service
public class GrupoService {
	@Autowired
	GrupoRepository grupoRepository;

	public List<Grupo> findAll() {
		List<Grupo> lista = this.grupoRepository.findAll();
		if (lista == null)
			throw new RuntimeException("Não há grupos cadastrados");
		return lista;
	}

	public Grupo findById(long id) {
		Optional<Grupo> optionalGrupo = this.grupoRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if (optionalGrupo == null)
			throw new RuntimeException("Grupo não encontrado");
		return optionalGrupo.get();
	}

	public void deleteById(long id) {
		if (findById(id) != null)
			this.grupoRepository.deleteById(id);
	}

	public void save(Grupo grupo) {
		if (grupo == null)
			throw new RuntimeException("Chamada inválida");
		this.grupoRepository.save(grupo);
	}

	public void update(@Valid long id, Grupo grupo) {
		if (grupo == null)
			throw new RuntimeException("Chamada inválida");
		if (findById(id) != null) {
			grupo.setIdGrupo(id);
			this.grupoRepository.save(grupo);
		}
	}


}
