package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPessoa;
	@NotBlank(message = "Campo nome não pode ser nulo!")
	private String nome;
	@NotBlank(message = "Campo login não pode ser nulo!")
	private String login;
	@NotBlank(message = "Campo senha não pode ser nulo!")
	private String senha;
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	@NotBlank(message = "Campo cpf não pode ser nulo!")
	private String cpf;
	@NotBlank(message = "Campo telefone não pode ser nulo!")
	private String telefone;
	@NotBlank(message = "Campo periodo não pode ser nulo!")
	private int periodo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pessoas")
	private Grupo grupo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pessoas")
	private Funcao funcao;
}
