package app.service;

import app.entity.Instituicao;
import app.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    public String save(Instituicao instituicao){

        String cep = instituicao.getCep();
        String nome = instituicao.getNome();

        if(instituicaoRepository.existsByNome(nome))

            throw new RuntimeException();

        if(cep.length() < 9)

            throw new RuntimeException();

       this.instituicaoRepository.save(instituicao);

       return  instituicao.getNome() + " Instituição salva com sucesso";
    }

    public String update(long id, Instituicao instituicao){

         instituicao.setIdInstituicao(id);

         this.instituicaoRepository.save(instituicao);

         return "Instituição alterada com sucesso";
    }

    public List<Instituicao> listAll(){

        return this.instituicaoRepository.findAll();
    }

    public String delete (long id){

      if(id <= 0)

         throw  new RuntimeException();

      this.instituicaoRepository.deleteById(id);

      return "Instituição excluida";
    }

    public Instituicao findById(long id){

        Instituicao instituicao = this.instituicaoRepository.findById(id).get();

        return instituicao;
    }

}
