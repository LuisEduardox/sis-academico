package br.edu.ifpb.tsi.poo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Estagio extends ComponenteAcademico{
    private List<Aluno> alunos;
    private List<Professor> professores;

    public Estagio(String nome, int cargaHoraria){
        super(nome, cargaHoraria);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

    @Override
    public float calcularMedia() {
        return 0;
    }

    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public String toString(){
        return this.getNome() + " " + this.getCargaHoraria() + "horas";
    }
}
