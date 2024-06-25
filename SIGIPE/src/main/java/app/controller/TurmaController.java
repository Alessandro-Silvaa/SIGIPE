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

import app.entity.Turma;
import app.service.TurmaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turma")
@CrossOrigin("*")
@Validated
public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	
	private Logger logger = LoggerFactory.getLogger(TurmaController.class);

	@PostMapping("/save")
	public ResponseEntity<Turma> save(@Valid @RequestBody Turma turma) {
	    logger.trace("Recepção de requisição de save dados: " + turma);
		try {
			return new ResponseEntity<Turma>(this.turmaService.save(turma), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Turma> update(@Valid @RequestBody Turma turma, @PathVariable long id) {
	    logger.trace("Recepção de requisição de update com id: " + id + " e dados: " + turma);
	    try {
	        Turma updatedTurma = this.turmaService.update(id, turma);
	        logger.info("Update bem sucedido para o id: " + id);
	        return new ResponseEntity<Turma>(updatedTurma, HttpStatus.OK);
	    } catch (Exception e) {
	        logger.error("Update falho para o id: " + id, e);
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Turma>> findAll() {
	    logger.trace("Recepção de requisição de findAll");
		try {
			List<Turma> lista = this.turmaService.findAll();
			return new ResponseEntity<List<Turma>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Turma> findById(@PathVariable long id) {
		logger.trace("Recepção de requisição de findById com id: " + id);
		try {
			return new ResponseEntity<Turma>(this.turmaService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Turma> deleteById(@PathVariable long id) {
		logger.trace("Recepção de requisição de deleteById com id: " + id);
		try {
			return new ResponseEntity<Turma>(this.turmaService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/gerarTurmas/{idCurso}")
	public ResponseEntity<List<Turma>> gerarTurmas(@PathVariable long idCurso) {
		logger.trace("Recepção de requisição de gerarTurmas para curso com id: " + idCurso);
		try {
			return new ResponseEntity<List<Turma>>(this.turmaService.gerarTurmas(idCurso), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
