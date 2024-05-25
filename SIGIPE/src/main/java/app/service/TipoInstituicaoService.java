package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.TipoInstituicao;
import app.repository.TipoInstituicaoRepository;

@Service
public class TipoInstituicaoService {
	
	@Autowired
	TipoInstituicaoRepository tipoInstituicaoRepository;
	
	public String save(TipoInstituicao tipoInstituicao) {
		
		this.tipoInstituicaoRepository.save(tipoInstituicao);
		
		return tipoInstituicao.getNome() + " Adicionado com sucesso";
	}
	
	public String update(long id,TipoInstituicao tipoInstituicao) {
		
		tipoInstituicao.setIdTipoInstituicao(id);
		this.tipoInstituicaoRepository.save(tipoInstituicao);
		
		return tipoInstituicao.getNome() + " Alterado com sucesso";
	}
	
	public List<TipoInstituicao> findAll(){
		
		return tipoInstituicaoRepository.findAll();
		
	}
	
	public String deleteById(long id) {
		
		if(id <= 0) 
			
			throw new RuntimeException();
		
		this.tipoInstituicaoRepository.deleteById(id);
		
		return "Excluido com sucesso";
	}
	
	public TipoInstituicao findById(long id){

        TipoInstituicao tipoinstituicao = this.tipoInstituicaoRepository.findById(id).get();

        return tipoinstituicao;
    }
	
}
