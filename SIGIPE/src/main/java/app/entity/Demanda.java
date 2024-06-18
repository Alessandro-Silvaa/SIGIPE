package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
	private long id;
	private String nome;
	private int quantidadeGrupos;
	private String descricaoProblema;
	private String resultadosEsperados;
	private String nivelImpacto;
	private String expectativaPrazo;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private StatusDemanda status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private Demandante demandante;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private Instituicao instituicao;
	
	@OneToMany(mappedBy = "demanda")
	@JsonIgnoreProperties("demanda")
	private List<Grupo> grupos;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandas")
	private List<Curso> cursos;
}
