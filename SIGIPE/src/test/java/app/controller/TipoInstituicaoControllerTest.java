package app.controller;

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
import org.springframework.http.ResponseEntity;

import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TipoInstituicaoControllerTest {

	@Autowired
	TipoInstituicaoController tipoInstituicaoController;
	
	@MockBean
	TipoInstituicaoRepository tipoInstituicaoRepository;
	
	@BeforeEach
	void setup() {
		
		List<TipoInstituicao> lista = new ArrayList<>();
		
		TipoInstituicao tipoinstituicao = new TipoInstituicao();
		
		tipoinstituicao.setIdTipoInstituicao(1);
		tipoinstituicao.setNome("Uniamérica");
		
		TipoInstituicao tipoinstituicao2 = new TipoInstituicao();
		tipoinstituicao2.setIdTipoInstituicao(2);
		tipoinstituicao2.setNome("Cezufoz");
		
		lista.add(tipoinstituicao2);
		
		when(this.tipoInstituicaoRepository.findAll()).thenReturn(lista);
		//when(this.tipoInstituicaoRepository.save(new)).thenReturn(new TipoInstituicao());
	    when(this.tipoInstituicaoRepository.findById(1L)).thenReturn(Optional.of(new TipoInstituicao()));
	    doNothing().when(this.tipoInstituicaoRepository).deleteById(1L);
	}
	

	@Test
    void Cenario01(){

        ResponseEntity<List<TipoInstituicao>> response = this.tipoInstituicaoController.listAll();

       assertEquals(2,response.getBody().size());
    }
	
	
	
	
	
}
