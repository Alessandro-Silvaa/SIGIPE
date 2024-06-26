package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Curso;
import app.entity.Demanda;
import app.entity.Demandante;
import app.entity.Grupo;
import app.entity.Instituicao;
import app.entity.StatusDemanda;
import app.repository.DemandaRepository;

@SpringBootTest
public class DemandaControllerTest {
	@Autowired
	DemandaController demandaController;

	@MockBean
	DemandaRepository demandaRepository;

	@BeforeEach
	void setup() {

		when(this.demandaRepository.findById((long) 1)).thenReturn(Optional.of(new Demanda()));// ok
		when(this.demandaRepository.findById((long) 0)).thenReturn(Optional.of(new Demanda()));// bad_request
		when(this.demandaRepository.findById((long) 2)).thenReturn(null);// not_found

		doNothing().when(this.demandaRepository).deleteById((long) 1);// ok

		when(this.demandaRepository.save(new Demanda(1, 1, "aaa", "aaa", "aaa", "aaa", new ArrayList<Grupo>(),
				new Instituicao(), new Demandante(), new StatusDemanda(), new ArrayList<Curso>()))).thenReturn(new Demanda());
		when(this.demandaRepository.save(null)).thenReturn(null);
	}

	@Test
	void findAll200() {// ok
		when(this.demandaRepository.findAll()).thenReturn(new ArrayList<Demanda>());
		ResponseEntity<?> response = this.demandaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void findAll404() {// not_found
		when(this.demandaRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = this.demandaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}

	@Test
	void findById200() {// ok
		ResponseEntity<?> response = this.demandaController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void findById400() {// bad_request
		ResponseEntity<?> response = this.demandaController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}

	@Test
	void findById404() {// not_found
		ResponseEntity<?> response = this.demandaController.findById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}

	@Test
	void deleteById200() {// ok
		ResponseEntity<String> response = this.demandaController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void deleteById400() {// bad_request
		ResponseEntity<String> response = this.demandaController.deleteById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}

	@Test
	void deleteById404() {// not_found
		ResponseEntity<String> response = this.demandaController.deleteById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}

	@Test
	void save200() {
		ResponseEntity<String> response = this.demandaController.save(new Demanda(1, 1, "aaa", "aaa", "aaa", "aaa", new ArrayList<Grupo>(),
				new Instituicao(), new Demandante(), new StatusDemanda(), new ArrayList<Curso>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void save400() {
		ResponseEntity<String> response = this.demandaController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}

	@Test
	void update200() {
		ResponseEntity<String> response = this.demandaController.updade(1, new Demanda(1, 1, "aaa", "aaa", "aaa", "aaa", new ArrayList<Grupo>(),
				new Instituicao(), new Demandante(), new StatusDemanda(), new ArrayList<Curso>()));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void update400() {
		ResponseEntity<String> response = this.demandaController.updade(0, new Demanda(1, 1, "aaa", "aaa", "aaa", "aaa", new ArrayList<Grupo>(),
				new Instituicao(), new Demandante(), new StatusDemanda(), new ArrayList<Curso>()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void update404_1() {
		ResponseEntity<String> response = this.demandaController.updade(2, new Demanda(1, 1, "aaa", "aaa", "aaa", "aaa", new ArrayList<Grupo>(),
				new Instituicao(), new Demandante(), new StatusDemanda(), new ArrayList<Curso>()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void update404_2() {
		ResponseEntity<String> response = this.demandaController.updade(1, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
