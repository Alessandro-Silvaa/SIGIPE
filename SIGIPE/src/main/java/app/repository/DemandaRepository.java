package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Demanda;

public interface DemandaRepository extends JpaRepository<Long, Demanda>{

}
