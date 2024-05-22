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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alunos")
	private Curso curso;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alunos")
	private Periodo periodo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alunos")
	@JoinTable
	(
		name = "a_aluno_grupo",
		joinColumns = @JoinColumn(name = "idAluno"),
		inverseJoinColumns = @JoinColumn(name = "idGrupo")
	)
	private List<Grupo> grupos;
}
