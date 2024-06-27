package app.controller;

import java.util.List;

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

import app.entity.StatusDemanda;
import app.service.StatusDemandaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/statusDemanda")
@CrossOrigin("*")
@Validated
public class StatusDemandaController {

	@Autowired
	private StatusDemandaService statusDemandaService;

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PostMapping("/save")
	public ResponseEntity<StatusDemanda> save(@Valid @RequestBody StatusDemanda statusDemanda) {
		try {
			return new ResponseEntity<StatusDemanda>(this.statusDemandaService.save(statusDemanda), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@PutMapping("/update/{id}")
	public ResponseEntity<StatusDemanda> update(@Valid @RequestBody StatusDemanda statusDemanda, @PathVariable int id) {
		try {
			return new ResponseEntity<StatusDemanda>(this.statusDemandaService.update(id, statusDemanda), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findAll")
	public ResponseEntity<List<StatusDemanda>> findAll() {
		try {
			List<StatusDemanda> lista = this.statusDemandaService.findAll();
			return new ResponseEntity<List<StatusDemanda>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<StatusDemanda> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusDemanda>(this.statusDemandaService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('coordenadorExtensao')")
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<StatusDemanda> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<StatusDemanda>(this.statusDemandaService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
