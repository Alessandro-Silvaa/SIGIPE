package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPessoa;
	@NotBlank(message = "Campo nome não pode ser nulo!")
	private String nome;
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	@NotBlank(message = "Campo cpf não pode ser nulo!")
	private String cpf;
	@NotBlank(message = "Campo telefone não pode ser nulo!")
	private String telefone;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pessoas")
	private StatusPessoa statusPessoa;


	public Pessoa(long id){
		this.idPessoa = id;
	}

}
