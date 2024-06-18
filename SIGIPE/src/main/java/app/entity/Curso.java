package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {
	//Atributos de definição
	private long id;
	private String nome;
	private int quantidadePeriodos;
	
	//Atributos de relacionamentowqk
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Professor> professores;

	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")	
	private List<CoordenadorCurso> coordenadores;
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Grupo> grupos;
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")	
	private List<Turma> turmas;
	
	@ManyToMany(mappedBy = "cursos")
	@JsonIgnoreProperties("cursos")
	private List<Demanda> demandas;
	
}
