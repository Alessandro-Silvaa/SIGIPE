package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
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
public class TipoInstituicao {
	//Atributos de definição
	private long id;
	private String nome;
	
	//Atributos de relacionamento
	
	@OneToMany(mappedBy = "tipo")
	@JsonIgnoreProperties("tipo")
	private List<Instituicao> instituicoes;
	
}
