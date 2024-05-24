package app.controller;

import app.entity.Pessoa;
import app.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin("*")
@Validated
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Pessoa pessoa){

        try {

            String salvar = this.pessoaService.save(pessoa);
            return new ResponseEntity<>(salvar, HttpStatus.CREATED);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable long id,@RequestBody Pessoa pessoa){

        try{

            String alterar = this.pessoaService.update(id,pessoa);
            return new ResponseEntity<>(alterar, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable long id){

        try {

            Pessoa pessoa = this.pessoaService.findById(id);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Pessoa>> findAll(){

        try{

            List<Pessoa> pessoa = this.pessoaService.findAll();
            return new ResponseEntity<>(pessoa, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){

        try{

            String deletar = this.pessoaService.deleteById(id);
            return new ResponseEntity<>(deletar, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
