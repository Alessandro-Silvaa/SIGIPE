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
import app.entity.Status;
import app.repository.StatusRepository;

@SpringBootTest
public class StatusControllerTest {
	@Autowired
	StatusController statusController;
	
	@MockBean
	StatusRepository statusRepository;
	
	@BeforeEach
	void setup() {
		
		when(this.statusRepository.findById((long) 1)).thenReturn(Optional.of(new Status()));//ok
		when(this.statusRepository.findById((long) 0)).thenReturn(Optional.of(new Status()));//bad_request
		when(this.statusRepository.findById((long) 2)).thenReturn(null);//not_found
		
		doNothing().when(this.statusRepository).deleteById((long) 1);//ok

		when(this.statusRepository.save(new Status(1, "aaa", new ArrayList<Demanda>()))).thenReturn(new Status());
		when(this.statusRepository.save(null)).thenReturn(null);
	}
	
	@Test
	void findAll200() {//ok
		when(this.statusRepository.findAll()).thenReturn(new ArrayList<Status>());
		ResponseEntity<?> response = this.statusController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findAll404() {//not_found
		when(this.statusRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = this.statusController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void findById200() {//ok
		ResponseEntity<?> response = this.statusController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findById400() {//bad_request
		ResponseEntity<?> response = this.statusController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void findById404() {//not_found
		ResponseEntity<?> response = this.statusController.findById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void deleteById200() {//ok
		ResponseEntity<String> response = this.statusController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void deleteById400() {//bad_request
		ResponseEntity<String> response = this.statusController.deleteById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void deleteById404() {//not_found
		ResponseEntity<String> response = this.statusController.deleteById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void save200() {
		ResponseEntity<String> response = this.statusController.save(new Status(1, "aaa", new ArrayList<Demanda>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void save400() {
		ResponseEntity<String> response = this.statusController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void update200() {
		ResponseEntity<String> response = this.statusController.updade(1, new Status(1, "aaa", new ArrayList<Demanda>()));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void update400() {
		ResponseEntity<String> response = this.statusController.updade(0, new Status(1, "aaa", new ArrayList<Demanda>()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void update404_1() {
		ResponseEntity<String> response = this.statusController.updade(2, new Status(1, "aaa", new ArrayList<Demanda>()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void update404_2() {
		ResponseEntity<String> response = this.statusController.updade(1, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
