package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Instituicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInstituicao;
	@NotBlank(message = "Campo nome da Instituição não pode ser Nulo!")
	private String nome;
	@NotBlank(message = "Campo cidade  não pode ser nulo!")
	private String cidade;
	@Pattern(regexp = "^\\d{5}-\\d{3}$")
	@NotBlank(message = "Campo ce não pode ficar em branco")
	private String cep;
	@NotBlank(message = "Campo Razão Social  não pode ser nulo!")
	private String razaoSocial;
	
}
