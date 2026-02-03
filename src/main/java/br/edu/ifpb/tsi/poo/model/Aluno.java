package br.edu.ifpb.tsi.poo.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {
    private String nome;
    @EqualsAndHashCode.Include
    private String matricula;
    private List<Disciplina> disciplinas;
    private List<Estagio> estagios;

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = new ArrayList<>();
        this.estagios = new ArrayList<>();
    }

    public void addDisciplina(Disciplina disciplina){
        disciplinas.add(disciplina);
    }

    public void addEstagio(Estagio estagio){
        estagios.add(estagio);
    }

    public String toString(){
        return this.getNome() + " " + this.getMatricula();
    }
}



