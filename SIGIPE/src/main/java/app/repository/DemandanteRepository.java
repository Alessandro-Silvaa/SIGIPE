package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Demandante;

public interface DemandanteRepository extends JpaRepository<Demandante, Long>{
	
}