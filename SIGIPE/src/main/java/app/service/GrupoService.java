package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.Aluno;
import app.entity.Demanda;
import app.entity.StatusDemanda;
import app.repository.AlunoRepository;
import app.repository.DemandaRepository;
import app.repository.StatusDemandaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;
import jakarta.validation.Valid;

@Service
public class GrupoService {
	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	DemandaRepository demandaRepository;

	@Autowired
	StatusDemandaRepository statusDemandaRepository;

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
	
    public List<Grupo> findByNome(String grupo){return this.grupoRepository.findByNome(grupo);}

	public List<Grupo> findGruposByAlunoId(long idPessoa){
		return grupoRepository.findGruposByAluno(idPessoa);
	}

	@Transactional
	public Grupo associarDemandaAoGrupo(long grupoId, long demandaId) {
		// Busca o grupo pelo ID
		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new RuntimeException("Grupo não encontrado com o ID: " + grupoId));

		// Busca a demanda pelo ID
		Demanda demanda = demandaRepository.findById(demandaId)
				.orElseThrow(() -> new RuntimeException("Demanda não encontrada com o ID: " + demandaId));

		// Associa a demanda ao grupo
		grupo.setDemanda(demanda);

		// Salva e retorna o grupo atualizado
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void alterarStatusDemanda(long demandaId, long statusId) {

		// Busca a demanda pelo ID
		Demanda demanda = demandaRepository.findById(demandaId)
				.orElseThrow(() -> new RuntimeException("Demanda não encontrada com o ID: " + demandaId));

		//Busca o status correspondente
		StatusDemanda statusDemanda = statusDemandaRepository.findById(statusId)
				.orElseThrow(() -> new RuntimeException("Status não encontrado com o ID: " + statusId));

		//Define o novo status da demanda
		demanda.setStatus(statusDemanda);
	}
}
