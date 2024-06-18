package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Grupo {
	//Atributos de definição
	private long id;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("grupos")
	private StatusGrupo status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("grupos")
	private Curso curso;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("grupos")
	private Turma turma;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("grupos")
	private Demanda demanda;
	
	@ManyToMany(mappedBy = "grupos")
	@JsonIgnoreProperties("grupos")
	private List<Aluno> alunos;
	
}
