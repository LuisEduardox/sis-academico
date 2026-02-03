package br.edu.ifpb.tsi.poo.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map <Aluno, List<Integer>> notas;

    public Disciplina(String nome, int cargaHoraria){
        super(nome, cargaHoraria);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.notas = new HashMap<>();
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

    public void addNota(Aluno aluno, int nota){
        List<Integer> notasAluno = notas.get(aluno);

        if (notasAluno == null) {
            notasAluno = new ArrayList<>();
            notas.put(aluno, notasAluno);
        }

        notasAluno.add(nota);
    }

    public List<Integer> buscaNota(Aluno aluno){
        return notas.getOrDefault(aluno, new ArrayList<>());
    }
    
    public String toString(){
        return this.getNome() + " " + this.getCargaHoraria() + "horas";
    }
}
