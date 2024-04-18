package app.entity;

<<<<<<< HEAD:SIGIPE/src/main/java/app/entity/Tipo_Instituicao.java
import jakarta.persistence.*;
=======
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
>>>>>>> 817fb1952b5ce5a8d6662c61025badc1d7702b69:SIGIPE/src/main/java/app/entity/TipoInstituicao.java
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TipoInstituicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipoInstituicao;
	@NotBlank(message = "Campo tipo de instituição não pode ser nulo!")
	private String nome;
<<<<<<< HEAD:SIGIPE/src/main/java/app/entity/Tipo_Instituicao.java

=======
	
	@OneToMany(mappedBy = "tipoInstituicao")
	@JsonIgnoreProperties("tipoInstituicao")
	private List<Instituicao> instituicoes;
>>>>>>> 817fb1952b5ce5a8d6662c61025badc1d7702b69:SIGIPE/src/main/java/app/entity/TipoInstituicao.java
}
