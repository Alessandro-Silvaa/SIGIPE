package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Turma{
	//Atributos de definição
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int periodoCurso;
	
	//Atributos de relacionamento
	
	@ManyToOne(fetch = FetchType.EAGER)//turma não deve afetar nada em curso
	@JsonIgnoreProperties(value = "turmas")
	private Curso curso;
	
	@OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
	private List<Aluno> alunos;

	@OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
	private List<Professor> professores;
	
	@ManyToMany(mappedBy = "turmas", fetch = FetchType.EAGER)
	private List<Demanda> demandas;
}
