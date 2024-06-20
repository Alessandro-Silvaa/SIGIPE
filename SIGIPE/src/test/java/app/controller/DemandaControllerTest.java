package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

		when(this.demandaRepository
				.save(new Demanda(1, null, 1, "aaa", "aaa", "aaa", "aaa", new StatusDemanda(), new Demandante(),
						new Instituicao(), new ArrayList<Curso>(), new ArrayList<Grupo>(), new ArrayList<Grupo>())))
				.thenReturn(new Demanda());
		when(this.demandaRepository.save(null)).thenReturn(null);
	}

	@Test
	void findAll200() {
		when(this.demandaRepository.findAll()).thenReturn(new ArrayList<Demanda>());
		ResponseEntity<List<Demanda>> response = this.demandaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void findAll400() {
		when(this.demandaRepository.findAll()).thenThrow(new IllegalArgumentException());
		ResponseEntity<List<Demanda>> response = this.demandaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}

	@Test
	void findById200() {
		ResponseEntity<Demanda> response = this.demandaController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void update200() {
		ResponseEntity<Demanda> response = this.demandaController
				.update(new Demanda(1, null, 1, "aaa", "aaa", "aaa", "aaa", new StatusDemanda(), new Demandante(),
						new Instituicao(), new ArrayList<Curso>(), new ArrayList<Grupo>(), new ArrayList<Grupo>()), 1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	void save200() {
		ResponseEntity<Demanda> response = this.demandaController
				.save(new Demanda(1, null, 1, "aaa", "aaa", "aaa", "aaa", new StatusDemanda(), new Demandante(),
						new Instituicao(), new ArrayList<Curso>(), new ArrayList<Grupo>(), new ArrayList<Grupo>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);

	}

	@Test
	void deleteById200() {
		ResponseEntity<Demanda> response = this.demandaController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);

	}
}
