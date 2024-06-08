package app.service;

import app.entity.Periodo;
import app.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoService {

    @Autowired
    PeriodoRepository periodoRepository;

    public String save(Periodo periodo){

         this.periodoRepository.save(periodo);

         return "Periodo cadastrado";
    }

   public String update(long id, Periodo periodo){

        periodo.setIdPeriodo(id);
       this.periodoRepository.save(periodo);

       return "Período Alterado";
   }

   public List<Periodo> findAll(){

        return periodoRepository.findAll();
   }

  public Periodo findById(long id){

        Periodo periodo = this.periodoRepository.findById(id).get();

        return periodo;
  }

  public String deleteById(long id){

     this.periodoRepository.deleteById(id);

     return "Período Excluido";
  }
}
