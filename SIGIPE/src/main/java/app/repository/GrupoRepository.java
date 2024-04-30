package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	public List<Grupo> findByNome(String nome);
	
	@Query("SELECT ng"
			+ "FROM Grupo ng"
			+ "WHERE ng.nome"
			+ "LIKE :nome%")
	
	 public List<Grupo> findByBuscaNome(String nome);
}
