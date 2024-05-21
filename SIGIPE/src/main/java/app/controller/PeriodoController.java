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

import app.entity.Periodo;
import app.service.PeriodoService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/periodo")
public class PeriodoController {

    @Autowired
    PeriodoService periodoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody Periodo periodo){

        try {

             String salvar = this.periodoService.save(periodo);
             return new ResponseEntity<>(salvar,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<String> update(@Valid @PathVariable long id,@RequestBody Periodo periodo){

        try {

            String alterar = this.periodoService.update(id,periodo);
            return new ResponseEntity<>(alterar,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
   }

   @GetMapping("/listAll")
   public ResponseEntity<List<Periodo>> listAll(){

        try {

            List<Periodo> listar = this.periodoService.listAll();
            return new ResponseEntity<>(listar,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
   }

   @GetMapping("findById/{id}")
   public ResponseEntity<Periodo> findById(@Valid @PathVariable long id){

        try {

             Periodo periodo = this.periodoService.findById(id);
             return new ResponseEntity<>(periodo,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
   }

   @DeleteMapping("/deleteById/{id}")
   public ResponseEntity<String> deleteById(@Valid @PathVariable long id){

        try {

             String deletar = this.periodoService.deleteById(id);
             return new ResponseEntity<>(deletar,HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
   }
}
