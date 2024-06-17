package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.Aluno;
import app.entity.Grupo;
import app.repository.AlunoRepository;
import app.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demanda;
import app.repository.DemandaRepository;
import jakarta.validation.Valid;

@Service
public class DemandaService {
	@Autowired
	DemandaRepository demandaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private GrupoRepository grupoRepository;


	public List<Demanda> findAll() {
		List<Demanda> lista = this.demandaRepository.findAll();
		if (lista == null)
			throw new RuntimeException("Não há demandas cadastradas");
		return lista;
	}

	public Demanda findById(long id) {
		Optional<Demanda> optionalDemanda = this.demandaRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if (optionalDemanda == null)
			throw new RuntimeException("Demanda não encontrada");
		return optionalDemanda.get();
	}

	public void deleteById(long id) {
		if (findById(id) != null)
			this.demandaRepository.deleteById(id);
	}

	public void save(Demanda demanda) {
		if (demanda == null)
			throw new RuntimeException("Chamada inválida");
		if(!verificaquantidadeGrupo(demanda))
			throw new RuntimeException("Quantidade de grupos inválida");
		this.demandaRepository.save(demanda);
	}

	public void update(@Valid long id, Demanda demanda) {
		if (demanda == null)
			throw new RuntimeException("Chamada inválida");
		if (findById(id) != null) {
			demanda.setIdDemanda(id);
			this.demandaRepository.save(demanda);
		}
	}
	
	public boolean verificaquantidadeGrupo(Demanda demanda) {
		if(demanda.getQuantidadeGrupo()<=0)
			return false;
		return true;
	}

	public List<Demanda> findDemandaByGrupoId(long grupoId){

		return this.demandaRepository.findDemandaByGrupoId(grupoId);

	}

	@Transactional
	public void inscreverEmDemanda(long demandaId, long alunoId) {
		// Busca a demanda pelo ID
		Optional<Demanda> optionalDemanda = demandaRepository.findById(demandaId);
		Demanda demanda = optionalDemanda.orElseThrow(() -> new RuntimeException("Demanda não encontrada"));

		// Busca o aluno pelo ID
		Optional<Aluno> optionalAluno = alunoRepository.findById(alunoId);
		Aluno aluno = optionalAluno.orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

		// Verifica se existem grupos cadastrados para esta demanda
		List<Grupo> grupos = demanda.getGrupos();

		Grupo grupoSelecionado = null;

		for (Grupo grupo : grupos) {
			// Verifica se o grupo tem entre 4 e 5 alunos
			if (grupo.getAlunos().size() >= 4 && grupo.getAlunos().size() <= 5) {
				grupoSelecionado = grupo;
				break;
			}
		}

		if (grupoSelecionado == null) {
			// Se não encontrou nenhum grupo com 4-5 alunos, cria um novo grupo
			grupoSelecionado = new Grupo();
			grupoSelecionado.setDemanda(demanda);
			grupos.add(grupoSelecionado);
		}

		// Adiciona o aluno ao grupo selecionado
		grupoSelecionado.getAlunos().add(aluno);
		aluno.getGrupos().add(grupoSelecionado);

		// Salva as alterações no banco de dados
		grupoRepository.save(grupoSelecionado);
		alunoRepository.save(aluno);
		demandaRepository.save(demanda);
	}
}

