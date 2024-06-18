package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno extends Pessoa{
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alunos")
	private Turma turma;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alunos")
	private Curso curso;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "rel_aluno_grupo",
		joinColumns = @JoinColumn(name = "idAluno"),
		inverseJoinColumns = @JoinColumn(name = "idGrupo")
	)
	@JsonIgnoreProperties("alunos")
	private List<Grupo> grupos;
}
