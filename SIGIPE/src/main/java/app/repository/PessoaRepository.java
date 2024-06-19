package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
