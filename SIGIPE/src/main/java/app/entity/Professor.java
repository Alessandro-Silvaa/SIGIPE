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
public class Professor extends Pessoa {
	//Atributos de relacionamento
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("professores")
	private Turma turma;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("professores")
	private Curso curso;
}
