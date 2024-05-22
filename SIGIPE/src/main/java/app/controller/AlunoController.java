package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin("*")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Aluno aluno) {

		try {

			String mensagem = this.alunoService.save(aluno);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Aluno aluno, @PathVariable int id) {

		try {

			String mensagem = this.alunoService.update(id, aluno);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Aluno>> listAll() {

		try {

			List<Aluno> lista = this.alunoService.listAll();
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

	@DeleteMapping("/delete/{idAluno}")
	public ResponseEntity<String> delete(@PathVariable long idAluno) {

		try {

			String mensagem = this.alunoService.delete(idAluno);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
