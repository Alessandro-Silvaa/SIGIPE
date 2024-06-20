package app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DemandaDto(
		long id,
		String nome,
		@NotNull int quantidadeGrupos,
		@NotBlank String descricaoProblema, 
		@NotBlank String resultadosEsperados,
		@NotBlank String nivelImpacto,
		@NotBlank String expectativaPrazo) {

}
