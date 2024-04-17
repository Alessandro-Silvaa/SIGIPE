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
import org.springframework.http.ResponseEntity;

import app.entity.Demanda;
import app.entity.Demandante;
import app.entity.Instituicao;
import app.repository.DemandanteRepository;

@SpringBootTest
public class DemandanteControllerTest{
	@Autowired
	DemandanteController demandanteController;
	
	@MockBean
	DemandanteRepository demandanteRepository;
	
	@BeforeEach
	void setup(){
		when(this.demandanteRepository.findById((long) 1)).thenReturn(Optional.of(new Demandante()));
		when(this.demandanteRepository.findById((long) 0)).thenReturn(Optional.of(new Demandante()));
		when(this.demandanteRepository.save(new Demandante(1,"Cesar","cesar@gmail.com","45999999999", new Instituicao(), new ArrayList<Demanda>()))).thenReturn(new Demandante());
		when(this.demandanteRepository.save(null)).thenReturn(null);
		doNothing().when(this.demandanteRepository).deleteById((long) 1);
	}
	
	@Test
	void findByIdOk(){
		ResponseEntity<?> response = this.demandanteController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200,httpStatus);
		
	}
	
	@Test
	void findByIdException() {
		ResponseEntity<?> response = this.demandanteController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400,httpStatus);
	}
	
	@Test
	void deleteOk() {
		ResponseEntity<String> response = this.demandanteController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200,httpStatus);
	}
	
	@Test
	void deleteException() {
		ResponseEntity<String> response = this.demandanteController.delete(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400,httpStatus);
	}
	
	@Test
	void saveOk() {
		ResponseEntity<String> response = this.demandanteController.save(new Demandante(1,"Abc","abc@gasm.com","435345345", new Instituicao(), new ArrayList<Demanda>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(201,httpStatus);
	}
	
	@Test
	void saveException() {
		ResponseEntity<String> response = this.demandanteController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400,httpStatus);
	}
	
	@Test
	void updateOk() {
		ResponseEntity<String> response = this.demandanteController.update(1,new Demandante(1,"Dcz","asdasd@asdasdasd.com","3333333333333", new Instituicao(), new ArrayList<Demanda>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200,httpStatus);
	}
	
	@Test 
	void updateException(){
		ResponseEntity<String> response = this.demandanteController.update(1,null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400,httpStatus);
	}
	
}	