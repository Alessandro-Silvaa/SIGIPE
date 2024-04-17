package app.controller;

import app.entity.Instituicao;
import app.service.InstituicaoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoService instituicaoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Instituicao instituicao){

    try {

    String salvar = this.instituicaoService.save(instituicao);
    return new ResponseEntity<>(salvar, HttpStatus.CREATED);

    }catch (Exception e){

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
}

   @PutMapping("/update/{id}")
   public ResponseEntity<String> update(@PathVariable long id, @RequestBody Instituicao instituicao){

      try {

          String alterar = this.instituicaoService.update(id,instituicao);
          return new ResponseEntity<>(alterar,HttpStatus.OK);

      }catch (Exception e){

          return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

      }
    }

   @GetMapping("/findById/{id}")
   public ResponseEntity<Instituicao> findById(@PathVariable long id){

     try {

         Instituicao instituicao = this.instituicaoService.findById(id);
         return new ResponseEntity<>(instituicao,HttpStatus.OK);

     }catch (Exception e){

         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

     }

   }

   @GetMapping("/listAll")
    public ResponseEntity<List<Instituicao>> listAll() {

       try {

           List<Instituicao> lista = this.instituicaoService.listAll();
           return new ResponseEntity<>(lista, HttpStatus.OK);

       } catch (Exception e) {

           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

       }
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        try {
            String deletar = this.instituicaoService.delete(id);
            return new ResponseEntity<>(deletar, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
