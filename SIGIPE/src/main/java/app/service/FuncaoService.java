package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcao;
import app.repository.FuncaoRepository;
import jakarta.validation.Valid;

@Service
public class FuncaoService {
	@Autowired
	FuncaoRepository funcaoRepository;

	public List<Funcao> findAll() {
		List<Funcao> lista = this.funcaoRepository.findAll();
		if(lista==null)
			throw new RuntimeException("Não há funções cadastradas");
		return lista;
	}

	public Funcao findById(long id) {
		Optional<Funcao> optionalFuncao = this.funcaoRepository.findById(id);
		if (id <= 0)
			throw new RuntimeException("Id inválido");
		if(optionalFuncao==null)
			throw new RuntimeException("Função não encontrada");
		return optionalFuncao.get();
	}

	public void deleteById(long id) {
		if(findById(id)!=null)
			this.funcaoRepository.deleteById(id);
	}

	public void save(Funcao funcao) {
		if(funcao == null)
			throw new RuntimeException("Chamada inválida");
		this.funcaoRepository.save(funcao);
	}

	public void update(@Valid long id, Funcao funcao) {
		if(funcao == null)
			throw new RuntimeException("Chamada inválida");
		if(findById(id)!=null) {
			funcao.setIdFuncao(id);
			this.funcaoRepository.save(funcao);
		}
		
	}
}
