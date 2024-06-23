package app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import app.entity.CoordenadorCurso;
import app.service.CoordenadorCursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coordenadorCurso")
@CrossOrigin("*")
@Validated
public class CoodenadorCursoController {

	@Autowired
	private CoordenadorCursoService coordenadorCursoService;
	
	private Logger logger = LoggerFactory.getLogger(CoordenadorCurso.class);

	@PostMapping("/save")
	public ResponseEntity<CoordenadorCurso> save(@Valid @RequestBody CoordenadorCurso coordenadorCurso) {
	    logger.trace("Recepção de requisição de save dados: " + coordenadorCurso);
	    System.out.println(coordenadorCurso.getNome());
	    System.out.println(coordenadorCurso.getCpf());
	    System.out.println(coordenadorCurso.getCurso().getId());
		try {
			coordenadorCurso = this.coordenadorCursoService.save(coordenadorCurso);
	        logger.info("Save bem sucedido com dados: " + coordenadorCurso);
			return new ResponseEntity<CoordenadorCurso>(coordenadorCurso, HttpStatus.OK);
		} catch (Exception e) {
	        logger.error("Save falho com dados", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CoordenadorCurso> update(@Valid @RequestBody CoordenadorCurso coordenadorCurso, @PathVariable int id) {
		try {
			return new ResponseEntity<CoordenadorCurso>(this.coordenadorCursoService.update(id, coordenadorCurso), HttpStatus.OK);
		} catch (Exception e) {
	        logger.error("Save falho com dados", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<CoordenadorCurso>> findAll() {
		try {
			List<CoordenadorCurso> lista = this.coordenadorCursoService.findAll();
			return new ResponseEntity<List<CoordenadorCurso>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<CoordenadorCurso> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<CoordenadorCurso>(this.coordenadorCursoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<CoordenadorCurso> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<CoordenadorCurso>(this.coordenadorCursoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
