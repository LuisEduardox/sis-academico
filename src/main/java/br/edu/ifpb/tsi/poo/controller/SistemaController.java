package br.edu.ifpb.tsi.poo.controller;

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
            sistemaUI.limpaTela();
            sistemaUI.exibaMenu();
            opcaoMenu = sistemaUI.leiaOpcaoMenuPrincipal();
            sistemaUI.limpaTela();
            executeOperacao(opcaoMenu);
        } while(opcaoMenu != SAIR);
    }

    private void executeOperacao(int opcaoMenu){
        switch (opcaoMenu) {
            case 1 -> executeCadastraAluno();
            case 2 -> executeCadastraProfessor();
            case 3 -> executeCadastraDisciplina();
            case 4 -> executeCadastraEstagio();
            case 5 -> executeMatriculaAluno();
        }
    }

    private void executeCadastraAluno(){
        alunoCorrente = sistemaUI.cadastraAluno();
        alunoRepo.salvar(alunoCorrente);
        sistemaUI.limpaTela();
        sistemaUI.imprimaMensagemSucesso("Aluno cadastrado com sucesso!");
        sistemaUI.pause();
    }

    private void executeCadastraProfessor(){
        professorCorrente = sistemaUI.cadastraProfessor();
        professorRepo.salvar(professorCorrente);
        sistemaUI.limpaTela();
        sistemaUI.imprimaMensagemSucesso("Professor cadastrado com sucesso!");
        sistemaUI.pause();
    }

    private void executeCadastraDisciplina(){
        disciplinaCorrente = sistemaUI.cadastraDisciplina();
        disciplinaRepo.salvar(disciplinaCorrente);
        sistemaUI.limpaTela();
        sistemaUI.imprimaMensagemSucesso("Disciplina cadastrado com sucesso!");
        sistemaUI.pause();
    }

    private void executeCadastraEstagio(){
        estagioCorrente = sistemaUI.cadastraEstagio();
        estagioRepo.salvar(estagioCorrente);
        sistemaUI.limpaTela();
        sistemaUI.imprimaMensagemSucesso("Estagio cadastrado com sucesso!");
        sistemaUI.pause();
    }

    private void executeMatriculaAluno(){
        disciplinaCorrente = sistemaUI.exibaMenuSelecaoDisciplina(disciplinaRepo.buscarTodos());
        disciplinaCorrente.addAluno(alunoCorrente);
        sistemaUI.limpaTela();
    }   
    
    private void executeMudarAlunoCorrente() {
        
    }

}
