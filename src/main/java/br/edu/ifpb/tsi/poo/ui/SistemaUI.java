package br.edu.ifpb.tsi.poo.ui;

import java.util.List;
import br.edu.ifpb.tsi.poo.model.Aluno;
import br.edu.ifpb.tsi.poo.model.Disciplina;
import br.edu.ifpb.tsi.poo.model.Estagio;
import br.edu.ifpb.tsi.poo.model.Professor;
import java.util.ArrayList;
import java.util.Collections;

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
        "Registrar nota em disciplina para aluno", //V
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
        while (true) {
            console.print("Digite a nota (0 a 100): ");
            try {
                int nota = console.nextInt();
                if (nota >= 0 && nota <= 100) {
                    return nota;
                }
            } catch (NumberFormatException ignored) {
                // continua
            }
            console.println(Cores.VERMELHO + "Nota inválida. Tente novamente." + Cores.RESET);
        }
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

        int quantidadeAvaliacoes;
        while (true){
            console.print("Quantidade de avaliações (mínimo 2): ");
            quantidadeAvaliacoes = console.nextInt();
            if (quantidadeAvaliacoes >= Disciplina.QUANTIDADE_MINIMA_AVALIACOES){
                break;
            }
            console.println("Quantidade inválida. Informe um número >= 2.");
        }
        
        Disciplina disciplina = new Disciplina(nome, cargaHoraria, quantidadeAvaliacoes);
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
        if (alunos == null || alunos.isEmpty()){
            imprimaMensagemErro("Não há alunos disponíveis para seleção.");
            pause();
            return null;
        }
        List<String> itensMenu = new ArrayList<>();
        for (Aluno a : alunos){
            itensMenu.add(a.toString());
        }
        itensMenu.add("Voltar");
        Menu menuAlunos = new Menu("Alunos", itensMenu, "Selecionar alunos: ", this.console);
        int opcao =  menuAlunos.exiba().leiaOpcao();
        if (opcao == itensMenu.size()){
            return null;
        }
        return alunos.get(opcao - 1);
    }

    
    public Disciplina exibaMenuSelecaoDisciplina(List<Disciplina> disciplinas) {
        this.exibaCursor();
        if (disciplinas == null || disciplinas.isEmpty()){
            imprimaMensagemErro("Não há disciplinas disponíveis para seleção.");
            pause();
            return null;
        }
        List<String> itensMenu = new ArrayList<>();
        for (Disciplina d : disciplinas) { 
            itensMenu.add(d.toString());
        }
        itensMenu.add("Voltar");
        Menu menuDisciplinas = new Menu("Disciplina", itensMenu, "Selecionar disciplina: ", this.console);
        int opcao = menuDisciplinas.exiba().leiaOpcao();
        if (opcao == itensMenu.size()){
            return null;
        }
        return disciplinas.get(opcao - 1);
    }

     public Estagio exibaMenuSelecaoEstagio(List<Estagio> estagio) {
        this.exibaCursor();
        if (estagio == null || estagio.isEmpty()){
            imprimaMensagemErro("Não há estágios disponíveis para seleção.");
            pause();
            return null;
        }
        List<String> itensMenu = new ArrayList<>();
        for (Estagio e : estagio) {
            itensMenu.add(e.toString());
        }
        itensMenu.add("Voltar");
        Menu menuEstagios = new Menu("Estagio", itensMenu, "Selecionar estagio: ", this.console);
        int opcao = menuEstagios.exiba().leiaOpcao();
        if (opcao == itensMenu.size()){
            return null;
        }
        return estagio.get(opcao - 1);
    }

     public void exibaDisciplinas(List<Disciplina> disciplinas) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            itensMenu.add(d.toString());
        }
        Menu menuDisciplinas = new Menu("Disciplinas", itensMenu, "", this.console); 
        menuDisciplinas.exiba(false);
        }

        public void exibaEstagios(List<Estagio> estagios) {
        this.exibaCursor();
        List<String> itensMenu = new ArrayList<>();
        for (Estagio e : estagios) {
            itensMenu.add(e.toString());
        }
        Menu menuEstagios = new Menu("Estagios", itensMenu, "", this.console); 
        menuEstagios.exiba(false);
        }

        public void exibaResultadosCalculoComponentes(Aluno aluno, List<String> calculados, List<String> pendentes){
            if (calculados == null) calculados = Collections.emptyList();
            if (pendentes == null) pendentes = Collections.emptyList();

            List<String> linhas = new ArrayList<>();
            linhas.add("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            linhas.add(" ");

            if (calculados.isEmpty()){
                linhas.add("Nenhum componente com média calculável.");
            } else {
                linhas.add("Calculados:");
                linhas.addAll(calculados);
            }

            if (!pendentes.isEmpty()){
                linhas.add(" ");
                linhas.add("Atenção: componentes sem notas completas:");
                linhas.addAll(pendentes);
            }

            exibaListagem("Resultado acadêmico", linhas);
        }

        public void exibaSituacaoAluno(Aluno aluno, List<String> linhas){
            List<String> itens = new ArrayList<>();
            itens.add("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            itens.add(" ");

            if (linhas == null || linhas.isEmpty()){
                itens.add("Nenhum componente matriculado.");
            } else {
                itens.addAll(linhas);
            }

            exibaListagem("Situação do aluno", itens);
        }

        public void exibaDetalhesAvaliacaoDisciplina(Aluno aluno, Disciplina disciplina, List<Integer> notas, int media, String situacao){
            List<String> itens = new ArrayList<>();
            itens.add("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            itens.add("Disciplina: " + disciplina.getNome());
            itens.add("Notas: " + (notas == null ? "[]" : notas.toString()));
            itens.add("Média: " + media);
            itens.add("Situação: " + situacao);
            exibaListagem("Detalhes de avaliação", itens);
        }

        public void exibaSituacaoTodosAlunos(List<String> linhas){
            List<String> itens = new ArrayList<>();
            if (linhas == null || linhas.isEmpty()){
                itens.add("Nenhum aluno possui componentes com notas completas para cálculo.");
            } else {
                itens.addAll(linhas);
            }
            exibaListagem("Situação de todos os alunos", itens);
        }

        private void exibaListagem(String titulo, List<String> linhas){
            this.exibaCursor();
            if (linhas == null) linhas = Collections.emptyList();

            if (linhas.isEmpty()){
                new Menu(titulo, List.of("(vazio)"), "", this.console).exiba(false);
                return;
            }

            new Menu(titulo, linhas, "", this.console).exiba(false);
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
