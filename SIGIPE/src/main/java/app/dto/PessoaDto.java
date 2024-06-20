package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaDto(
		long id,
		@NotBlank String nome,
		@NotBlank @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)") String cpf) {
}
