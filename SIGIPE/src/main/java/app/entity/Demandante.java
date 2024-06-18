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
public class Demandante {
	//Atributos de definição
	private long id;
	private String nome;
	private String email;
	private String telefone;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandantes")
	private Instituicao instituicao;
	
	@OneToMany(mappedBy = "demandante")
	@JsonIgnoreProperties("demandante")
	private List<Demanda> demandas;

}
