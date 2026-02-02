package br.edu.ifpb.tsi.poo.model;

import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina extends ComponenteAcademico{
    private List<Aluno> alunos;
    private List<Professor> professores;

    public Disciplina(String nome, int cargaHoraria){
        super(nome, cargaHoraria);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

    @Override
    public float calcularMedia(){
        return 0;
    }

    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void addProfessor(Professor professor){
        professores.add(professor);
    }
}
