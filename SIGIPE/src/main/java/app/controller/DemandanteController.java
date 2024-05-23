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
@RequestMapping("api/demandante")
@CrossOrigin("*")
@Validated
public class DemandanteController{
	@Autowired
	DemandanteService demandanteService;
	
	@GetMapping("/findAll")
	public  ResponseEntity<List<Demandante>> findAll(){
		try {
			List<Demandante> lista = this.demandanteService.findAll();
			if(lista.size()<=0)
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			return ResponseEntity.ok().body(lista);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Demandante>findById(@PathVariable long id){
		try {
			
			Demandante demandante = this.demandanteService.findById(id);
			if(id<=0)
				return ResponseEntity.badRequest().body(null);
			
			return ResponseEntity.ok().body(demandante);
			
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("/save")
	
	public ResponseEntity<String> save(@Valid @RequestBody Demandante demandante){
		try {
			this.demandanteService.save(demandante);
			return new ResponseEntity<String>("O Demandante "+demandante.getNome()+"foi salvo com sucesso!",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>("Houve um erro ao salvar : "+e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @PathVariable long id,@RequestBody Demandante demandante){
		try {
			this.demandanteService.update(id, demandante);
			return new ResponseEntity<String>("O Demandante"+demandante.getNome()+" foi atualizado com sucesso",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Houve um erro ao atualizar: "+e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			return new ResponseEntity<String>("O demandante "+this.demandanteService.delete(id)+"foi deletado com sucesso!",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Houve um erro ao deletar o demandante",HttpStatus.BAD_REQUEST);
		}
	}
}


