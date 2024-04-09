package app.sigipe.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Tipo_Instituicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long idTipoInstituicao;
	@NotBlank(message = "Campo tipo de instituição não pode ser nulo!")
	private String nome;
	
	

}
