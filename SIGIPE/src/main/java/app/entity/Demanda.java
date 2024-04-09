package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	

}
