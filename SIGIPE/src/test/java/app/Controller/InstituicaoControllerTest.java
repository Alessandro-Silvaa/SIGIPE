package app.Controller;

import app.controller.InstituicaoController;
import app.entity.Instituicao;
import app.repository.InstituicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InstituicaoControllerTest {

    @Autowired
    InstituicaoController instituicaoController;

    @MockBean
    InstituicaoRepository instituicaoRepository;

    @BeforeEach
    void setup() {

        List<Instituicao> lista = new ArrayList<>();
        lista.add(new Instituicao(1, "Uniamérica", "Foz do Iguaçu", null));
        lista.add(new Instituicao(2, "Unicesumar", "Foz do Iguaçu", null));
        lista.add(new Instituicao(3, "Fafig", "São Paulo", null));
        when(this.instituicaoRepository.save(Mockito.any())).thenReturn(new Instituicao(4, "Cesufoz", "Foz do Iguaçu", null));
        when(this.instituicaoRepository.findAll()).thenReturn(lista);
        when(this.instituicaoRepository.findById(1L)).thenReturn(Optional.of(new Instituicao(5, "Anhanguera", "São Paulo", null)));
        doNothing().when(this.instituicaoRepository).deleteById(1L);
    }

    @Test
    void testMetodoFindAll() {

        ResponseEntity<List<Instituicao>> response = this.instituicaoController.findAll();

        assertEquals(3, response.getBody().size());

    }

    @Test
    void testMetodoSave() {

        Instituicao instituicao = new Instituicao(6, "Unopar", "Londrina", null);

        ResponseEntity<Instituicao> response = this.instituicaoController.save(instituicao);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        System.out.println("Instituição Salva com sucesso: " + instituicao.getNome());
    }

    @Test
    void testMetodoSaveException() {

        Instituicao instituicao = new Instituicao(6, "", "Londrina", null);

        ResponseEntity<Instituicao> response = this.instituicaoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        System.out.println(" Instituição não salva");
    }

    /*
    @Test
    void testMetodoUpdate(){

        Instituicao instituicao = new Instituicao(6,"Unopar","Curitiba",null);

        ResponseEntity<Instituicao> response = this.instituicaoController.update(instituicao,instituicao.getId());

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    */

    @Test
    void testMetodoUpdateException(){

        Instituicao instituicao = new Instituicao(8,"Sigipe","Foz do Iguaçu",null);

        ResponseEntity<Instituicao> response = this.instituicaoController.update(instituicao,instituicao.getId());

        assertEquals(HttpStatus.BAD_REQUEST,
                response.getStatusCode());

        System.out.println("Instituição não encontrada: " + instituicao.getNome());
    }


}


