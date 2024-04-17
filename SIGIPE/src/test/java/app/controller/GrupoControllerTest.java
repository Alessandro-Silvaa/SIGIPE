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

import app.entity.Demanda;
import app.entity.Grupo;
import app.entity.Pessoa;
import app.repository.GrupoRepository;

@SpringBootTest
public class GrupoControllerTest {
	@Autowired
	GrupoController grupoController;
	
	@MockBean
	GrupoRepository grupoRepository;
	
	@BeforeEach
	void setup() {
		
		when(this.grupoRepository.findById((long) 1)).thenReturn(Optional.of(new Grupo()));//ok
		when(this.grupoRepository.findById((long) 0)).thenReturn(Optional.of(new Grupo()));//bad_request
		when(this.grupoRepository.findById((long) 2)).thenReturn(null);//not_found
		
		doNothing().when(this.grupoRepository).deleteById((long) 1);//ok

		when(this.grupoRepository.save(new Grupo(1, "aaa", new Demanda(), new ArrayList<Pessoa>()))).thenReturn(new Grupo());
		when(this.grupoRepository.save(null)).thenReturn(null);
	}
	
	@Test
	void findAll200() {//ok
		when(this.grupoRepository.findAll()).thenReturn(new ArrayList<Grupo>());
		ResponseEntity<?> response = this.grupoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findAll404() {//not_found
		when(this.grupoRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = this.grupoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void findById200() {//ok
		ResponseEntity<?> response = this.grupoController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findById400() {//bad_request
		ResponseEntity<?> response = this.grupoController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void findById404() {//not_found
		ResponseEntity<?> response = this.grupoController.findById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void deleteById200() {//ok
		ResponseEntity<String> response = this.grupoController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void deleteById400() {//bad_request
		ResponseEntity<String> response = this.grupoController.deleteById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void deleteById404() {//not_found
		ResponseEntity<String> response = this.grupoController.deleteById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void save200() {
		ResponseEntity<String> response = this.grupoController.save(new Grupo(1, "aaa", new Demanda(), new ArrayList<Pessoa>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void save400() {
		ResponseEntity<String> response = this.grupoController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void update200() {
		ResponseEntity<String> response = this.grupoController.updade(1, new Grupo(1, "aaa", new Demanda(), new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void update400() {
		ResponseEntity<String> response = this.grupoController.updade(0, new Grupo(1, "aaa", new Demanda(), new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void update404_1() {
		ResponseEntity<String> response = this.grupoController.updade(2, new Grupo(1, "aaa", new Demanda(), new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void update404_2() {
		ResponseEntity<String> response = this.grupoController.updade(1, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}


}
