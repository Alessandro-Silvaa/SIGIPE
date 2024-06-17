package app.repository;

import app.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Demanda;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandaRepository extends JpaRepository<Demanda, Long>{

    @Query("SELECT d FROM Demanda d JOIN FETCH d.grupos g WHERE g.idGrupo = :id")
    List<Demanda> findDemandaByGrupoId(long id);

}
