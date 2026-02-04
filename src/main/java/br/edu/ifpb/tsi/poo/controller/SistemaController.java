package br.edu.ifpb.tsi.poo.controller;

import java.util.List;
import java.util.ArrayList;

import br.edu.ifpb.tsi.poo.ui.SistemaUI;
import br.edu.ifpb.tsi.poo.model.Aluno;
import br.edu.ifpb.tsi.poo.model.Disciplina;
import br.edu.ifpb.tsi.poo.model.Estagio;
import br.edu.ifpb.tsi.poo.model.Professor;
import br.edu.ifpb.tsi.poo.persistence.AlunoRepositorio;
import br.edu.ifpb.tsi.poo.persistence.ProfessorRepositorio;
import br.edu.ifpb.tsi.poo.persistence.EstagioRepositorio;
import br.edu.ifpb.tsi.poo.persistence.DisciplinaRepositorio;

public class SistemaController {
    private static final int SAIR = 14;
    
    private SistemaUI sistemaUI;
    private Aluno alunoCorrente;
    private Professor professorCorrente;
    private Disciplina disciplinaCorrente;
    private Estagio estagioCorrente;
    private AlunoRepositorio alunoRepo = new AlunoRepositorio();
    private ProfessorRepositorio professorRepo = new ProfessorRepositorio();
    private DisciplinaRepositorio disciplinaRepo = new DisciplinaRepositorio();
    private EstagioRepositorio estagioRepo = new EstagioRepositorio();

    public SistemaController(SistemaUI sistemaUI){
        this.sistemaUI = sistemaUI;
    }

    public void execute(){
        int opcaoMenu = SAIR;
        do{
            sistemaUI.exibaMenu();
            opcaoMenu = sistemaUI.leiaOpcaoMenuPrincipal();
            executeOperacao(opcaoMenu);
        } while(opcaoMenu != SAIR);
    }

    private void executeOperacao(int opcaoMenu){
        switch (opcaoMenu) {
            case 1 -> executeCadastraAluno();
            case 2 -> executeCadastraProfessor();
            case 3 -> executeCadastraDisciplina();
            case 4 -> executeCadastraEstagio();
            case 5 -> executeMatriculaAlunoDisciplina();
            case 6 -> executeMatriculaAlunoEstagio();
            case 7 -> executeCadastraNotaDisciplina();
            case 8 -> executeCadastraNotaEstagio();
            case 9 -> executeCalculaMediaComponentesAcademicos();
            case 10 -> executeSituacaoAluno();
            case 11 -> executeExibiComponentesAcademicos();
            case 12 -> executeDetalhesAvalicaoAluno();
            case 13 -> executeSituacaoDeTodosAluno();
        }
    }

    private void executeCadastraAluno(){
        while (true){
            alunoCorrente = sistemaUI.cadastraAluno();
            if (alunoCorrente == null || alunoCorrente.getMatricula() == null || alunoCorrente.getMatricula().isBlank()){
                sistemaUI.imprimaMensagemErro("Cadastro cancelado.");
                sistemaUI.pause();
                return;
            }

            if (!alunoRepo.salvar(alunoCorrente)){
                sistemaUI.imprimaMensagemErro("Já existe aluno com essa matrícula. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            sistemaUI.imprimaMensagemSucesso("Aluno cadastrado com sucesso!");
            sistemaUI.pause();
            return;
        }
    }

    private void executeCadastraProfessor(){
        while (true){
            professorCorrente = sistemaUI.cadastraProfessor();
            if (professorCorrente == null || professorCorrente.getMatricula() == null || professorCorrente.getMatricula().isBlank()){
                sistemaUI.imprimaMensagemErro("Cadastro cancelado.");
                sistemaUI.pause();
                return;
            }

            if (!professorRepo.salvar(professorCorrente)){
                sistemaUI.imprimaMensagemErro("Já existe professor com essa matrícula. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            sistemaUI.imprimaMensagemSucesso("Professor cadastrado com sucesso!");
            sistemaUI.pause();
            return;
        }
    }

    private void executeCadastraDisciplina(){
        while (true){
            disciplinaCorrente = sistemaUI.cadastraDisciplina();
            if (disciplinaCorrente == null || disciplinaCorrente.getNome() == null || disciplinaCorrente.getNome().isBlank()){
                sistemaUI.imprimaMensagemErro("Cadastro cancelado.");
                sistemaUI.pause();
                return;
            }

            if (!disciplinaRepo.salvar(disciplinaCorrente)){
                sistemaUI.imprimaMensagemErro("Já existe disciplina com esse nome. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            sistemaUI.imprimaMensagemSucesso("Disciplina cadastrada com sucesso!");
            sistemaUI.pause();
            return;
        }
    }

    private void executeCadastraEstagio(){
        while (true){
            estagioCorrente = sistemaUI.cadastraEstagio();
            if (estagioCorrente == null || estagioCorrente.getNome() == null || estagioCorrente.getNome().isBlank()){
                sistemaUI.imprimaMensagemErro("Cadastro cancelado.");
                sistemaUI.pause();
                return;
            }

            if (!estagioRepo.salvar(estagioCorrente)){
                sistemaUI.imprimaMensagemErro("Já existe estágio com esse nome. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            sistemaUI.imprimaMensagemSucesso("Estágio cadastrado com sucesso!");
            sistemaUI.pause();
            return;
        }
    }

    private void executeMatriculaAlunoDisciplina(){
        if (alunoRepo.buscarTodos().isEmpty()){
            sistemaUI.imprimaMensagemErro("Não há alunos cadastrados.");
            sistemaUI.pause();
            return;
        }
        if (disciplinaRepo.buscarTodos().isEmpty()){
            sistemaUI.imprimaMensagemErro("Não há disciplinas cadastradas.");
            sistemaUI.pause();
            return;
        }

        while (true){
            alunoCorrente = sistemaUI.exibaMenuSelecaoAluno(alunoRepo.buscarTodos());
            if (alunoCorrente == null){
                return;
            }

            disciplinaCorrente = sistemaUI.exibaMenuSelecaoDisciplina(disciplinaRepo.buscarTodos());
            if (disciplinaCorrente == null){
                return;
            }

            if (alunoCorrente.estaMatriculadoEmDisciplina(disciplinaCorrente)){
                sistemaUI.imprimaMensagemErro("Aluno já está matriculado nessa disciplina.");
                sistemaUI.pause();
                continue;
            }

            disciplinaCorrente.addAluno(alunoCorrente);
            alunoCorrente.addDisciplina(disciplinaCorrente);
            sistemaUI.imprimaMensagemSucesso("Matrícula realizada com sucesso!");
            sistemaUI.pause();
            return;
        }
    }   
    
    private void executeMatriculaAlunoEstagio(){
        if (alunoRepo.buscarTodos().isEmpty()){
            sistemaUI.imprimaMensagemErro("Não há alunos cadastrados.");
            sistemaUI.pause();
            return;
        }
        if (estagioRepo.buscarTodos().isEmpty()){
            sistemaUI.imprimaMensagemErro("Não há estágios cadastrados.");
            sistemaUI.pause();
            return;
        }

        while (true){
            alunoCorrente = sistemaUI.exibaMenuSelecaoAluno(alunoRepo.buscarTodos());
            if (alunoCorrente == null){
                return;
            }

            estagioCorrente = sistemaUI.exibaMenuSelecaoEstagio(estagioRepo.buscarTodos());
            if (estagioCorrente == null){
                return;
            }

            if (alunoCorrente.estaMatriculadoEmEstagio(estagioCorrente)){
                sistemaUI.imprimaMensagemErro("Aluno já está matriculado nesse estágio.");
                sistemaUI.pause();
                continue;
            }

            estagioCorrente.addAluno(alunoCorrente);
            alunoCorrente.addEstagio(estagioCorrente);
            sistemaUI.imprimaMensagemSucesso("Matrícula realizada com sucesso!");
            sistemaUI.pause();
            return;
        }
    }   


     private void executeCadastraNotaDisciplina(){
        while (true){
            String matricula = sistemaUI.leiaMatriculaAluno();
            if (matricula == null || matricula.isBlank()){
                return;
            }

            alunoCorrente = alunoRepo.buscar(matricula);
            if (alunoCorrente == null){
                sistemaUI.imprimaMensagemErro("Aluno não encontrado. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            if (alunoCorrente.getDisciplinas() == null || alunoCorrente.getDisciplinas().isEmpty()){
                sistemaUI.imprimaMensagemErro("Aluno não está matriculado em nenhuma disciplina.");
                sistemaUI.pause();
                return;
            }

            disciplinaCorrente = sistemaUI.exibaMenuSelecaoDisciplina(alunoCorrente.getDisciplinas());
            if (disciplinaCorrente == null){
                return;
            }

            List<Integer> notasAtuais = disciplinaCorrente.buscaNota(alunoCorrente);
            if (notasAtuais != null && notasAtuais.size() >= disciplinaCorrente.getQuantidadeAvaliacoesParaCalculo()){
                sistemaUI.imprimaMensagemErro("Todas as notas dessa disciplina já foram cadastradas.");
                sistemaUI.pause();
                return;
            }

            int nota = sistemaUI.leiaNota();
            disciplinaCorrente.addNota(alunoCorrente, nota);
            sistemaUI.imprimaMensagemSucesso("Nota cadastrada com sucesso!");
            sistemaUI.pause();
            return;
        }
    }

    private void executeCadastraNotaEstagio(){
        while (true){
            String matricula = sistemaUI.leiaMatriculaAluno();
            if (matricula == null || matricula.isBlank()){
                return;
            }

            alunoCorrente = alunoRepo.buscar(matricula);
            if (alunoCorrente == null){
                sistemaUI.imprimaMensagemErro("Aluno não encontrado. Tente novamente.");
                sistemaUI.pause();
                continue;
            }

            if (alunoCorrente.getEstagios() == null || alunoCorrente.getEstagios().isEmpty()){
                sistemaUI.imprimaMensagemErro("Aluno não está matriculado em nenhum estágio.");
                sistemaUI.pause();
                return;
            }

            estagioCorrente = sistemaUI.exibaMenuSelecaoEstagio(alunoCorrente.getEstagios());
            if (estagioCorrente == null){
                return;
            }

            int nota = sistemaUI.leiaNota();
            estagioCorrente.addMedia(alunoCorrente, nota);
            sistemaUI.imprimaMensagemSucesso("Avaliação cadastrada com sucesso!");
            sistemaUI.pause();
            return;
        }
    }


    private void executeCalculaMediaComponentesAcademicos(){
        String matricula = sistemaUI.leiaMatriculaAluno();
        alunoCorrente = alunoRepo.buscar(matricula);

        if (alunoCorrente == null){
            sistemaUI.imprimaMensagemErro("Aluno não encontrado para matrícula: " + matricula);
            sistemaUI.pause();
            return;
        }

        var resultadosCalculados = new ArrayList<String>();
        var resultadosPendentes = new ArrayList<String>();

        for (Disciplina d : alunoCorrente.getDisciplinas()){
            if (d.podeCalcularMedia(alunoCorrente)){
                int media = d.calcularMediaAluno(alunoCorrente);
                String situacao = d.calcularSituacaoPorMedia(media);
                resultadosCalculados.add("DISCIPLINA | " + d.getNome() + " | média=" + media + " | " + situacao);
            } else {
                resultadosPendentes.add("DISCIPLINA | " + d.getNome() + " | notas incompletas");
            }
        }

        for (Estagio e : alunoCorrente.getEstagios()){
            if (e.podeCalcularMedia(alunoCorrente)){
                int media = e.calcularMediaAluno(alunoCorrente);
                String situacao = e.calcularSituacaoPorMedia(media);
                resultadosCalculados.add("ESTÁGIO | " + e.getNome() + " | média=" + media + " | " + situacao);
            } else {
                resultadosPendentes.add("ESTÁGIO | " + e.getNome() + " | avaliação não cadastrada");
            }
        }

        sistemaUI.exibaResultadosCalculoComponentes(alunoCorrente, resultadosCalculados, resultadosPendentes);
        sistemaUI.pause();
    }

    private void executeSituacaoAluno(){
        String matricula = sistemaUI.leiaMatriculaAluno();
        alunoCorrente = alunoRepo.buscar(matricula);

        if (alunoCorrente == null){
            sistemaUI.imprimaMensagemErro("Aluno não encontrado para matrícula: " + matricula);
            sistemaUI.pause();
            return;
        }

        var linhas = new ArrayList<String>();

        for (Disciplina d : alunoCorrente.getDisciplinas()){
            if (d.podeCalcularMedia(alunoCorrente)){
                int media = d.calcularMediaAluno(alunoCorrente);
                String situacao = d.calcularSituacaoPorMedia(media);
                linhas.add("DISCIPLINA | " + d.getNome() + " | " + situacao);
            } else {
                linhas.add("DISCIPLINA | " + d.getNome() + " | INCOMPLETO (sem todas as notas)");
            }
        }

        for (Estagio e : alunoCorrente.getEstagios()){
            if (e.podeCalcularMedia(alunoCorrente)){
                int media = e.calcularMediaAluno(alunoCorrente);
                String situacao = e.calcularSituacaoPorMedia(media);
                linhas.add("ESTÁGIO | " + e.getNome() + " | " + situacao);
            } else {
                linhas.add("ESTÁGIO | " + e.getNome() + " | INCOMPLETO (sem avaliação)");
            }
        }

        sistemaUI.exibaSituacaoAluno(alunoCorrente, linhas);
        sistemaUI.pause();
    }

    private void executeExibiComponentesAcademicos(){
        sistemaUI.exibaDisciplinas(disciplinaRepo.buscarTodos());
        sistemaUI.exibaEstagios(estagioRepo.buscarTodos());
        sistemaUI.pause();
    }

    private void executeDetalhesAvalicaoAluno(){
        String matricula = sistemaUI.leiaMatriculaAluno();
        alunoCorrente = alunoRepo.buscar(matricula);

        if (alunoCorrente == null){
            sistemaUI.imprimaMensagemErro("Aluno não encontrado para matrícula: " + matricula);
            sistemaUI.pause();
            return;
        }

        if (alunoCorrente.getDisciplinas() == null || alunoCorrente.getDisciplinas().isEmpty()){
            sistemaUI.imprimaMensagemErro("Aluno não está matriculado em nenhuma disciplina");
            sistemaUI.pause();
            return;
        }

        disciplinaCorrente = sistemaUI.exibaMenuSelecaoDisciplina(alunoCorrente.getDisciplinas());

        if (!disciplinaCorrente.podeCalcularMedia(alunoCorrente)){
            sistemaUI.imprimaMensagemErro("Não é possível exibir detalhes: notas incompletas para a disciplina selecionada");
            sistemaUI.pause();
            return;
        }

        List<Integer> notas = disciplinaCorrente.buscaNota(alunoCorrente);
        int media = disciplinaCorrente.calcularMediaAluno(alunoCorrente);
        String situacao = disciplinaCorrente.calcularSituacaoPorMedia(media);

        sistemaUI.exibaDetalhesAvaliacaoDisciplina(alunoCorrente, disciplinaCorrente, notas, media, situacao);
        sistemaUI.pause();
    }

     private void executeSituacaoDeTodosAluno(){
        var alunos = alunoRepo.buscarTodos();
        if (alunos == null || alunos.isEmpty()){
            sistemaUI.imprimaMensagemErro("Não há alunos cadastrados");
            sistemaUI.pause();
            return;
        }

        var linhas = new ArrayList<String>();

        for (Aluno a : alunos){
            for (Disciplina d : a.getDisciplinas()){
                if (d.podeCalcularMedia(a)){
                    int media = d.calcularMediaAluno(a);
                    String situacao = d.calcularSituacaoPorMedia(media);
                    linhas.add(a.getNome() + " (" + a.getMatricula() + ") | DISCIPLINA " + d.getNome() + " | média=" + media + " | " + situacao);
                }
            }

            for (Estagio e : a.getEstagios()){
                if (e.podeCalcularMedia(a)){
                    int media = e.calcularMediaAluno(a);
                    String situacao = e.calcularSituacaoPorMedia(media);
                    linhas.add(a.getNome() + " (" + a.getMatricula() + ") | ESTÁGIO " + e.getNome() + " | média=" + media + " | " + situacao);
                }
            }
        }

        sistemaUI.exibaSituacaoTodosAlunos(linhas);
        sistemaUI.pause();
    }
}
