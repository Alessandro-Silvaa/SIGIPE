package app.controller;

import app.entity.Periodo;
import app.repository.PeriodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PeriodoControllerTest {

    @Autowired
    PeriodoController periodoController;

    @MockBean
    PeriodoRepository periodoRepository;

    @BeforeEach
    void setup(){

        List<Periodo> lista = new ArrayList<>();

        lista.add(new Periodo(1,1));
        lista.add(new Periodo(2,2));

        when(this.periodoRepository.findAll()).thenReturn(lista);
        when(this.periodoRepository.save(Mockito.any())).thenReturn(new Periodo(3,3));
        when(this.periodoRepository.findById(1L)).thenReturn(Optional.of(new Periodo(4,4)));
        doNothing().when(this.periodoRepository).deleteById(1L);
    }

    @Test
    void cenario01(){

        ResponseEntity<List<Periodo>> response = this.periodoController.listAll();

        assertEquals(2,response.getBody().size());
    }

    @Test
    void testMetodoSave(){

        Periodo periodo = new Periodo(5,5);

        ResponseEntity<String> response = this.periodoController.save(periodo);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Período Cadastrado: " + periodo.getIdPeriodo());
    }

    @Test
    void testMetodoSaveExceptiom(){

        Periodo periodo = new Periodo(5,5);

        ResponseEntity<String> response = this.periodoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Erro ao cadastrar um período");
    }

    @Test
    void testMetodoUpdate(){

        Periodo periodo = new Periodo(6,6);

        ResponseEntity<String> response = this.periodoController.update(periodo.getIdPeriodo(),periodo);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Período alterado: " + periodo.getIdPeriodo());
    }

    @Test
    void testMetodoUpdateException(){

        Periodo periodo = new Periodo(6,6);

        ResponseEntity<String> response = this.periodoController.update(periodo.getIdPeriodo(),null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Não foi possível alterar o período");
    }

    @Test
    void testMetodoFindById(){

        ResponseEntity<Periodo> response = this.periodoController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Período Encontrado");
    }

    @Test
    void testMetodoFindByIdException(){

        ResponseEntity<Periodo> response = this.periodoController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Período não encontrado com o id específicado");
    }

    @Test
    void testMetodoDeleteById(){

        Periodo periodo = new Periodo(7,7);

        long id = periodo.getIdPeriodo();

        ResponseEntity<String> response = this.periodoController.deleteById(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Instituição excluida com sucesso");
    }

    @Test
    void testMetodoDeleteByIdException(){

        ResponseEntity<String> response = this.periodoController.deleteById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Instituição não encontrada para a exclusão");
    }
}
