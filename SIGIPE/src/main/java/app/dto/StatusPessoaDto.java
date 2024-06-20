package app.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusPessoaDto(
		long id,
		@NotBlank String nome) {

}
