package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.entity.Aluno;
import app.entity.Demanda;
import app.repository.AlunoRepository;
import app.repository.DemandaRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Grupo;
import app.repository.GrupoRepository;
import jakarta.transaction.Transactional;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Transactional
	public Grupo save(Grupo grupo) {
		List<Aluno> lista = new ArrayList<>();
		grupo.getAlunos().forEach((aluno ) ->{
			Optional<Aluno> optionalAluno = this.alunoRepository.findById(aluno.getId());
			if(optionalAluno.isPresent()){
				lista.add(optionalAluno.get());
				System.out.println(aluno.getNome());
			}
			else{
				throw new RuntimeException("Não foi possivel encontrar o aluno de ID: " + aluno.getId());
			}

		});
		grupo.setAlunos(lista);

		System.out.println(grupo.getAlunos().get(1).getNome());
		return grupoRepository.save(grupo);
	}


	public Grupo update(long id, Grupo grupoNovo) {
		Optional<Grupo> optGrupo = this.grupoRepository.findById(id);
		if(optGrupo.isPresent()) {
			Grupo grupoOld = optGrupo.get();
			grupoNovo.setId(id);
			grupoNovo.setAlunos(grupoOld.getAlunos());
			grupoNovo.setDemandaInscrita(grupoOld.getDemandaInscrita());
			grupoNovo.setDemandaSolicitada(grupoOld.getDemandaSolicitada());
			
			return this.grupoRepository.save(grupoNovo);
		}
		throw new RuntimeException("Id não encontrado.");
	}

	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	public Grupo findById(long idGrupo) {
		Optional<Grupo> optionalGrupo = this.grupoRepository.findById(idGrupo);
		if (optionalGrupo.isPresent())
			return optionalGrupo.get();
		throw new RuntimeException("Id não encontrado.");
	}

	@Transactional
	public Grupo deleteById(long idGrupo) {
		Optional<Grupo> optionalGrupo = this.grupoRepository.findById(idGrupo);
		if (optionalGrupo.isPresent()) {
			Grupo grupo = optionalGrupo.get();
			
			// Inicialize as coleções necessárias
	        Hibernate.initialize(grupo.getAlunos());
	        Hibernate.initialize(grupo.getDemandaInscrita());
	        Hibernate.initialize(grupo.getDemandaSolicitada());
			
			this.grupoRepository.deleteById(idGrupo);
			return grupo;
		}
		throw new RuntimeException("Id não encontrado.");
	}
}
