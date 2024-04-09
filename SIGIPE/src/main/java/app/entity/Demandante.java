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
public class Demandante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idDemandante;
	@NotBlank(message = "Campo nome demandante não pode ser nulo!")
	private String nome;
	@NotBlank(message = "Campo nome e-mail não pode ser nulo!")
	private String email;
	@NotBlank(message = "Campo nome telefone não pode ser nulo!")
	private String telefone;
	

}
