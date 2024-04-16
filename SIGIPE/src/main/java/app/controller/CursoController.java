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

import app.entity.Curso;
import app.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/curso")
@Validated
public class CursoController {
	@Autowired
	CursoService cursoService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<Curso> lista = this.cursoService.findAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			if(e.getMessage().equals("Não há cursos cadastrados"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Curso curso = this.cursoService.findById(id);
			return ResponseEntity.ok().body(curso);
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Curso não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			this.cursoService.deleteById(id);
			return ResponseEntity.ok().body("Curso excluída com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Curso não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Curso curso) {
		try {
			this.cursoService.save(curso);
			return ResponseEntity.ok().body("Curso salvo com sucesso");
		}  catch (Exception e) {
			if(e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@Valid @PathVariable long id, @RequestBody Curso curso) {
		try {
			this.cursoService.update(id, curso);
			return ResponseEntity.ok().body("Curso "+curso.getNome()+" atualizado com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido") || e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Curso não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}
}
