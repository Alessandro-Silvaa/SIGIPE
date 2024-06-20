package app.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusDemandaDto(
		long id,
		@NotBlank String nome) {

}
