package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Funcao;
import app.service.FuncaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/funcao")
@Validated
public class FuncaoController {
	@Autowired
	FuncaoService funcaoService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<Funcao> lista = this.funcaoService.findAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			if(e.getMessage().equals("Não há funções cadastradas"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Funcao funcao = this.funcaoService.findById(id);
			return ResponseEntity.ok().body(funcao);
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Função não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			this.funcaoService.deleteById(id);
			return ResponseEntity.ok().body("Função excluída com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Função não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Funcao funcao) {
		try {
			this.funcaoService.save(funcao);
			return ResponseEntity.ok().body("Função salva com sucesso");
		}  catch (Exception e) {
			if(e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@Valid @PathVariable long id, @RequestBody Funcao funcao) {
		try {
			this.funcaoService.update(id, funcao);
			return ResponseEntity.ok().body("Função "+funcao.getNome()+" atualizada com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido") || e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Função não encontrada"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}
}
