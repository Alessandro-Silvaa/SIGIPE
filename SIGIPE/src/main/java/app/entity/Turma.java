package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Turma{
	//Atributos de definição
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String semestreAno;
	@NotNull
	private int periodoCurso;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("turmas")
	private Curso curso;
	
	@OneToMany(mappedBy = "turma")
	@JsonIgnoreProperties("turma")
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "turma")
	@JsonIgnoreProperties("turma")
	private List<Professor> professores;
	
	@OneToMany(mappedBy = "turma")
	@JsonIgnoreProperties("turma")
	private List<Grupo> grupos;
}
