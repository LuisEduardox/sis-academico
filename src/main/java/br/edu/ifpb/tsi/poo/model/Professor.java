package br.edu.ifpb.tsi.poo.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor{
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;
    private List<Estagio> estagios;
    
    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = new ArrayList<>();
        this.estagios = new ArrayList<>();
    }
}
