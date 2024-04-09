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
	
	private long id_demandante;
	@NotBlank(message = "Campo nome demandante não pode ser nulo!")
	private String nome;
	private String email;
	private String telefone;
	

}
