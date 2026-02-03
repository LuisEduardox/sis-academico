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
        "Matricular aluno em disciplina", //V
        "Matricular aluno em estágio",// V
        "Registrar nota em disciplina para aluno",
        "Registrar avaliação em estágio para aluno",
        "Calcular resultado de aluno em componentes acadêmicos",
        "Consultar situação de aluno",
        "Consultar listagem de disciplina e estágio", //V
        "Consultar detalhes de avaliação para aluno/disciplina",
        "Consultar sobre todos os alunos matriculados" ,
        "Sair"   //V
        );
        this.menuPrincipal = new Menu("Menu", itensMenu, "Digite a opção: ", this.console);
    }

    public String leiaMatriculaAluno(){
        console.print("Digite a matrícula do aluno: ");
        String matricula = console.nextLine();
        return matricula;
    }

    public int leiaNota(){
        console.print("Digite a nota: ");
        int nota = console.nextInt();
        return nota;
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

    public Aluno exibaMenuSelecaoAluno(List<Aluno> alunos){
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Aluno a : alunos){
            itensMenu.add(a.toString());
        }
        Menu menuAlunos = new Menu("Alunos", itensMenu, "Selecionar alunos: ", this.console);
        int opcao =  menuAlunos.exiba().leiaOpcao();
        return alunos.get(opcao - 1);
    }

    
    public Disciplina exibaMenuSelecaoDisciplina(List<Disciplina> disciplinas) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Disciplina d : disciplinas) { 
            itensMenu.add(d.toString());
        }
        Menu menuDisciplinas = new Menu("Disciplina", itensMenu, "Selecionar disciplina: ", this.console);
        int opcao = menuDisciplinas.exiba().leiaOpcao();
        return disciplinas.get(opcao - 1);
    }

     public Estagio exibaMenuSelecaoEstagio(List<Estagio> estagio) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Estagio e : estagio) {
            itensMenu.add(e.toString());
        }
        Menu menuEstagios = new Menu("Estagio", itensMenu, "Selecionar estagio: ", this.console);
        int opcao = menuEstagios.exiba().leiaOpcao();
        return estagio.get(opcao - 1);
    }

     public void exibaDisciplinas(List<Disciplina> disciplinas) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            itensMenu.add(d.toString());
        }
        Menu menuDisciplinas = new Menu("Disciplina", itensMenu, "", this.console); 
        menuDisciplinas.exiba();
        }

        public void exibaEstagios(List<Estagio> estagios) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Estagio e : estagios) {
            itensMenu.add(e.toString());
        }
        Menu menuEstagios = new Menu("Estagios", itensMenu, "", this.console); 
        menuEstagios.exiba();
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
