package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaDto(
		long id,
		@NotBlank String semestreAno,
		@NotNull int periodoCurso) {

}