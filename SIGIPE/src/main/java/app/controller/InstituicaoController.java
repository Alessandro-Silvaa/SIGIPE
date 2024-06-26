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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Instituicao;
import app.service.InstituicaoService;
import jakarta.validation.Valid;


@Validated
@RestController
@RequestMapping("/api/instituicao")
@CrossOrigin("*")
public class InstituicaoController {

    @Autowired
    InstituicaoService instituicaoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody Instituicao instituicao){

    try {

    String salvar = this.instituicaoService.save(instituicao);
    return new ResponseEntity<>(salvar, HttpStatus.CREATED);

    }catch (Exception e){

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }
}

   @PutMapping("/update/{id}")
   public ResponseEntity<String> update(@Valid @PathVariable long id, @RequestBody Instituicao instituicao){

      try {

          String alterar = this.instituicaoService.update(id,instituicao);
          return new ResponseEntity<>(alterar,HttpStatus.OK);

      }catch (Exception e){

          return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

      }
    }

   @GetMapping("/findById/{id}")
   public ResponseEntity<Instituicao> findById(@Valid @PathVariable long id){

     try {

         Instituicao instituicao = this.instituicaoService.findById(id);
         return new ResponseEntity<>(instituicao,HttpStatus.OK);

     }catch (Exception e){

         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

     }

   }

   @GetMapping("/findAll")
    public ResponseEntity<List<Instituicao>> findAll() {

       try {

           List<Instituicao> lista = this.instituicaoService.findAll();
           return new ResponseEntity<>(lista, HttpStatus.OK);

       } catch (Exception e) {

           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

       }
   }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable long id){
        try {
            String deletar = this.instituicaoService.deleteById(id);
            return new ResponseEntity<>(deletar, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByRazaoSocial")
    public ResponseEntity<List<Instituicao>> findByRazaoSocial(@Valid @RequestParam String razaoSocial){

        try {

            List<Instituicao> instituicao = this.instituicaoService.findByRazaoSocial(razaoSocial);
            return new ResponseEntity<>(instituicao,HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/findByCidade")
    public ResponseEntity<List<Instituicao>> findByCidade(@Valid @RequestParam String cidade){

        try {

            List<Instituicao> instituicao = this.instituicaoService.findByCidade(cidade);
            return new ResponseEntity<>(instituicao,HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }

}
