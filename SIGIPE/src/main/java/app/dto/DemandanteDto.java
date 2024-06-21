package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DemandanteDto(
		long id,
		@NotBlank String nome,
		//@NotBlank @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido") String email,
		//@NotBlank @Pattern(regexp = "\\(\\d{2}\\) (9\\d{4})|(\\d{4})-\\d{4})", message = "Telefone inválido") String telefone
		@NotBlank @Pattern(regexp = "a", message = "Email inválido") String email,
		@NotNull TelefoneDto telefone) {

}
