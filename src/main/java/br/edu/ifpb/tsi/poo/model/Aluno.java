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
        if (disciplina == null){
            return;
        }
        if (!estaMatriculadoEmDisciplina(disciplina)){
            disciplinas.add(disciplina);
        }
    }

    public void addEstagio(Estagio estagio){
        if (estagio == null){
            return;
        }
        if (!estaMatriculadoEmEstagio(estagio)){
            estagios.add(estagio);
        }
    }

    public boolean estaMatriculadoEmDisciplina(Disciplina disciplina){
        if (disciplina == null || disciplinas == null){
            return false;
        }
        for (Disciplina d : disciplinas){
            if (d != null && d.getNome() != null && disciplina.getNome() != null
                    && d.getNome().equalsIgnoreCase(disciplina.getNome())){
                return true;
            }
        }
        return false;
    }

    public boolean estaMatriculadoEmEstagio(Estagio estagio){
        if (estagio == null || estagios == null){
            return false;
        }
        for (Estagio e : estagios){
            if (e != null && e.getNome() != null && estagio.getNome() != null
                    && e.getNome().equalsIgnoreCase(estagio.getNome())){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return this.getNome() + " " + this.getMatricula();
    }
}



