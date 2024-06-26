package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Professor;
import app.service.ProfessorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin("*")
@Validated
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Professor professor) {
		
		try {
			
			String mensagem = this.professorService.save(professor);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Professor professor, @PathVariable int id) {
		
		try {
			
			String mensagem = this.professorService.update(id, professor);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Professor>> findAll (){
		
		try {
			
			List<Professor> lista = this.professorService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
		
	}
	
	@GetMapping("/findById/{idProfessor}")
	public ResponseEntity<Professor> findById(@PathVariable long idProfessor){
		
		try {
			
			Professor professor = this.professorService.findById(idProfessor);
			return new ResponseEntity<>(professor, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/deleteById/{idProfessor}")
	public ResponseEntity<String> deleteById(@PathVariable long idProfessor){
		
		try {
			
			String mensagem = this.professorService.deleteById(idProfessor);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

}
