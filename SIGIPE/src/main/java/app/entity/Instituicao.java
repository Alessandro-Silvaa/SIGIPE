package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "Campo cidade  não pode ser vazio e nem nulo!")
	private String cidade;
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Cep não deve ter menos de 9 caracteres")
	@NotBlank(message = "Campo cep não pode ficar em branco")
	private String cep;
	@NotBlank(message = "Campo Razão Social  não pode ser vazio e nem nulo!")
	private String razaoSocial;

}
