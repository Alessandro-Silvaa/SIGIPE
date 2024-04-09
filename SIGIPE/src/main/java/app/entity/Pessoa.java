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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Campo nome não pode ser nulo!")
	private String nome;
	@NotBlank(message = "Campo login não pode ser nulo!")
	private String login;
	@NotBlank(message = "Campo senha não pode ser nulo!")
	private String senha;
	@NotBlank(message = "Campo cpf não pode ser nulo!")
	private String cpf;
	@NotBlank(message = "Campo telefone não pode ser nulo!")
	private String telefone;
	@NotBlank(message = "Campo periodo não pode ser nulo!")
	private int periodo;
	

}
