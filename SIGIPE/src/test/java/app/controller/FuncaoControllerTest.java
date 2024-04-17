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

import app.entity.Funcao;
import app.entity.Pessoa;
import app.repository.FuncaoRepository;

@SpringBootTest
public class FuncaoControllerTest {
	@Autowired
	FuncaoController funcaoController;
	
	@MockBean
	FuncaoRepository funcaoRepository;
	
	@BeforeEach
	void setup() {
		
		when(this.funcaoRepository.findById((long) 1)).thenReturn(Optional.of(new Funcao()));//ok
		when(this.funcaoRepository.findById((long) 0)).thenReturn(Optional.of(new Funcao()));//bad_request
		when(this.funcaoRepository.findById((long) 2)).thenReturn(null);//not_found
		
		doNothing().when(this.funcaoRepository).deleteById((long) 1);//ok

		when(this.funcaoRepository.save(new Funcao(1, "aaa", new ArrayList<Pessoa>()))).thenReturn(new Funcao());
		when(this.funcaoRepository.save(null)).thenReturn(null);
	}
	
	@Test
	void findAll200() {//ok
		when(this.funcaoRepository.findAll()).thenReturn(new ArrayList<Funcao>());
		ResponseEntity<?> response = this.funcaoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findAll404() {//not_found
		when(this.funcaoRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = this.funcaoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void findById200() {//ok
		ResponseEntity<?> response = this.funcaoController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findById400() {//bad_request
		ResponseEntity<?> response = this.funcaoController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void findById404() {//not_found
		ResponseEntity<?> response = this.funcaoController.findById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void deleteById200() {//ok
		ResponseEntity<String> response = this.funcaoController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void deleteById400() {//bad_request
		ResponseEntity<String> response = this.funcaoController.deleteById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void deleteById404() {//not_found
		ResponseEntity<String> response = this.funcaoController.deleteById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void save200() {
		ResponseEntity<String> response = this.funcaoController.save(new Funcao(1, "aaa", new ArrayList<Pessoa>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void save400() {
		ResponseEntity<String> response = this.funcaoController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void update200() {
		ResponseEntity<String> response = this.funcaoController.updade(1, new Funcao(1, "aaa", new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void update400() {
		ResponseEntity<String> response = this.funcaoController.updade(0, new Funcao(1, "aaa", new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void update404_1() {
		ResponseEntity<String> response = this.funcaoController.updade(2, new Funcao(1, "aaa", new ArrayList<Pessoa>()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void update404_2() {
		ResponseEntity<String> response = this.funcaoController.updade(1, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
