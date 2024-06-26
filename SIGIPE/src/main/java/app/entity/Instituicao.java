package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@NotBlank(message = "Campo cidade  não pode ser vazio e nem nulo!")
	private String cidade;
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Cep não deve ter menos de 9 caracteres")
	@NotBlank(message = "Campo cep não pode ficar em branco")
	private String cep;
	@Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "CNPJ deve estar no formato xx.xxx.xxx/xxxx-xx")
	@NotBlank(message = "Campo Cnpj não pode ser vazio e nem nulo!")
	private String cnpj;
	@NotBlank(message = "Campo Razão Social  não pode ser vazio e nem nulo!")
	private String razaoSocial;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("instituicao")
	private TipoInstituicao tipoInstituicao;

	@OneToMany(mappedBy = "instituicao")
	@JsonIgnoreProperties("instituicao")
	private List<Demandante> demandantes;

	@OneToMany(mappedBy = "instituicao")
	@JsonIgnoreProperties("instituicao")
	private List<Demanda> demandas;

}
