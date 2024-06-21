package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
	@JsonIgnoreProperties("demandas")
	private StatusDemanda status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private Demandante demandante;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private Instituicao instituicao;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private List<Curso> cursos;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandas")
	private List<Turma> turmas;
	
	@OneToMany(mappedBy = "demandaInscrita", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandaInscrita")
	private List<Grupo> gruposInscritos;
	
	@OneToMany(mappedBy = "demandaSolicitada", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("demandaSolicitada")
	private List<Grupo> gruposSolicitacao;
}
