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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TipoInstituicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipoInstituicao;
	@NotBlank(message = "Campo tipo de instituição não pode ser nulo!")
	private String nome;
	
	
	public long getIdTipoInstituicao() {
		return idTipoInstituicao;
	}
	public void setIdTipoInstituicao(long idTipoInstituicao) {
		this.idTipoInstituicao = idTipoInstituicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
