package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Demanda;
import app.service.DemandaService;

@RestController
@RequestMapping("api/demanda")
public class DemandaController {
	@Autowired
	DemandaService demandaService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Demanda>> findAll() {
		try {
			List<Demanda> lista = this.demandaService.findAll();
			if (lista == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			// Log a exceção para fins de depuração
			e.printStackTrace();
			// Retornar uma resposta com status 500 (Internal Server Error)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Demanda> findById(@PathVariable long id) {
		try {
			Demanda demanda = this.demandaService.findById(id);
			if (id <= 0)
				return ResponseEntity.badRequest().body(null);
			if (demanda != null)
				return ResponseEntity.ok().body(demanda);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			// Log a exceção para fins de depuração
			e.printStackTrace();
			// Retornar uma resposta com status 500 (Internal Server Error)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Demanda demanda) {
		try {
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@PathVariable long id, @RequestBody Demanda demanda) {
		try {
			return null;
		} catch (Exception e) {
			return null;
		}

	}

}
