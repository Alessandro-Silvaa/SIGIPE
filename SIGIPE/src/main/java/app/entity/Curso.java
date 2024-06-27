package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	@JsonManagedReference(value = "curso-alunos")
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	//@JsonManagedReference(value = "curso-professores")
	private List<Professor> professores;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "curso-coordenadores")
	private List<CoordenadorCurso> coordenadores;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "curso-turmas")
	private List<Turma> turmas;

	@ManyToMany(mappedBy = "cursos", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("cursos")
	private List<Demanda> demandas;
}
