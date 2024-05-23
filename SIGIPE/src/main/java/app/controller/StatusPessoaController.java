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

import app.entity.StatusPessoa;
import app.service.StatusPessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/statusPessoa")
@CrossOrigin("*")
@Validated
public class StatusPessoaController {

	@Autowired
	private StatusPessoaService statusPessoaService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody StatusPessoa statusPessoa) {

		try {

			String mensagem = this.statusPessoaService.save(statusPessoa);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody StatusPessoa statusPessoa, @PathVariable int id) {

		try {

			String mensagem = this.statusPessoaService.update(id, statusPessoa);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity<List<StatusPessoa>> listAll() {

		try {

			List<StatusPessoa> lista = this.statusPessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{idStatusPessoa}")
	public ResponseEntity<StatusPessoa> findById(@PathVariable long idStatusPessoa) {

		try {

			StatusPessoa statusPessoa = this.statusPessoaService.findById(idStatusPessoa);
			return new ResponseEntity<>(statusPessoa, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{idStatusPessoa}")
	public ResponseEntity<String> delete(@PathVariable long idStatusPessoa) {

		try {

			String mensagem = this.statusPessoaService.delete(idStatusPessoa);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
