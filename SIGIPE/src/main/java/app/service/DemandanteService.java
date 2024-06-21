package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import app.dto.DemandanteDto;
import app.dto.TelefoneDto;
import app.entity.Demandante;
import app.repository.DemandanteRepository;

@Service
public class DemandanteService {

	@Autowired
	private DemandanteRepository repository;
	
	private TelefoneDto separaTelefone(String telefone) {
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		try {
            // Analisa o número de telefone formatado no padrão internacional
            PhoneNumber number = phoneNumberUtil.parse(telefone, null);
            // Extrai o código de país
            String codigoPais = String.valueOf(number.getCountryCode());
            // Extrai o número nacional
            String numeroTelefone = String.valueOf(number.getNationalNumber());

            TelefoneDto telefoneDto = new TelefoneDto(codigoPais, numeroTelefone);

            return telefoneDto;
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Número de telefone formatado inválido", e);
        }
	}

	public DemandanteDto save(DemandanteDto dto) {
		Demandante entidade = new Demandante(dto);
		entidade = this.repository.save(entidade);
		dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), separaTelefone(entidade.getTelefone()));
		return dto;
	}

	public DemandanteDto update(long id, DemandanteDto dto) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = new Demandante(dto);
			entidade.setId(id);
			entidade = this.repository.save(entidade);
			dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), separaTelefone(entidade.getTelefone()));
			return dto;
		}
		throw new RuntimeException();
	}

	public List<DemandanteDto> findAll() {
		List<Demandante> listaEntidades = this.repository.findAll();
		List<DemandanteDto> listaDtos = new ArrayList<DemandanteDto>();

		for (Demandante entidade : listaEntidades) {
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), separaTelefone(entidade.getTelefone()));
			listaDtos.add(dto);
		}
		return listaDtos;
	}

	public DemandanteDto findById(long id) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = optional.get();
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), separaTelefone(entidade.getTelefone()));
			return dto;
		}
		throw new RuntimeException();
	}

	public DemandanteDto deleteById(long id) {
		Optional<Demandante> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			Demandante entidade = optional.get();
			DemandanteDto dto = new DemandanteDto(entidade.getId(), entidade.getNome(), entidade.getEmail(), separaTelefone(entidade.getTelefone()));
			this.repository.deleteById(id);
			return dto;
		}
		throw new RuntimeException();
	}
}
