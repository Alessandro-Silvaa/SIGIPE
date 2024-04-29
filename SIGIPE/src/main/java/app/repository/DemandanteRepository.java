package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Demandante;

public interface DemandanteRepository extends JpaRepository<Demandante, Long>{
	
	@Query ("SELECT d FROM Demandante d WHERE d.cpf LIKE :cpf%")
	List<Demandante> findByCpf (String cpf);
}