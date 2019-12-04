package TrabalhoPratico;

import java.util.Scanner;

public class Menu {
    static Scanner input = new Scanner(System.in);

    public static void main (String []args) throws InterruptedException{
    	Locadora locadora = new Locadora();
        boolean saida = true;
        MenuLocacao menuLoca = new MenuLocacao();
        MenuCarro menuCarros = new MenuCarro();
        while (saida) {
            System.out.println("**************************************************");
            System.out.println("                      MENU                        ");
            System.out.println("**************************************************");
            System.out.println("	     Digite o que deseja fazer");
            System.out.println(" 0 - Sair do Programa");
            System.out.println(" 1 - Entrar no Menu de Locacoes");
            System.out.println(" 2 - Entrar no Menu de Carros");
            System.out.print("Opcao: ");
            int opcao = input.nextInt();
            System.out.println("**************************************************");
            switch(opcao) {
                case 0:
                    saida = false;
                    break;
                case 1:
                    menuLoca.Menu(locadora);
                    break;
                case 2:
                    menuCarros.Menu(locadora);
                    break;
                default:
                    System.out.println("Erro, codigo digitado invalido!");
                    break;

            }
        }
    }
}