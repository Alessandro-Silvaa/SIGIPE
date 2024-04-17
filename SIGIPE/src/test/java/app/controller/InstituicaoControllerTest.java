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

import app.entity.Demanda;
import app.entity.Demandante;
import app.entity.Instituicao;
import app.entity.TipoInstituicao;
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
        lista.add(new Instituicao(1,"João Henrique","Joinville","89788-888","Limitada LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>()));
        lista.add(new Instituicao(2,"Marcia","Fortaleza","89777-887","Crazy LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>()));
        lista.add(new Instituicao(3,"Juliana","Curitiba","88888-888","Tigrinho LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>()));
        when(this.instituicaoRepository.findAll()).thenReturn(lista);
        when(this.instituicaoRepository.save(Mockito.any())).thenReturn(new Instituicao(4,"Bernardo Silva","Cascavel","11111-111","Ox LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>()));
        when(this.instituicaoRepository.findById(1L)).thenReturn(Optional.of(new Instituicao(5,"Casemiro","Amapá","22222-222","Dragon LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>())));
        doNothing().when(this.instituicaoRepository).deleteById(1L);
    }

    @Test
    void Cenario01(){

        ResponseEntity<List<Instituicao>> response = this.instituicaoController.listAll();

        assertEquals(3, response.getBody().size());
    }

    @Test
    void testMetodoSave(){

       Instituicao instituicao = new Instituicao(6,"John Johns","Maringá","01020-000","Onze Ltda", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>());

       ResponseEntity<String> response = this.instituicaoController.save(instituicao);

       assertEquals(HttpStatus.CREATED,response.getStatusCode());

       System.out.println("Instituição Salva: " + instituicao.getNome());
    }

    @Test
    void testMetodoSaveException(){

        ResponseEntity<String> response = this.instituicaoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Erro ao salvar");
    }

    @Test
    void testMetodoUpdate(){

       Instituicao instituicao = new Instituicao(7,"Armenio","São Paulo","98788-889","Trix LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>());

       ResponseEntity<String> response = this.instituicaoController.update(instituicao.getIdInstituicao(),instituicao);

       assertEquals(HttpStatus.OK,response.getStatusCode());

       System.out.println("Instituição alterada com sucesso: " + instituicao.getNome());
    }

    @Test
    void testMetodoUpdateException(){

        Instituicao instituicao = new Instituicao(7,"Armenio","São Paulo","98788-889","Trix LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>());

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

         Instituicao instituicao = new Instituicao(8,"José Almeida","Palmeiras","98784-788","Acer LTDA", new TipoInstituicao(), new ArrayList<Demandante>(), new ArrayList<Demanda>());

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
