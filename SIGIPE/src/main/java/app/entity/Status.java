package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idStatus;
	@NotBlank(message = "Campo nome Status não pode ser nulo")
	private String nome;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("status")
	private Demanda demandas;
}
