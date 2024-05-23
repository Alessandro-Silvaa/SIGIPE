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

import app.entity.StatusDemanda;
import app.service.StatusDemandaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/status")
@Validated
@CrossOrigin("*")
public class StatusDemandaController {
	@Autowired
	StatusDemandaService statusService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<StatusDemanda> lista = this.statusService.findAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			if(e.getMessage().equals("Não há statuss cadastrados"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			StatusDemanda status = this.statusService.findById(id);
			return ResponseEntity.ok().body(status);
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Status não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			this.statusService.deleteById(id);
			return ResponseEntity.ok().body("Status excluída com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Status não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody StatusDemanda status) {
		try {
			this.statusService.save(status);
			return ResponseEntity.ok().body("Status salvo com sucesso");
		}  catch (Exception e) {
			if(e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@Valid @PathVariable long id, @RequestBody StatusDemanda status) {
		try {
			this.statusService.update(id, status);
			return ResponseEntity.ok().body("Status "+status.getNome()+" atualizado com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido") || e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Status não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}
}
