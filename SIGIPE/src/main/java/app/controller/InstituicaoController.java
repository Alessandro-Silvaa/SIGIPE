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

import app.entity.Instituicao;
import app.service.InstituicaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instituicao")
@CrossOrigin("*")
@Validated
public class InstituicaoController {

	@Autowired
	private InstituicaoService instituicaoService;

	@PostMapping("/save")
	public ResponseEntity<Instituicao> save(@Valid @RequestBody Instituicao instituicao) {
		try {
			return new ResponseEntity<Instituicao>(this.instituicaoService.save(instituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Instituicao> update(@Valid @RequestBody Instituicao instituicao, @PathVariable int id) {
		try {
			return new ResponseEntity<Instituicao>(this.instituicaoService.update(id, instituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Instituicao>> findAll() {
		try {
			List<Instituicao> lista = this.instituicaoService.findAll();
			return new ResponseEntity<List<Instituicao>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Instituicao> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Instituicao>(this.instituicaoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Instituicao> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<Instituicao>(this.instituicaoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
