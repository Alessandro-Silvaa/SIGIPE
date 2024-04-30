package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.entity.Periodo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Funcao;
import app.entity.Grupo;
import app.entity.Pessoa;
import app.repository.PessoaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    PessoaController pessoaController;

    @MockBean
    PessoaRepository pessoaRepository;

    @BeforeEach
    void setup(){

        List<Pessoa> lista = new ArrayList<>();

        lista.add(new Pessoa(1,"João Marcelo","marcelinho12@email.com","Senha111","011.024.987-89","(88)03909-9090",new Periodo(), new Grupo(), new Funcao()));
        lista.add(new Pessoa(2,"Homer","homer12@email.com","Senha121","013.014.983-84","(80)03939-9030",new Periodo(), new Grupo(), new Funcao()));

        when(this.pessoaRepository.findAll()).thenReturn(lista);
        when(this.pessoaRepository.save(Mockito.any())).thenReturn(new Pessoa(3,"Bart","bart12@email.com","BArto","125.256.987-89","(89)97788-7878",new Periodo(), new Grupo(), new Funcao()));
        when(this.pessoaRepository.findById(1L)).thenReturn(Optional.of(new Pessoa(4,"Lisa","lisa@email.com","Lisa12","987.256.959-98","(09)98977-9898",new Periodo(), new Grupo(), new Funcao())));
        doNothing().when(this.pessoaRepository).deleteById(1L);

    }

    @Test
    void TestMetodoListAll(){

        ResponseEntity<List<Pessoa>> response = this.pessoaController.listAll();

        assertEquals(2,response.getBody().size());
    }

    @Test
    void TestMetodoSave(){

        Pessoa pessoa = new Pessoa(5,"Zé","ze@email.com","Ze1","989.989.989-98","(78)78787-7878",new Periodo(), new Grupo(), new Funcao());

        ResponseEntity<String> response = this.pessoaController.save(pessoa);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        System.out.println("Pessoa Salva: " + pessoa.getNome());
    }

    @Test
    void TestMetodoSaveException(){

        ResponseEntity<String> response = this.pessoaController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Erro ao salvar");
    }

    @Test
    void TestMetodoUpdate(){

        Pessoa pessoa = new Pessoa(6,"Marge","marge2@email.com","Senha11","115.545.789-98","(12)98742-9756",new Periodo(), new Grupo(), new Funcao());

        ResponseEntity<String> response = this.pessoaController.update(pessoa.getIdPessoa(),pessoa);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Cadastro Alterado: " + pessoa.getNome());
    }

    @Test
    void TestMetodoUpdateException(){

        Pessoa pessoa = new Pessoa(6,"Marge","marge2@email.com","Senha11","115.545.789-98","(12)98742-9756",new Periodo(), new Grupo(), new Funcao());

        ResponseEntity<String> response = this.pessoaController.update(pessoa.getIdPessoa(),null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Pessoa não encontrada");
    }

    @Test
    void TestMetodoFindById(){

        ResponseEntity<Pessoa> response = this.pessoaController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void TestMetodoFindByIdException(){

        ResponseEntity<Pessoa> response = this.pessoaController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Não foi possível encontrar o id Expecificado");
    }

    @Test
    void TestDeleteById(){

        Pessoa pessoa = new Pessoa(7,"Flanders","flanders21@email.com","Senha11","032.124.985-03","(32)12033-3654",new Periodo(), new Grupo(), new Funcao());

        long id = pessoa.getIdPessoa();

        ResponseEntity<String> response = this.pessoaController.delete(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println("Id Excluido: " + pessoa.getIdPessoa());
    }

    @Test
    void TestDeleteByIdException(){

        ResponseEntity<String> response = this.pessoaController.delete(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id Não Encontrado");
    }
}
