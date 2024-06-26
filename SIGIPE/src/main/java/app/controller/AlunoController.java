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

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Aluno aluno) {

		try {

			String mensagem = this.alunoService.save(aluno);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Aluno aluno, @PathVariable int id) {

		try {

			String mensagem = this.alunoService.update(id, aluno);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Aluno>> findAll() {

		try {

			List<Aluno> lista = this.alunoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{idAluno}")
	public ResponseEntity<Aluno> findById(@PathVariable long idAluno) {

		try {

			Aluno aluno = this.alunoService.findById(idAluno);
			return new ResponseEntity<>(aluno, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{idAluno}")
	public ResponseEntity<String> deleteById(@PathVariable long idAluno) {

		try {

			String mensagem = this.alunoService.deleteById(idAluno);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
