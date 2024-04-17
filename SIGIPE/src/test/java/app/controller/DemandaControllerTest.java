package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import app.entity.Demanda;
import app.repository.DemandaRepository;

@SpringBootTest
public class DemandaControllerTest {
	@Autowired
	DemandaController demandaController;
	
	@MockBean
	DemandaRepository demandaRepository;
	
	@BeforeEach
	void setup() {
		when(this.demandaRepository.findAll()).thenReturn(new ArrayList<Demanda>());
		when(this.demandaRepository.findById((long) 1)).thenReturn(Optional.of(new Demanda()));
	}
	
	@Test
	void findAll() {
		ResponseEntity<List<Demanda>> response = this.demandaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
}
