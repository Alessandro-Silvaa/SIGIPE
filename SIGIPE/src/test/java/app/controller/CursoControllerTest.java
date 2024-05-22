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

import app.entity.Aluno;
import app.entity.CoordenadorCurso;
import app.entity.Curso;
import app.entity.Demanda;
import app.entity.Professor;
import app.repository.CursoRepository;

@SpringBootTest
public class CursoControllerTest {
	@Autowired
	CursoController cursoController;
	
	@MockBean
	CursoRepository cursoRepository;
	
	@BeforeEach
	void setup() {
		
		when(this.cursoRepository.findById((long) 1)).thenReturn(Optional.of(new Curso()));//ok
		when(this.cursoRepository.findById((long) 0)).thenReturn(Optional.of(new Curso()));//bad_request
		when(this.cursoRepository.findById((long) 2)).thenReturn(null);//not_found
		
		doNothing().when(this.cursoRepository).deleteById((long) 1);//ok

		when(this.cursoRepository.save(new Curso(1, "aaa", new ArrayList<Demanda>(), new ArrayList<CoordenadorCurso>(), new ArrayList<Professor>(), new ArrayList<Aluno>()))).thenReturn(new Curso());
		when(this.cursoRepository.save(null)).thenReturn(null);
	}
	
	@Test
	void findAll200() {//ok
		when(this.cursoRepository.findAll()).thenReturn(new ArrayList<Curso>());
		ResponseEntity<?> response = this.cursoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findAll404() {//not_found
		when(this.cursoRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = this.cursoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void findById200() {//ok
		ResponseEntity<?> response = this.cursoController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void findById400() {//bad_request
		ResponseEntity<?> response = this.cursoController.findById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void findById404() {//not_found
		ResponseEntity<?> response = this.cursoController.findById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void deleteById200() {//ok
		ResponseEntity<String> response = this.cursoController.deleteById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void deleteById400() {//bad_request
		ResponseEntity<String> response = this.cursoController.deleteById(0);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void deleteById404() {//not_found
		ResponseEntity<String> response = this.cursoController.deleteById(2);
		int httpStatus = response.getStatusCode().value();
		assertEquals(404, httpStatus);
	}
	
	@Test
	void save200() {
		ResponseEntity<String> response = this.cursoController.save(new Curso(1, "aaa", new ArrayList<Demanda>(), new ArrayList<CoordenadorCurso>(), new ArrayList<Professor>(), new ArrayList<Aluno>()));
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	void save400() {
		ResponseEntity<String> response = this.cursoController.save(null);
		int httpStatus = response.getStatusCode().value();
		assertEquals(400, httpStatus);
	}
	
	@Test
	void update200() {
		ResponseEntity<String> response = this.cursoController.updade(1, new Curso(1, "aaa", new ArrayList<Demanda>(), new ArrayList<CoordenadorCurso>(), new ArrayList<Professor>(), new ArrayList<Aluno>()));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void update400() {
		ResponseEntity<String> response = this.cursoController.updade(0, new Curso(1, "aaa", new ArrayList<Demanda>(), new ArrayList<CoordenadorCurso>(), new ArrayList<Professor>(), new ArrayList<Aluno>()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void update404_1() {
		ResponseEntity<String> response = this.cursoController.updade(2, new Curso(1, "aaa", new ArrayList<Demanda>(), new ArrayList<CoordenadorCurso>(), new ArrayList<Professor>(), new ArrayList<Aluno>()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void update404_2() {
		ResponseEntity<String> response = this.cursoController.updade(1, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
