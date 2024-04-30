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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Grupo;
import app.service.GrupoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/grupo")
@Validated
public class GrupoController {
	@Autowired
	GrupoService grupoService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<Grupo> lista = this.grupoService.findAll();
			return ResponseEntity.ok().body(lista);
		} catch (Exception e) {
			if(e.getMessage().equals("Não há grupos cadastrados"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Grupo grupo = this.grupoService.findById(id);
			return ResponseEntity.ok().body(grupo);
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Grupo não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			this.grupoService.deleteById(id);
			return ResponseEntity.ok().body("Grupo excluída com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Grupo não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Grupo grupo) {
		try {
			this.grupoService.save(grupo);
			return ResponseEntity.ok().body("Grupo salvo com sucesso");
		}  catch (Exception e) {
			if(e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updade(@Valid @PathVariable long id, @RequestBody Grupo grupo) {
		try {
			this.grupoService.update(id, grupo);
			return ResponseEntity.ok().body("Grupo "+grupo.getNome()+" atualizado com sucesso");
		} catch (Exception e) {
			if(e.getMessage().equals("Id inválido") || e.getMessage().equals("Chamada inválida"))
				return ResponseEntity.badRequest().body(e.getMessage());
			if(e.getMessage().equals("Grupo não encontrado"))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu a excessão: " + e.getMessage());
		}

	}

	@GetMapping("/findByBuscaNome")
    public ResponseEntity<List<Grupo>> findByBuscaNome(@Valid @RequestParam String nome){

        try {

            List<Grupo> grupo = this.grupoService.findByBuscaNome(nome);
            return new ResponseEntity<>(grupo,HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }
	
	

}
