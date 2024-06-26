package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import app.entity.Aluno;
import app.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin("*")
@Validated
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PreAuthorize("('aluno')")
	@PostMapping("/save")
	public ResponseEntity<Aluno> save(@Valid @RequestBody Aluno aluno) {
		try {
			return new ResponseEntity<Aluno>(this.alunoService.save(aluno), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Aluno> update(@Valid @RequestBody Aluno aluno, @PathVariable int id) {
		try {
			return new ResponseEntity<Aluno>(this.alunoService.update(id, aluno), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Aluno>> findAll() {
		try {
			List<Aluno> lista = this.alunoService.findAll();
			return new ResponseEntity<List<Aluno>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Aluno>(this.alunoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Aluno> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<Aluno>(this.alunoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
