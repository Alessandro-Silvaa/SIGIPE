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

import app.entity.TipoInstituicao;
import app.service.TipoInstituicaoService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/tipoInstituicao")
public class TipoInstituicaoController {
	
	@Autowired
	TipoInstituicaoService tipoInstituicaoService;
	
	 @PostMapping("/save")
	    public ResponseEntity<String> save(@Valid @RequestBody TipoInstituicao tipoinstituicao){

	    try {

	    String salvar = this.tipoInstituicaoService.save(tipoinstituicao);
	    return new ResponseEntity<>(salvar, HttpStatus.CREATED);

	    }catch (Exception e){

	        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

	    }
	}
	 
	 @PutMapping("/update/{id}")
	   public ResponseEntity<String> update(@Valid @PathVariable long id, @RequestBody TipoInstituicao tipoinstituicao){

	      try {

	          String alterar = this.tipoInstituicaoService.update(id, tipoinstituicao);
	          return new ResponseEntity<>(alterar,HttpStatus.OK);

	      }catch (Exception e){

	          return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

	      }
	    }
	
	 @GetMapping("/findById/{id}")
	   public ResponseEntity<TipoInstituicao> findById(@Valid @PathVariable long id){

	     try {

	         TipoInstituicao tipoinstituicao = this.tipoInstituicaoService.findById(id);
	         return new ResponseEntity<>(tipoinstituicao,HttpStatus.OK);

	     }catch (Exception e){

	         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

	     }

	   }
	 
	 @GetMapping("/listAll")
	    public ResponseEntity<List<TipoInstituicao>> listAll() {

	       try {

	           List<TipoInstituicao> lista = this.tipoInstituicaoService.listAll();
	           return new ResponseEntity<>(lista, HttpStatus.OK);

	       } catch (Exception e) {

	           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	       }
	   }
	 
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> delete(@Valid @PathVariable long id){
	        try {
	            String deletar = this.tipoInstituicaoService.delete(id);
	            return new ResponseEntity<>(deletar, HttpStatus.OK);

	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	    }
}
