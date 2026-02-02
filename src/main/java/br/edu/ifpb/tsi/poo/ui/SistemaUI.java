package br.edu.ifpb.tsi.poo.ui;

import java.util.List;
import br.edu.ifpb.tsi.poo.model.Aluno;
import br.edu.ifpb.tsi.poo.model.Disciplina;
import br.edu.ifpb.tsi.poo.model.Estagio;
import br.edu.ifpb.tsi.poo.model.Professor;
import java.util.ArrayList;

public class SistemaUI {
    private Console console;
    private Menu menuPrincipal;
    
    public SistemaUI(){
        this.console = new Console();
        initMenuPrincipal();
    }

    public void exibaMenuPrincipal(){
        this.menuPrincipal.exiba();
    }

    public int leiaOpcaoMenuPrincipal(){
        return this.menuPrincipal.leiaOpcao();
    }

    public void initMenuPrincipal(){
        List<String> itensMenu = List.of(
        "Cadastrar aluno", // V
        "Cadastrar professor", // V
        "Cadastrar disciplina",// V
        "Cadastrar estágio", // V
        "Matricula aluno em disciplina",
        "Matricula professor em disciplina",
        "Matricula aluno em estágio",
        "Registra nota em disciplina para aluno",
        "Registra avaliação em estágio para aluno",
        "Calcular resultado de aluno em componentes acadêmicos",
        "Consulta situação de aluno",
        "Consulta listagem de disciplina e estágio",
        "Consulta detalhes de avaliação para aluno/disciplina",
        "Consulta sobre todos os alunos matriculados" ,
        "Sair"  
        );
        this.menuPrincipal = new Menu("Menu", itensMenu, "Digite a opção: ", this.console);
    }

    public Aluno cadastraAluno(){
        console.println("\n[ Cadastrar Aluno ]");
        console.print("Nome: ");
        String nome = console.nextLine();
        
        console.print("Matrícula: ");
        String matricula = console.nextLine();

        Aluno aluno = new Aluno(nome, matricula);
        return aluno;
    }

    public Professor cadastraProfessor(){
        console.println("\n[ Cadastrar Professor ]");
        console.print("Nome: ");
        String nome = console.nextLine();
        
        console.print("Matricula: ");
        String matricula = console.nextLine();
        
        Professor professor = new Professor(nome, matricula);
        return professor;
    }
    
    public Disciplina cadastraDisciplina(){
        console.println("\n[ Cadastrar Disciplina ]");
        console.print("Nome: ");
        String nome = console.nextLine();
        
        console.print("Carga Horario: ");
        int cargaHoraria = console.nextInt();
        
        Disciplina disciplina = new Disciplina(nome, cargaHoraria);
        return disciplina;
    }

    public Estagio cadastraEstagio(){
        console.println("\n[ Cadastrar Estagio ]");
        console.print("Nome: ");
        String nome = console.nextLine();
        
        console.print("Carga Horario: ");
        int cargaHoraria = console.nextInt();
        
        Estagio estagio = new Estagio(nome, cargaHoraria);
        return estagio;
    }

    
    public Disciplina exibaMenuSelecaoDisciplina(List<Disciplina> disciplinas) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            itensMenu.add(d.toString());
        }
        Menu menuDisciplinas = new Menu("Disciplina", itensMenu, "Opção: ", this.console);
        int opcao = menuDisciplinas.exiba().leiaOpcao();
        return disciplinas.get(opcao - 1);
    }


    public void limpaTela(){
        this.console.clrscr();
    }
    
    public void exibaCursor() {
        console.showCursor();
    }
    
    public void oculteCursor() {
        console.clearCursor();
    }

    public void pause() {
        console.pause();
    }


    public void imprimaMensagemErro(String mensagem){
        this.oculteCursor();
        this.console.printErrorMessage(mensagem);
        
    }

     public void imprimaMensagemSucesso(String mensagem){
        this.oculteCursor();
        this.console.printSuccessMessage(mensagem);
        
    }

    public void exibaMenu(){
        this.menuPrincipal.exiba();
    }
    
    
}
