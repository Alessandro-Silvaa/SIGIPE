package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
