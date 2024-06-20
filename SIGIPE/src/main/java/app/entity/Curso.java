package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.dto.CursoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nome;
	@NotNull
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
	
	public Curso(CursoDto dto) {
		this.id = dto.id();
		this.nome = dto.nome();
		this.quantidadePeriodos = dto.quantidadePeriodos();
	}
	
}
