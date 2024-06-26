package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCurso;
	@NotBlank(message = "Campo nome do Curso não pode ser nulo!")
	private String nome;	
	
	@ManyToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Demanda> demandas;
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<CoordenadorCurso> coordenadores;
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Professor> professores;
	
	@OneToMany(mappedBy = "curso")
	@JsonIgnoreProperties("curso")
	private List<Aluno> alunos;
}
