package app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import app.entity.Curso;
import app.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin("*")
@Validated
public class CursoController {

	@Autowired
	private CursoService cursoService;

	private Logger logger = LoggerFactory.getLogger(CursoController.class);

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PostMapping("/save")
	public ResponseEntity<Curso> save(@Valid @RequestBody Curso curso) {
		try {
			return new ResponseEntity<Curso>(this.cursoService.save(curso), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro no salvamento", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Curso> update(@Valid @RequestBody Curso curso, @PathVariable int id) {
		System.out.println(curso.getNome());
		System.out.println(curso.getId());
		System.out.println(curso.getQuantidadePeriodos());
		curso.getCoordenadores().forEach(e -> {
			System.out.println(e.getNome());
		});
		try {
			return new ResponseEntity<Curso>(this.cursoService.update(id, curso), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na atualização", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Curso>> findAll() {
		try {
			List<Curso> lista = this.cursoService.findAll();
			return new ResponseEntity<List<Curso>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na busca", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Curso> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Curso>(this.cursoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na busca", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Curso> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<Curso>(this.cursoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na deleção", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
