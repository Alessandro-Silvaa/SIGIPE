package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPeriodo;
    @NotNull
    private Integer periodo;

    @OneToMany(mappedBy = "periodo")
    @JsonIgnoreProperties("periodo")
    private List<Professor> professores;

    @OneToMany(mappedBy = "periodo")
    @JsonIgnoreProperties("periodo")
    private List<Aluno> alunos;

}
