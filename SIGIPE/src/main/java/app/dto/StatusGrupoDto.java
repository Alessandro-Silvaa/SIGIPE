package app.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusGrupoDto(
		long id,
		@NotBlank String nome) {

}
