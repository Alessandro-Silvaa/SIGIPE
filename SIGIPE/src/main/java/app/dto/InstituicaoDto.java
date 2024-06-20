package app.dto;

import jakarta.validation.constraints.NotBlank;

public record InstituicaoDto(
		long id,
		@NotBlank String nome,
		@NotBlank String cidade) {

}
