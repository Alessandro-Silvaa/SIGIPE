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

import app.entity.Demanda;
import app.service.DemandaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/demanda")
@CrossOrigin("*")
@Validated
public class DemandaController {

	@Autowired
	private DemandaService demandaService;

	private Logger logger = LoggerFactory.getLogger(CursoController.class);

	@PostMapping("/save")
	public ResponseEntity<Demanda> save(@Valid @RequestBody Demanda demanda) {
		try {
			return new ResponseEntity<Demanda>(this.demandaService.save(demanda), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro no salvamento", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

		@PutMapping("/update/{id}")
		public ResponseEntity<Demanda> update(@Valid @RequestBody Demanda demanda, @PathVariable int id) {
			try {
				return new ResponseEntity<Demanda>(this.demandaService.update(id, demanda), HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Erro na atualização", e);
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}

	@GetMapping("/findAll")
	public ResponseEntity<List<Demanda>> findAll() {
		try {
			List<Demanda> lista = this.demandaService.findAll();
			return new ResponseEntity<List<Demanda>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na busca", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{idDemanda}")
	public ResponseEntity<Demanda> findById(@PathVariable long idDemanda) {
		try {
			return new ResponseEntity<Demanda>(this.demandaService.findById(idDemanda), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na busca", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{idDemanda}")
	public ResponseEntity<Demanda> deleteById(@PathVariable long idDemanda) {
		try {
			return new ResponseEntity<Demanda>(this.demandaService.deleteById(idDemanda), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro na deleção", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
