package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import app.dto.DemandanteDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Demandante {
	//Atributos de definição
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nome;
	@NotBlank
	//@Pattern(regexp = "a", message = "Email inválido")
    private String email;
	@NotBlank
	@Pattern(regexp = "b", message = "Telefone inválido")
	private String telefone;
	
	//Atributos de relacionamento
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("demandantes")
	private Instituicao instituicao;
	
	@OneToMany(mappedBy = "demandante")
	@JsonIgnoreProperties("demandante")
	private List<Demanda> demandas;

	public Demandante(DemandanteDto dto) {
		this.id = dto.id();
		this.nome = dto.nome();
		this.email = dto.email();
		
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		System.out.println("1");
		try {
			System.out.println(dto.telefone().numeroTelefone());
			System.out.println(dto.telefone().codigoPais());
			PhoneNumber number = phoneNumberUtil
					.parse(dto.telefone().numeroTelefone(), 
							dto.telefone().codigoPais());
			System.out.println("2");
			this.telefone = phoneNumberUtil
					.format(number, 
							PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
			System.out.println("telefone: "+this.telefone);
		} catch (NumberParseException e) {
			throw new IllegalArgumentException("Número de telefone inválido ou código de país incorreto", e);
		}
	}
}
