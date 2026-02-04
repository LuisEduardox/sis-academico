package br.edu.ifpb.tsi.poo.ui;

import java.util.Scanner;

public class Console {
    private Scanner in;

    public Console() {
        this.in = new Scanner(System.in);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public String nextLine() {
        return in.nextLine();
    }

    public int nextInt() {
         return Integer.parseInt(in.nextLine());
    }

    public void printBorder(int nLinhas, int largura) {
        final String TL = "╔";
        final String TR = "╗";
        final String BL = "╚";
        final String BR = "╝";
        final String HL = "═";
        final String VL = "║";

        // Linha superior
        System.out.println(TL + HL.repeat(largura) + TR);

        // Linhas internas
        for (int i = 0; i < nLinhas; i++) {
            System.out.println(VL + " ".repeat(largura) + VL);
        }

        // Linha inferior
        System.out.println(BL + HL.repeat(largura) + BR);
    }

    private void printMessageBox(String cor, String mensagem) {
        final String TL = "╔";
        final String TR = "╗";
        final String BL = "╚";
        final String BR = "╝";
        final String HL = "═";
        final String VL = "║";

        int larguraInterna = mensagem.length() + 2; // 1 espaço de cada lado
        System.out.println(TL + HL.repeat(larguraInterna) + TR);
        System.out.println(VL + " " + cor + mensagem + Cores.RESET + " " + VL);
        System.out.println(BL + HL.repeat(larguraInterna) + BR);
    }

    public void clrscr() {
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();
    }

    public void gotoXY(int linha, int coluna) {
        System.out.print("\u001B[" + linha + ";" + coluna + "H");
    }

    public void clearLines(int linhaInicial, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            gotoXY(linhaInicial + i, 1); // vai até o início da linha
            System.out.print("\u001B[2K"); // apaga linha inteira
        }
    }

    public void clearRegion(int linhaInicio, int colInicio, int linhas, int colunas) {
        for (int i = 0; i < linhas; i++) {
            gotoXY(linhaInicio + i, colInicio);
            System.out.print(" ".repeat(colunas));
        }
    }

    public String inputLine(int tamanho) {
        System.out.print(Cores.AZUL_BG + " ".repeat(tamanho) + Cores.RESET);
        System.out.print("\u001B[1D".repeat(tamanho));
        String input = in.nextLine();
        return input;
    }

    public void print(String cor, String texto) {
        System.out.print(cor + texto + Cores.RESET);
    }

    public void pause() {
        this.nextLine();
    }

    public void printErrorMessage(String mensagem) {
        this.printMessageBox(Cores.VERMELHO, mensagem);
    }

    public void printSuccessMessage(String mensagem) {
        this.printMessageBox(Cores.VERDE, mensagem);
    }

    public void showCursor() {
        System.out.print(Cores.SHOW_CURSOR);
    }

    public void clearCursor() {
        System.out.print(Cores.CLEAR_CURSOR);
    }

}

