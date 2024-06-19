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
	public ResponseEntity<Professor> save(@Valid @RequestBody Professor professor) {
		try {
			return new ResponseEntity<Professor>(this.professorService.save(professor), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Professor> update(@Valid @RequestBody Professor professor, @PathVariable int id) {
		try {
			return new ResponseEntity<Professor>(this.professorService.update(id, professor), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Professor>> findAll() {
		try {
			List<Professor> lista = this.professorService.findAll();
			return new ResponseEntity<List<Professor>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Professor> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Professor>(this.professorService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Professor> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<Professor>(this.professorService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
