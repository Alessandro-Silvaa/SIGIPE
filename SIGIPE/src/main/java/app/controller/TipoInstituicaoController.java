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

import app.dto.TipoInstituicaoDto;
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
	public ResponseEntity<TipoInstituicaoDto> save(@Valid @RequestBody TipoInstituicaoDto tipoInstituicao) {
		try {
			return new ResponseEntity<TipoInstituicaoDto>(this.tipoInstituicaoService.save(tipoInstituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TipoInstituicaoDto> update(@Valid @RequestBody TipoInstituicaoDto tipoInstituicao, @PathVariable int id) {
		try {
			return new ResponseEntity<TipoInstituicaoDto>(this.tipoInstituicaoService.update(id, tipoInstituicao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<TipoInstituicaoDto>> findAll() {
		try {
			List<TipoInstituicaoDto> lista = this.tipoInstituicaoService.findAll();
			return new ResponseEntity<List<TipoInstituicaoDto>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<TipoInstituicaoDto> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<TipoInstituicaoDto>(this.tipoInstituicaoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<TipoInstituicaoDto> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<TipoInstituicaoDto>(this.tipoInstituicaoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
