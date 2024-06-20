package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.dto.TipoInstituicaoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nome;
	
	//Atributos de relacionamento
	
	@OneToMany(mappedBy = "tipo")
	@JsonIgnoreProperties("tipo")
	private List<Instituicao> instituicoes;
	
	public TipoInstituicao(TipoInstituicaoDto dto) {
		this.id = dto.id();
		this.nome = dto.nome();
	}
}
