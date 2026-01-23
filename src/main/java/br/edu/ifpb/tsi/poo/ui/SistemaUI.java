package br.edu.ifpb.tsi.poo.ui;

import java.util.List;
public class SistemaUI {
    private Console console;
    private Menu menuPrincipal;
    
    public SistemaUI(){
        this.console = new Console();
        initMenuPrincipal();
    }

    public void initMenuPrincipal(){
        List<String> itensMenu = List.of(
        "Cadastrar aluno",
        "Cadastrar professor", 
        "Cadastrar disciplina",
        "Cadastrar estágio"
        );
        this.menuPrincipal = new Menu("Menu", itensMenu, "Digite a opção: ", this.console);
    }

    public void exibaMenu(){
        this.menuPrincipal.exiba();
    }
}
