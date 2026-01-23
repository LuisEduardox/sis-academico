package br.edu.ifpb.tsi.poo.app;

import br.edu.ifpb.tsi.poo.controller.SistemaController;
import br.edu.ifpb.tsi.poo.ui.SistemaUI;
public class SistemaApp {
    public static void main(String[] args) {
        SistemaUI sistemaUI = new SistemaUI();
        SistemaController controller = new SistemaController(sistemaUI);
        controller.execute();
    }
}
