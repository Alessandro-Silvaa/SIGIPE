package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Demanda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idDemanda;
	private int quantidadeGrupo;
	@NotBlank(message = "Campo Problema não pode ser nulo!")
	private String problema;
	@NotBlank(message = "Campo Resultado não pode ser nulo!")
	private String resultado;
	@NotBlank(message = "Campo Impacto não pode ser nulo!")
	private String impacto;
	@NotBlank(message = "Campo Prazo não pode ser nulo!")
	private String prazo;
	
	@OneToMany(mappedBy = "demanda")
	@JsonIgnoreProperties("demanda")
	private List<Grupo> grupos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private Instituicao instituicao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private Demandante demandante;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private Status status;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	@JoinTable
	(
		name="Curso_Demanda",
		joinColumns = @JoinColumn(name = "idDemanda"),
		inverseJoinColumns = @JoinColumn(name = "idCurso")
	)
	private List<Curso> cursos;
}
