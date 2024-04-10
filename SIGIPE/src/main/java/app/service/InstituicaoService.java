package app.service;

import app.entity.Instituicao;
import app.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    public String save(Instituicao instituicao){

       this.instituicaoRepository.save(instituicao);

       return "Instituição cadastrada com sucesso";
    }


}
