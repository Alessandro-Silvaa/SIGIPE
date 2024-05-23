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

import app.entity.Demanda;
import app.service.DemandaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/demanda")
@Validated
@CrossOrigin("*")
public class DemandaController {
	@Autowired
	DemandaService demandaService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<Demanda> lista = this.demandaService.findAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			if(e.getMessage().equals("Não há demandas cadastradas"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Demanda demanda = this.demandaService.findById(id);
			return ResponseEntity.ok().body(demanda);
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Demanda não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			this.demandaService.deleteById(id);
			return ResponseEntity.ok().body("Demanda excluída com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Demanda não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Demanda demanda) {
		try {
			this.demandaService.save(demanda);
			return ResponseEntity.ok().body("Demanda salva com sucesso");
		}  catch (Exception e) {
			if(e.getMessage().equals("Chamada inválida") || e.getMessage().equals("Quantidade de grupos inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@Valid @PathVariable long id, @RequestBody Demanda demanda) {
		try {
			this.demandaService.update(id, demanda);
			return ResponseEntity.ok().body("Demanda "+demanda.getIdDemanda()+" atualizada com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido") || e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Demanda não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}
}
