
package br.edu.ifpb.tsi.poo.ui;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String titulo;
    private List<String> itens;
    private String prompt;
    private Console console;
    public Menu exiba() {
        
        // Caracteres da moldura
        final char TL = '╔'; // Top Left
        final char TR = '╗'; // Top Right
        final char BL = '╚'; // Bottom Left
        final char BR = '╝'; // Bottom Right
        final char HL = '═'; // Horizontal Line
        final char VL = '║'; // Vertical Line
        // Descobrir largura máxima necessária (título ou item)
        int maxConteudo = titulo.length();
        for (int i = 0; i < itens.size(); i++) {
            int tamanhoItem = (i + 1 + ") " + itens.get(i)).length();
            if (tamanhoItem > maxConteudo) {
                maxConteudo = tamanhoItem;
            }
        }
        // Largura total considerando moldura + espaços
        int largura = maxConteudo + 2;
        // Construir parte superior da moldura
        System.out.print(TL);
        for (int i = 0; i < largura; i++)
            System.out.print(HL);
        System.out.println(TR);
        // Imprimir o título centralizado
        int espacos = largura - titulo.length();
        int esquerda = espacos / 2;
        int direita = espacos - esquerda;
        System.out.print(VL);
        for (int i = 0; i < esquerda; i++)
            System.out.print(" ");
        System.out.print(titulo);
        for (int i = 0; i < direita; i++)
            System.out.print(" ");
        System.out.println(VL);
        // Linha separadora
        System.out.print(VL);
        for (int i = 0; i < largura; i++)
            System.out.print(HL);
        System.out.println(VL);
        // Itens numerados
        for (int i = 0; i < itens.size(); i++) {
            String itemColorido = Cores.AZUL + itens.get(i) + Cores.RESET;
            String linha = (i + 1) + ") " + itemColorido;
            System.out.print(VL);
            System.out.print(linha);
            // Completar espaços até o fim
            int comprimentoVisivel = (i + 1 + ") ").length() + itens.get(i).length();
            for (int j = comprimentoVisivel; j < largura; j++) {
                System.out.print(" ");
            }
            System.out.println(VL);
        }
        // Parte inferior da moldura
        System.out.print(BL);
        for (int i = 0; i < largura; i++)
            System.out.print(HL);
        System.out.println(BR);
        return this;
    }
    public int leiaOpcao() {
        System.out.print(Cores.AZUL + this.prompt + Cores.RESET);
        return Integer.parseInt(console.nextLine());
    }
    public int getAltura() {
        return 4 + itens.size();
    }
    public int getLargura() {
        int maxConteudo = titulo.length();
        for (int i = 0; i < itens.size(); i++) {
            // Cálculo sem considerar os códigos ANSI para cores etc
            int visibleLen = (i + 1 + ") ").length() + itens.get(i).length();
            if (visibleLen > maxConteudo) {
                maxConteudo = visibleLen;
            }
        }
        // largura total = conteúdo + 4 (2 espaços internos + 2 bordas)
        return maxConteudo + 4;
    }
    public static void main(String[] args) {
        Console console = new Console();
        Menu menu = new Menu(
            "Menu", 
            List.of("Cadastrar aluno", "Cadastrar professor", "Cadastrar disciplina", "Cadastrar estágio"
            ), 
            "Escolha uma opção: ", 
            console);
        menu.exiba();
        int op = menu.leiaOpcao();
        System.out.println("Opção: " + op);
    }
}