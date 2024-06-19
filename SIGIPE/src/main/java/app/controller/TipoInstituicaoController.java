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

import app.entity.TipoInstituicao;
import app.service.TipoInstituicaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipoInstituicao")
@CrossOrigin("*")
@Validated
public class TipoInstituicaoController {

	@Autowired
	private TipoInstituicaoService tipoInstituicaoService;

	@PostMapping("/save")
	public ResponseEntity<TipoInstituicao> save(@Valid @RequestBody TipoInstituicao tipoInstituicao) {
		try {
			return new ResponseEntity<TipoInstituicao>(this.tipoInstituicaoService.save(tipoInstituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TipoInstituicao> update(@Valid @RequestBody TipoInstituicao tipoInstituicao, @PathVariable int id) {
		try {
			return new ResponseEntity<TipoInstituicao>(this.tipoInstituicaoService.update(id, tipoInstituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<TipoInstituicao>> findAll() {
		try {
			List<TipoInstituicao> lista = this.tipoInstituicaoService.findAll();
			return new ResponseEntity<List<TipoInstituicao>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<TipoInstituicao> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<TipoInstituicao>(this.tipoInstituicaoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<TipoInstituicao> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<TipoInstituicao>(this.tipoInstituicaoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
