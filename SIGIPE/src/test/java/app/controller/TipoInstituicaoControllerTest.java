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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import app.entity.Instituicao;
import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;

@SpringBootTest
public class TipoInstituicaoControllerTest {

	@Autowired
	TipoInstituicaoController tipoinstituicaoController;
	
	@MockBean
	TipoInstituicaoRepository tipoinstituicaoRepository;
	
	@BeforeEach
	void setup() {
		
		when(this.tipoinstituicaoRepository.findAll()).thenReturn(new ArrayList<TipoInstituicao>());
		when(this.tipoinstituicaoRepository.findById((long) 1)).thenReturn(Optional.of(new TipoInstituicao(5,"asoas", new ArrayList<Instituicao>())));
        when(this.tipoinstituicaoRepository.save(Mockito.any())).thenReturn(new TipoInstituicao(4,"Bernardo Silva", new ArrayList<Instituicao>()));
        when(this.tipoinstituicaoRepository.findById(1L)).thenReturn(Optional.of(new TipoInstituicao(5,"Casemiro", new ArrayList<Instituicao>())));
        doNothing().when(this.tipoinstituicaoRepository).deleteById(1L);

		
	}
	
	@Test
	void findAllOK() {
		ResponseEntity<List<TipoInstituicao>> response = this.tipoinstituicaoController.findAll();
		HttpStatusCode httpStatus = response.getStatusCode();
		assertEquals(HttpStatus.OK, httpStatus);
	}
	
	@Test
    void UpdateOK(){

       TipoInstituicao tipoinstituicao = new TipoInstituicao(2,"service", new ArrayList<Instituicao>());

       ResponseEntity<String> response = this.tipoinstituicaoController.update(tipoinstituicao.getIdTipoInstituicao(),tipoinstituicao);

       assertEquals(HttpStatus.OK,response.getStatusCode());

       System.out.println("Instituição alterada com sucesso: " + tipoinstituicao.getNome());
    }
	
	@Test
	void UpdateException() {
		
		TipoInstituicao tipoinstituicao = new TipoInstituicao(3,"Zé", new ArrayList<Instituicao>());
		
		ResponseEntity<String> response = this.tipoinstituicaoController.update(tipoinstituicao.getIdTipoInstituicao(),null);
		
	    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
	    
	    System.out.println("Não foi encontrado");
	}

	
	
	@Test
    void SaveOK(){

       TipoInstituicao tipoinstituicao = new TipoInstituicao(6,"John", new ArrayList<Instituicao>());

       ResponseEntity<String> response = this.tipoinstituicaoController.save(tipoinstituicao);

       assertEquals(HttpStatus.CREATED,response.getStatusCode());

       System.out.println("Instituição Salva: " + tipoinstituicao.getNome());
    }
	
	@Test
	void SaveException(){

        ResponseEntity<String> response = this.tipoinstituicaoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());


        System.out.println("Erro ao salvar");
    }
	
	@Test
    void FindByIdOK(){

        ResponseEntity<TipoInstituicao> response = this.tipoinstituicaoController.findById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Instituição encontrada");
    }

	@Test
    void FindByIdException(){

        ResponseEntity<TipoInstituicao> response = this.tipoinstituicaoController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Instituição não encontrada com o Id pesquisado");
    }
	
	@Test
    void DeleteByIdOK(){

         TipoInstituicao tipoinstituicao = new TipoInstituicao(8,"José Almeida", new ArrayList<Instituicao>());

         long id = tipoinstituicao.getIdTipoInstituicao();

         ResponseEntity<String> response = this.tipoinstituicaoController.deleteById(id);

         assertEquals(HttpStatus.OK,response.getStatusCode());

         System.out.println("Id: " + tipoinstituicao.getIdTipoInstituicao() + " -> Instituição excluida: " + tipoinstituicao.getNome());
    }

    @Test
    void DeleteByIdException(){

        ResponseEntity<String> response = this.tipoinstituicaoController.deleteById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        System.out.println("Id não encontrado para exclusão");
    }

	
}
