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

    public boolean podeCalcularMedia(Aluno aluno){
        return media.get(aluno) != null;
    }

    public int calcularMediaAluno(Aluno aluno){
        Integer mediaAluno = media.get(aluno);
        if (mediaAluno == null){
            throw new IllegalStateException("Avaliação não cadastrada para o estágio");
        }
        return mediaAluno;
    }

    public String calcularSituacaoPorMedia(int media){
        return (media >= MEDIA_MINIMA_APROVACAO) ? "APROVADO" : "REPROVADO";
    }

    public String calcularSituacaoAluno(Aluno aluno){
        return calcularSituacaoPorMedia(calcularMediaAluno(aluno));
    }

    public void addAluno(Aluno aluno){
        if (aluno == null){
            return;
        }
        if (!alunos.contains(aluno)){
            alunos.add(aluno);
        }
    }

    public void addMedia(Aluno aluno, int nota){
        media.put(aluno, nota);
    }

    public Integer buscaNota(Aluno aluno){
        return media.get(aluno);
    }

    public String toString(){
        return this.getNome() + " " + this.getCargaHoraria() + " horas";
    }
}
