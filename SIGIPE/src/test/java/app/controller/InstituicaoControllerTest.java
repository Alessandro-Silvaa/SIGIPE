package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import app.entity.Instituicao;
import app.repository.InstituicaoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class InstituicaoControllerTest {

    @Autowired
    InstituicaoController instituicaoController;

    @MockBean
    InstituicaoRepository instituicaoRepository;

    @BeforeEach
    void setup(){

        List<Instituicao> lista = new ArrayList<>();
        lista.add(new Instituicao(1,"Uniamérica","Foz do Iguaçu","98989-989","55.555.555/5555-55","Uniamérica Instituicao",null,null,null));
        lista.add(new Instituicao(2,"Fafig","São Paulo","23536-989","44.444.444/4444-44","Fafig Instituicao",null,null,null));
        lista.add(new Instituicao(3,"Cambridge","Amapá","25252-252","33.333.333/3333-33","Cambridge Institute",null,null,null));
        when(this.instituicaoRepository.findAll()).thenReturn(lista);
        when(this.instituicaoRepository.save(Mockito.any())).thenReturn(new Instituicao(4,"MIT","São Paulo","98989-989","22.222.222/2222-22","MIT Institute",null,null,null));
        when(this.instituicaoRepository.findById(1L)).thenReturn(Optional.of(new Instituicao(5,"Harvard","São Paulo","89898-989","11.111.111/1111-11","Harvard Institute",null,null,null)));
        doNothing().when(this.instituicaoRepository).deleteById(1L);
    }

    @Test
    void Cenario01(){

        ResponseEntity<List<Instituicao>> response = this.instituicaoController.listAll();

        assertEquals(3, response.getBody().size());
    }

    @Test
    void testMetodoSave(){

       Instituicao instituicao = new Instituicao(6,"Anhanguera","São Paulo","98989-998","77.777.777/7777-77","Anhanguera Instituicao",null,null,null);

       ResponseEntity<String> response = this.instituicaoController.save(instituicao);

       assertEquals(HttpStatus.CREATED,response.getStatusCode());

       System.out.println("Instituição Salva: " + instituicao.getNome());
    }

    @Test
    void testMetodoSaveException(){

        Instituicao instituicao = new Instituicao(6,"Anhanguera","São Paulo","98989-99","77.777.777/7777-77","Anhanguera Instituicao",null,null,null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {

            this.instituicaoController.save(instituicao);

        });

        System.out.println(exception.getMessage());

    }

    @Test
    void testMetodoUpdate(){

       Instituicao instituicao = new Instituicao(7,"Unicesumar","Foz do Iguaçu","36565-989","88.888.888/8888-88","Unicesumar Instituicao",null,null,null);

       ResponseEntity<String> response = this.instituicaoController.update(instituicao.getIdInstituicao(),instituicao);

       assertEquals(HttpStatus.OK,response.getStatusCode());

       System.out.println("Instituição alterada com sucesso: " + instituicao.getNome());
    }

    @Test
    void testMetodoUpdateException(){

       Instituicao instituicao = new Instituicao(7,"Unicesumar","Foz do Iguaçu","36565-989","88.888.888/8888-88","Unicesumar Instituicao",null,null,null);

        ResponseEntity<String> response = this.instituicaoController.update(instituicao.getIdInstituicao(),null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Instituição não encontrada para alteração");
    }

    @Test
    void testMetodoFindById(){

        ResponseEntity<Instituicao> response = this.instituicaoController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Instituição encontrada");
    }

    @Test
    void testMetodoFindByIdException(){

        ResponseEntity<Instituicao> response = this.instituicaoController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Instituição não encontrada com o Id pesquisado");
    }

    @Test
    void testMetodoDeleteById(){

         Instituicao instituicao =  new Instituicao(8,"Cesufoz","Foz do Iguaçu","36363-363","99.999.999/9999-99","Cesufoz Instituição",null,null,null);

         long id = instituicao.getIdInstituicao();

         ResponseEntity<String> response = this.instituicaoController.delete(id);

         assertEquals(HttpStatus.OK,response.getStatusCode());

         System.out.println("Id: " + instituicao.getIdInstituicao() + " -> Instituição excluida: " + instituicao.getNome());
    }

    @Test
    void testMetodoDeleteByIdException(){

        ResponseEntity<String> response = this.instituicaoController.delete(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id não encontrado para exclusão");
    }

}
