package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Demanda;

@SpringBootTest
public class DemandaServiceTest {
	@Autowired
	DemandaService demandaService;

	@Test
	void verificaquantidadeGrupoTestTrue() {
		Demanda demanda =  new Demanda();
		demanda.setQuantidadeGrupo(1);
		boolean retorno = this.demandaService.verificaquantidadeGrupo(demanda);
		assertEquals(true, retorno);
	}
	@Test
	void verificaquantidadeGrupoTestFalse() {
		Demanda demanda =  new Demanda();
		demanda.setQuantidadeGrupo(0);
		boolean retorno = this.demandaService.verificaquantidadeGrupo(demanda);
		assertEquals(false, retorno);
	}
}
