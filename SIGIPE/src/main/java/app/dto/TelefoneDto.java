package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneDto(
		@NotBlank @Pattern(regexp = "[0-9]{1,3}") String codigoPais,
		@NotBlank @Pattern(regexp = "[0-9]+") String numeroTelefone) {

}
