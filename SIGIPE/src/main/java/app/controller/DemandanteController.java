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

import app.entity.Demandante;
import app.service.DemandanteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/demandante")
@CrossOrigin("*")
@Validated
public class DemandanteController {

	@Autowired
	private DemandanteService demandanteService;

	@PostMapping("/save")
	public ResponseEntity<Demandante> save(@Valid @RequestBody Demandante demandante) {
		try {
			return new ResponseEntity<Demandante>(this.demandanteService.save(demandante), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Demandante> update(@Valid @RequestBody Demandante demandante, @PathVariable int id) {
		try {
			return new ResponseEntity<Demandante>(this.demandanteService.update(id, demandante), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Demandante>> findAll() {
		try {
			List<Demandante> lista = this.demandanteService.findAll();
			return new ResponseEntity<List<Demandante>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{idDemandante}")
	public ResponseEntity<Demandante> findById(@PathVariable long idDemandante) {
		try {
			return new ResponseEntity<Demandante>(this.demandanteService.findById(idDemandante), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{idDemandante}")
	public ResponseEntity<Demandante> deleteById(@PathVariable long idDemandante) {
		try {
			return new ResponseEntity<Demandante>(this.demandanteService.deleteById(idDemandante), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
