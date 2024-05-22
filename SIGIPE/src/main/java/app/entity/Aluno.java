package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
}
