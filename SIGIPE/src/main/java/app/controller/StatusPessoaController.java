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
@RequestMapping("/api/statuPessoa")
@CrossOrigin("*")
@Validated
public class StatusPessoaController {

	@Autowired
	private StatusPessoaService statuPessoaService;

	@PostMapping("/save")
	public ResponseEntity<StatusPessoa> save(@Valid @RequestBody StatusPessoa statuPessoa) {
		try {
			return new ResponseEntity<StatusPessoa>(this.statuPessoaService.save(statuPessoa), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StatusPessoa> update(@Valid @RequestBody StatusPessoa statuPessoa, @PathVariable int id) {
		try {
			return new ResponseEntity<StatusPessoa>(this.statuPessoaService.update(id, statuPessoa), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<StatusPessoa>> findAll() {
		try {
			List<StatusPessoa> lista = this.statuPessoaService.findAll();
			return new ResponseEntity<List<StatusPessoa>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<StatusPessoa> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusPessoa>(this.statuPessoaService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<StatusPessoa> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusPessoa>(this.statuPessoaService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
