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

import app.entity.Grupo;
import app.service.GrupoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grupo")
@CrossOrigin("*")
@Validated
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	private Logger logger = LoggerFactory.getLogger(CursoController.class);

	@PostMapping("/save")
	public ResponseEntity<Grupo> save(@Valid @RequestBody Grupo grupo) {
		try {
			return new ResponseEntity<Grupo>(this.grupoService.save(grupo), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro no salvamento", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasRole('aluno')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Grupo> update(@Valid @RequestBody Grupo grupo, @PathVariable int id) {
		try {
			return new ResponseEntity<Grupo>(this.grupoService.update(id, grupo), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro ao alterar", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Grupo>> findAll() {
		try {
			List<Grupo> lista = this.grupoService.findAll();
			return new ResponseEntity<List<Grupo>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{idGrupo}")
	public ResponseEntity<Grupo> findById(@PathVariable long idGrupo) {
		try {
			return new ResponseEntity<Grupo>(this.grupoService.findById(idGrupo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{idGrupo}")
	public ResponseEntity<Grupo> deleteById(@PathVariable long idGrupo) {
		try {
			return new ResponseEntity<Grupo>(this.grupoService.deleteById(idGrupo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
