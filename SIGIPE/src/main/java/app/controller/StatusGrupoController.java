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

import app.entity.StatusGrupo;
import app.service.StatusGrupoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/statusGrupo")
@CrossOrigin("*")
@Validated
public class StatusGrupoController {

	@Autowired
	private StatusGrupoService statusGrupoService;

	@PostMapping("/save")
	public ResponseEntity<StatusGrupo> save(@Valid @RequestBody StatusGrupo statusGrupo) {
		try {
			return new ResponseEntity<StatusGrupo>(this.statusGrupoService.save(statusGrupo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StatusGrupo> update(@Valid @RequestBody StatusGrupo statusGrupo, @PathVariable int id) {
		try {
			return new ResponseEntity<StatusGrupo>(this.statusGrupoService.update(id, statusGrupo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<StatusGrupo>> findAll() {
		try {
			List<StatusGrupo> lista = this.statusGrupoService.findAll();
			return new ResponseEntity<List<StatusGrupo>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<StatusGrupo> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusGrupo>(this.statusGrupoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<StatusGrupo> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusGrupo>(this.statusGrupoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
