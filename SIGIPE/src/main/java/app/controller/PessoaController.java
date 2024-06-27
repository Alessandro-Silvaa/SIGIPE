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

import app.entity.Pessoa;
import app.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin("*")
@Validated
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PostMapping("/save")
	public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa) {
		try {
			return new ResponseEntity<Pessoa>(this.pessoaService.save(pessoa), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Pessoa> update(@Valid @RequestBody Pessoa pessoa, @PathVariable int id) {
		try {
			return new ResponseEntity<Pessoa>(this.pessoaService.update(id, pessoa), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Pessoa>> findAll() {
		try {
			List<Pessoa> lista = this.pessoaService.findAll();
			return new ResponseEntity<List<Pessoa>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Pessoa>(this.pessoaService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Pessoa> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<Pessoa>(this.pessoaService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
