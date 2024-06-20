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

import app.dto.CursoDto;
import app.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin("*")
@Validated
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@PostMapping("/save")
	public ResponseEntity<CursoDto> save(@Valid @RequestBody CursoDto curso) {
		try {
			return new ResponseEntity<CursoDto>(this.cursoService.save(curso), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CursoDto> update(@Valid @RequestBody CursoDto curso, @PathVariable int id) {
		try {
			return new ResponseEntity<CursoDto>(this.cursoService.update(id, curso), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<CursoDto>> findAll() {
		try {
			List<CursoDto> lista = this.cursoService.findAll();
			return new ResponseEntity<List<CursoDto>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<CursoDto> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<CursoDto>(this.cursoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<CursoDto> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<CursoDto>(this.cursoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}
}
