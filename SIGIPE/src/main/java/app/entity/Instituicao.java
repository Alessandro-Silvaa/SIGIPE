package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Instituicao {
	//Atributos de definição
	private long id;
	private String nome;
	private String cidade;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("instituicoes")
	private TipoInstituicao tipo;
	
	@OneToMany(mappedBy = "instituicao")
	@JsonIgnoreProperties("instituicao")
	private List<Demandante> demandantes;
	
	@OneToMany(mappedBy = "instituicao")
	@JsonIgnoreProperties("instituicao")
	private List<Demanda> demandas;
}
