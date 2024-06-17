package app.repository;

import java.util.List;

import app.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Grupo;
import org.springframework.data.repository.query.Param;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	public List<Grupo> findByNome(String nome);

	@Query("SELECT g FROM Grupo g JOIN g.alunos a WHERE a.idPessoa = :idPessoa")
	List<Grupo> findGruposByAluno(@Param("idPessoa")Long idPessoa);


}
