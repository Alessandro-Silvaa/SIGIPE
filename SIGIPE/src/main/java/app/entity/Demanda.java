package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private List<Curso> cursos;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Turma> turmas;
	
	@OneToMany(mappedBy = "demandaInscrita", fetch = FetchType.EAGER)
	private List<Grupo> gruposInscritos;
	
	@OneToMany(mappedBy = "demandaSolicitada", fetch = FetchType.EAGER)
	private List<Grupo> gruposSolicitacao;
}
