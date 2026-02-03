package br.edu.ifpb.tsi.poo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map <Aluno, Integer> media;

    public Estagio(String nome, int cargaHoraria){
        super(nome, cargaHoraria);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.media = new HashMap<>();
    }

    public Integer calcularMedia(Aluno aluno) {
        return media.get(aluno);
    }

    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void addMedia(Aluno aluno, int nota){
        Integer mediaAluno = media.get(aluno);

        if (mediaAluno == null) {
            mediaAluno = nota;
            media.put(aluno, mediaAluno);
        }

        // caso mediaAluno difrente de null atualizar
    }

    public Integer buscaNota(Aluno aluno){
        return media.get(aluno);
    }

    public String toString(){
        return this.getNome() + " " + this.getCargaHoraria() + " horas";
    }
}
