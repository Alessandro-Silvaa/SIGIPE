package app.repository;

import app.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstituicaoRepository extends JpaRepository<Instituicao,Long> {

    public List<Instituicao> findByRazaoSocial(String razaoSocial);

    @Query("SELECT COUNT(i) > 0 FROM Instituicao i WHERE i.nome = :nome")
    public boolean existsByNome(String nome);

    @Query("SELECT i FROM Instituicao i WHERE i.cidade LIKE :cidade%")
    List<Instituicao> findByCidade(String cidade);
}


