package locadora;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class MenuLocacao extends JFrame implements ActionListener{
    /* Atributos */
    Scanner input = new Scanner(System.in);
    Random gerador = new Random(System.currentTimeMillis());
    JLabel titulo = new JLabel("Menu Locacao",SwingConstants.CENTER);
    JLabel titulo2 = new JLabel("O que voce deseja fazer?",SwingConstants.CENTER);
    JRadioButton vo = new JRadioButton("Voltar para o Menu Principal",true);
    JRadioButton vi = new JRadioButton("Visualizar todos os carros alugados em uma locacao");
    JRadioButton cr = new JRadioButton("Criar uma nova locacao");
    JRadioButton de = new JRadioButton("Devolver um carro de uma locacao especifica");
    JRadioButton dl = new JRadioButton("Devolver uma locacao completa");
    JButton conf = new JButton("Confirmar");
    ButtonGroup grupo = new ButtonGroup();
    MenuLocacao(){
        setTitle("Menu Locacao");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(8,1));
        setResizable(false);

        titulo.setFont(new Font("Arial",Font.BOLD,30));
        add(titulo);
        add(titulo2);
        
        vo.setHorizontalAlignment(SwingConstants.CENTER);
        vi.setHorizontalAlignment(SwingConstants.CENTER);
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        de.setHorizontalAlignment(SwingConstants.CENTER);
        dl.setHorizontalAlignment(SwingConstants.CENTER);
        add(vo);grupo.add(vo);
        add(vi);grupo.add(vi);
        add(cr);grupo.add(cr);
        add(de);grupo.add(de);
        add(dl);grupo.add(dl);
        
        conf.setPreferredSize(new Dimension(10,10));
        conf.addActionListener(this);
        add(conf);
    }
    
    public void actionPerformed(ActionEvent e){   
        if(vo.isSelected()){    
             
        }    
        if(vi.isSelected()){    
            //chama vi
        }
        if(cr.isSelected()){
            //chama cr
        }
        if(de.isSelected()){
            
        }
        if(dl.isSelected()){
            
        }
    }
    
    public void Menu(Locadora locadora) throws InterruptedException{
    	ArrayList<Carro> carro1 = new ArrayList<>();
    	int i,entrada=0,entrada2 = 0,j;
    	String nome=null;
    	String data=null;
        boolean menuloca = true, Iverifica, Iverifica2;
        while (menuloca){
            System.out.println("\n**************************************************");
            System.out.println("                 MENU LOCACOES                     ");
            System.out.println("**************************************************");
            System.out.println("	     Digite o que deseja fazer");
            System.out.println("0 - Voltar para o Menu Principal");
            System.out.println("1 - Visualizar todos os carros alugados em uma locacao");
            System.out.println("2 - Criar uma nova locacao");
            System.out.println("3 - Devolver um carro de uma locacao especifica");
            System.out.println("4 - Devolver uma locacao completa");
            System.out.print("Opcao: ");
            int opcao = input.nextInt();
            System.out.println("**************************************************");
            switch (opcao) {
                case 0: // SAIR DO MENU DE LOCACAO
                    menuloca = false;
                    break;
                    
                case 1: // LISTAR TODOS OS CARROS QUE EXISTEM EM UMA LOCACAO
                    if (locadora.locacoes.isEmpty()){
                        System.out.println("Nao existem locacoes na locadora.");
                    }else{
                        System.out.println("Temos essas locacoes na locadora: \n");
                        i = 0;
                        while (i < locadora.locacoes.size()){
                            System.out.println(i + " - "+locadora.locacoes.get(i).toString());
                            i++;
                        }
                        Iverifica = true;
                        System.out.println("Qual locacao deseja verificar [Digite o indice]:");
                        while (Iverifica){
                            entrada = input.nextInt();
                            if (entrada >= 0 && entrada < locadora.locacoes.size()){
                                i = 0;
                                System.out.println("\nDono da locacao: "+locadora.locacoes.get(entrada).getCliente()+".\nReserva feita em: "+locadora.locacoes.get(entrada).getData()+".\nCarros que existem nessa locacao: ");
                                while (i < locadora.locacoes.get(entrada).getTam()){
                                    System.out.println(i + " - "+locadora.locacoes.get(entrada).getCarro(i).toString());
                                    i++;
                                }
                                System.out.println();
                                Iverifica = false;
                            }else{
                                System.out.println("Digite um indice valido por favor: ");
                            }
                        }
                        Thread.sleep(1500);
                    }
                    break;
                case 2: // CRIAR UMA NOVA LOCACAO
                    if (locadora.cars.isEmpty()){
                        System.out.println("\nNao existem carros para serem alugados na locadora.");
                    }else{
                        System.out.println("Temos "+locadora.cars.size()+" carro(s) para serem alugados. Quantos carros deseja alugar? ");
                        int qtd = input.nextInt();
                        if (qtd > locadora.cars.size()){
                            System.out.println("Erro! Nao temos todos esses carros na locadora para serem alugados.");
                        }else{
                            System.out.print("\nDigite seu nome: ");
                            nome = input.next();
                            System.out.print("\nDigite a data de aluguel: ");
                            data = input.next();
                            i=0;
                            while(i < qtd) {
                                System.out.println("\nTemos esses carros para serem alugados na locadora: ");
                                j = 0;
                                while (j < (locadora.cars.size())) {
                                    System.out.println(j+ " - "+locadora.ListarCarrosParaAlugar(j).toString());
                                    j++;
                                }
                                Iverifica = true;
                                System.out.print("\nDigite qual o indice do carro que voce deseja alugar: ");
                                while (Iverifica){
                                    entrada = input.nextInt();
                                    if (entrada >= 0 && entrada < locadora.cars.size()){
                                        locadora.cars.get(entrada).setAlugado(true);
                                        carro1.add(locadora.cars.get(entrada));
                                        locadora.carsAlugados.add(locadora.cars.get(entrada));
                                        locadora.cars.remove(entrada);
                                        System.out.println("\nCarro reservado com sucesso!");
                                        Iverifica = false;
                                    }else{
                                        System.out.println("Digite um indice valido por favor: ");
                                    }
                                }
                                i++;
                            }
                        int num = 0;
                        Iverifica = true;
                        while (Iverifica){
                            entrada = 0;
                            i = 0;
                            num = gerador.nextInt(1000000);
                            while (i < locadora.locacoes.size()){
                                if (num == locadora.locacoes.get(i).identificador){
                                    i = locadora.locacoes.size();
                                    entrada++;
                                }
                                i++;
                            }
                            if (entrada == 0){
                                Iverifica = false;
                            }
                        }
                        locadora.CriarLocacao(carro1,nome,data,num); // Adiciona uma nova locacao no array de locacoes
                        carro1.clear();
                        System.out.println("\nLocacao criada com sucesso! O ID dessa locacao e "+num+".\n");
                        }
                        Thread.sleep(1500);
                    }
                    break;
                    
                case 3: // DEVOLVER CARROS DE UMA LOCACAO
                    if (locadora.carsAlugados.isEmpty()){
                        System.out.println("Nao existem carros para serem devolvidos na locadora.");
                    }else{
                        i = 0;
                        System.out.println("\nTemos essas locacoes que podem devolver carros: ");
                        while (i < (locadora.locacoes.size())) {
                            System.out.println(i+" - "+locadora.locacoes.get(i).toString());
                            i++;
                        }
                        System.out.println("Qual dessas voce ira acessar? [Digite o indice]: ");
                        Iverifica = true;
                        while(Iverifica){
                            entrada = input.nextInt();
                            if (entrada >= 0 && entrada < locadora.locacoes.size()) {
                                System.out.println("Quantos carros voce deseja devolver? ");
                                int qtd = input.nextInt();
                            	i=0;
                            	while (i < qtd) {
	                                System.out.println("Temos esses carros na locacao escolhida: ");
	                                j = 0;
	                                while(j < locadora.locacoes.get(entrada).getTam()) {
	                                        System.out.println(j+" - "+locadora.locacoes.get(entrada).getCarro(j).toString());
	                                        j++;
	                                }
	                                System.out.println("\nQual carro voce deseja devolver? [Digite o indice]: ");
	                                Iverifica2 = true;
	                                while (Iverifica2) {
	                                	entrada2 = input.nextInt();
	                                	if (entrada2 >= 0 && entrada2 < locadora.locacoes.get(entrada).getTam()) {
	                                		locadora.DevolverCarro(entrada,entrada2);
	                                		System.out.println("Carro devolvido com sucesso!\n");
	                                		Iverifica2 = false;
	                                	}else{
	                                		System.out.println("Digite um indice valido por favor: ");
	                                	}
	                                }
                                	if (locadora.locacoes.get(entrada).getTam() == 0) {
                                		locadora.locacoes.remove(entrada);
                                		System.out.println("Como nao existem mais carros na locacao ela foi apagada!");
                                		i = qtd + 1;
                                	}
                                	i++;
                            	}
                            	Iverifica=false;
                            }else{
                                System.out.println("Digite um indice valido por favor: ");
                            }
                        }
                    }
                    Thread.sleep(1500);
                    break;
                    
                case 4: // DEVOLVER TODA UMA LOCACAO
                	if (locadora.locacoes.isEmpty()) {
                		System.out.println("Nao existem locacoes para serem devolvidas.");
                	}else{
                		System.out.println("Temos essas locacoes na locadora: ");
                		i = 0;
                		while (i < locadora.locacoes.size()) {
                			System.out.println(i + " - "+locadora.locacoes.get(i).toString());
                			i ++;
                		}
                		System.out.println("Qual locacao deseja devolver? [Digite o indice]: ");
                		Iverifica = true;
                		while (Iverifica) {
                			entrada = input.nextInt();
                			if (entrada >= 0 && entrada < locadora.locacoes.size()) {
                				locadora.DevolverLocacao(entrada);
                				System.out.println("\nLocacao devolvida com sucesso! Agradecemos a preferencia!");
                				Iverifica = false;
                			}else{
                				System.out.println("Digite um indice valido por favor: ");
                			}
                		}
                	}
                	break;
                default: // Comando invalido
                    System.out.println("Erro, codigo digitado invalido!");
                    break;
            }
        }
    }
}
