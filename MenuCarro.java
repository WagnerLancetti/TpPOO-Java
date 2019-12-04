package TrabalhoPratico;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class MenuCarro {
	/* Atributos */
    Scanner input = new Scanner (System.in);
    Random gerador = new Random(System.currentTimeMillis());

	void Menu(Locadora locadora) throws InterruptedException{
        int entrada,num = 0;
        boolean continua,Iverifica,menucar = true;
        int i;
        while (menucar){
            System.out.println("\n**************************************************");
            System.out.println("                 MENU CARROS                     ");
            System.out.println("**************************************************");
            System.out.println("	     Digite o que deseja fazer");
            System.out.println("0 - Voltar para o Menu Principal");
            System.out.println("1 - Cadastrar Carro");
            System.out.println("2 - Remover Carro");
            System.out.println("3 - Buscar Carro");
            System.out.print("Opcao: ");
            int opcao = input.nextInt();
            System.out.println("**************************************************\n");
            switch (opcao){
                case 0:
                    menucar = false;
                    break;
                    
                case 1: // CADASTRAR CARRO
                    System.out.println("\nDigite as informacoes do novo carro");
                    System.out.printf("Placa: ");
                    String Placa = input.next();
                    i = 0;
                    continua = true;
                    while (i < locadora.cars.size()){
                        // Verificar se ja foi cadastrado
                        if (locadora.cars.get(i).getPlaca().equals(Placa)){
                            continua = false;
                            i = locadora.cars.size() + 1;
                        }
                        i++;
                    }
                    i = 0;
                    while (i < locadora.carsAlugados.size()){
                        if (locadora.carsAlugados.get(i).getPlaca().equals(Placa)){
                            continua = false;
                            i = locadora.carsAlugados.size() + 1;
                        }
                        i++;
                    }
                    if (continua){
                        System.out.print("Marca: ");
                        String Marca= input.next();
                        System.out.print("Cor: ");
                        String Cor = input.next();
                        System.out.print("Modelo: ");
                        String Modelo = input.next();
                        System.out.print("Ano: ");
                        int Ano = input.nextInt();
                        Iverifica = true;
                        while (Iverifica){
                            num = gerador.nextInt(1000000);
                            i = 0;
                            entrada = 0;
                            while (i < locadora.cars.size()){
                                if (num == locadora.cars.get(i).identificador){
                                    i = locadora.cars.size();
                                    entrada++;
                                }
                                i++;
                            }
                            i = 0;
                            while (i < locadora.carsAlugados.size()){
                                if (num == locadora.carsAlugados.get(i).identificador){
                                    i = locadora.carsAlugados.size();
                                    entrada++;
                                }
                                i++;
                            }
                            if (entrada == 0){
                                Iverifica = false;
                            }
                        }
                        locadora.cars.add(new Carro(Placa,Marca,Cor,Modelo,Ano,num));
                        System.out.print("\nCarro cadastrado com sucesso! O ID desse carro e "+num+".\n");
                    }else{
                        System.out.println("Desculpe, mas o carro já está cadastrado no sistema.");
                    }
                    Thread.sleep(1500);
                    break;
                    
                case 2: // REMOVER CARRO
                    if (locadora.cars.isEmpty()){
                        System.out.println("Nao existem carros que podem ser removidos da locadora.");
                    }else{
                        System.out.println("\nTemos esses carros que podem ser removidos agora: ");
                        i = 0;
                        while (i < (locadora.cars.size())){
                            System.out.println(i+" - "+locadora.ListarCarrosParaAlugar(i));
                            i++;
                        }
                        Iverifica = true;
                        System.out.print("\nDigite o indice do carro que voce deseja remover: ");
                        while (Iverifica){
                            entrada = input.nextInt();
                            if (entrada >= 0 && entrada < locadora.cars.size()){
                            	locadora.cars.remove(entrada);
                                System.out.println("Carro removido com sucesso!");
                                Iverifica = false;
                            }else{
                                System.out.println("Digite um indice valido por favor: ");
                            }
                        }
                        Thread.sleep(1500);
                    }
                    break;
                    
                case 3:
                	if (locadora.cars.isEmpty() && locadora.carsAlugados.isEmpty()) {
                		System.out.println("\nNao existem carros na locadora.\n");
                	}else {
	                    String verificacao;
	                    int verificacao1;
	                    ArrayList<Carro> especificidade = new ArrayList<>();
	                    System.out.println("\nVoce pode buscar os carros pelos seguintes atributos: ");
	                    System.out.println("1 - Marca\n2 - Modelo\n3 - Cor\n4 - Ano\n5 - Placa\n6 - Id do Carro");
	                    System.out.print("Qual sua opcao: ");
	                    entrada = input.nextInt();
	                    switch (entrada){
	                        case 1: // Busca por Marca
	                            System.out.println("Digite a Marca que deseja verificar: ");
	                            verificacao = input.next();
	                            especificidade = locadora.BuscarCarro(entrada,verificacao);
	                            break;
	                            
	                        case 2: // Busca por Modelo
	                            System.out.println("Digite o Modelo que deseja verificar: ");
	                            verificacao = input.next();
	                            especificidade = locadora.BuscarCarro(entrada,verificacao);
	                            break;
	                            
	                        case 3: // Busca por Cor
	                            System.out.println("Digite a Cor que deseja verificar: ");
	                            verificacao = input.next();
	                            especificidade = locadora.BuscarCarro(entrada,verificacao);
	                            break;
	                            
	                        case 4: // Busca por Ano
	                            System.out.println("Digite o Ano que deseja verificar: ");
	                            verificacao1 = input.nextInt();
	                            especificidade = locadora.BuscarCarroInt(entrada,verificacao1);
	                            break;
	                            
	                        case 5: // Busca por Placa
	                            System.out.println("Digite a Placa que deseja verificar: ");
	                            verificacao = input.next();
	                            especificidade = locadora.BuscarCarro(entrada,verificacao);
	                            break;
	                            
	                        case 6: // Busca por ID
	                            System.out.println("Digite o ID que deseja verifica: ");
	                            verificacao1 = input.nextInt();
	                            especificidade = locadora.BuscarCarroInt(entrada, verificacao1);
	                            break;
	                        default:
	                            System.out.println("Erro, codigo digitado invalido!");
	                            break;
	                    }
	                    if (entrada >= 1 || entrada <= 6){
	                        if (especificidade.isEmpty()){
	                            System.out.println("Nao existe nenhum carro com essa especificacao.");
	                        }else{
	                            i = 0;
	                            while (i < especificidade.size()){
	                                System.out.println(i+" - "+especificidade.get(i).toString());
	                                i++;
	                            }
	                        }
	                        especificidade.clear();
	                    }
                	}
                    break;
                    
                default:
                    System.out.println("Erro, codigo digitado invalido!");
                    break;
            }
    
        }
    }
}