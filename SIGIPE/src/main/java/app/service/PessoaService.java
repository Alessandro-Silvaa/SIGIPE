package app.service;

import app.entity.Pessoa;
import app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public String save(Pessoa pessoa){

        this.pessoaRepository.save(pessoa);

        return pessoa.getNome() + "Adicionado com sucesso!";
    }

    public String update(long id, Pessoa pessoa){

        pessoa.setIdPessoa(id);

        this.pessoaRepository.save(pessoa);

        return "Pessoa alterada com sucesso";
    }

    public Pessoa findById(long id){

        Pessoa pessoa = this.pessoaRepository.findById(id).get();

        return pessoa;
    }

    public List<Pessoa> findAll(){

        return this.pessoaRepository.findAll();

    }

    public String deleteById(long id){

        if(id <= 0)

            throw new RuntimeException();

        this.pessoaRepository.deleteById(id);

        return "Pessoa deletada com sucesso";
    }

}
