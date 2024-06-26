package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
	private int quantidadePeriodos;
	
	//Atributos de relacionamentowqk
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("curso")
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"curso","professores.turma.curso"})
	private List<Professor> professores;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("curso")
	private List<CoordenadorCurso> coordenadores;
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("curso")
	private List<Turma> turmas;
	
	@ManyToMany(mappedBy = "cursos", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("cursos")
	private List<Demanda> demandas;
}
