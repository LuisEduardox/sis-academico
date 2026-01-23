package br.edu.ifpb.tsi.poo.controller;

import br.edu.ifpb.tsi.poo.ui.SistemaUI;

public class SistemaController {
    
    private SistemaUI sistemaUI;

    public SistemaController(SistemaUI sistemaUI){
        this.sistemaUI = sistemaUI;
    }

    public void execute(){
        sistemaUI.exibaMenu();
        
    }
}
