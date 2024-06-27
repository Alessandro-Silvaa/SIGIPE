package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Demanda {
	//Atributos de definição
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private int quantidadeGrupos;
	private String descricaoProblema;
	private String resultadosEsperados;
	private String nivelImpacto;
	private String expectativaPrazo;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference(value = "status-demandas")
	private StatusDemanda status;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Demandante demandante;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instituicao instituicao;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private List<Curso> cursos;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private List<Turma> turmas;
	
	@OneToMany(mappedBy = "demandaInscrita", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "demanda-gruposInscritos")
	private List<Grupo> gruposInscritos;
	
	@OneToMany(mappedBy = "demandaSolicitada", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "demanda-gruposSolicitacao")
	private List<Grupo> gruposSolicitacao;
}
