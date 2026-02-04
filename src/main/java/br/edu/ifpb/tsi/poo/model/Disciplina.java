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
    public static final int QUANTIDADE_MINIMA_AVALIACOES = 2;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private Map <Aluno, List<Integer>> notas;
    private int quantidadeAvaliacoes;

    public Disciplina(String nome, int cargaHoraria, int quantidadeAvaliacoes){
        super(nome, cargaHoraria);
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.notas = new HashMap<>();
        this.quantidadeAvaliacoes = Math.max(quantidadeAvaliacoes, QUANTIDADE_MINIMA_AVALIACOES);
    }

    public int getQuantidadeAvaliacoesParaCalculo(){
        return Math.max(this.quantidadeAvaliacoes, QUANTIDADE_MINIMA_AVALIACOES);
    }

    public Integer calcularMedia(List<Integer> notas){
        if (notas == null || notas.isEmpty()){
            throw new IllegalArgumentException("Lista de notas vazia");
        }
        int soma = 0;
        for (int n : notas){
            soma += n;
        }
        int media = soma / notas.size();
        return media;
    }

    public boolean podeCalcularMedia(Aluno aluno){
        List<Integer> notasAluno = notas.get(aluno);
        return notasAluno != null && notasAluno.size() >= getQuantidadeAvaliacoesParaCalculo();
    }

    public int calcularMediaAluno(Aluno aluno){
        if (!podeCalcularMedia(aluno)){
            throw new IllegalStateException("Notas insuficientes para calcular média");
        }
        return calcularMedia(notas.get(aluno));
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
        return notas.get(aluno);
    }
    
    public String toString(){
        return this.getNome() + " " + this.getCargaHoraria() + " horas";
    }
}
