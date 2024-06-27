package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference(value = "demanda-gruposSolicitacao")
	private Demanda demandaSolicitada;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference(value = "demanda-gruposInscritos")
	private Demanda demandaInscrita;
	
	@OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "grupo-alunos")
	private List<Aluno> alunos;
	
}
