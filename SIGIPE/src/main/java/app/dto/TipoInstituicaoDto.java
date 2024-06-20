package app.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoInstituicaoDto(
		long id, 
		@NotBlank String nome) {
}