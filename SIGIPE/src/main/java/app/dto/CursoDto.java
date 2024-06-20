package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDto(
		long id,
		@NotBlank String nome,
		@NotNull int quantidadePeriodos) {

}
