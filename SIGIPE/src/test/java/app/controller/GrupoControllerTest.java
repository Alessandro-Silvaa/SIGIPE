package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import app.entity.Grupo;
import app.repository.GrupoRepository;

public class GrupoControllerTest {
	@Autowired
	GrupoController	grupoController;
	
	@MockBean
	GrupoRepository grupoRepository;
	
	@BeforeEach
	void setup() {
		when(this.grupoRepository.findAll()).thenReturn(new ArrayList<Grupo>());
	}
	
	@Test
	@DisplayName("Teste de findAll() OK em Grupo")
	void findAllOk() {
		ResponseEntity<List<Grupo>> response = this.grupoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	/*@Test
	@DisplayName("Teste de findById() OK em Grupo")
	void findByIdOk() {
		ResponseEntity<Grupo> response = this.grupoController.findById(1);
		Grupo grupo = response.getBody();
		assertEquals(1, grupo.getIdGrupo());
	}
	
	@Test
	@DisplayName("Teste de save() OK em Grupo")
	void saveOk() {
		Grupo grupo = new Grupo(0,"nome 1");
		ResponseEntity<String> response = this.grupoController.save(grupo);
		int httpStatus = response.getStatusCode().value();
		assertEquals(201, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de update() OK em Grupo")
	void updateOk() {
		Grupo grupo = new Grupo(0,"nome 1");
		ResponseEntity<String> response = this.grupoController.update(1, grupo);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de delete() OK em Grupo")
	void deleteOk() {
		ResponseEntity<String> response = this.grupoController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}*/
	
}
