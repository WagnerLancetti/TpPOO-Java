package TrabalhoPratico;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class MenuLoca extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	/* Atributos */
    Scanner input = new Scanner(System.in);
    Random gerador = new Random(System.currentTimeMillis());
    String[] escolhas = {"Voltar para o Menu Principal", "Visualizar todos os carros alugados em uma locacao", "Criar uma nova locacao", "Devolver um carro de uma locacao especifica", "Devolver uma locacao completa"};
    ButtonGroup grupo = new ButtonGroup();
    
    
    public void Menu(Locadora locadora){
    	//ME APAGA DPS
    	Carro c = new Carro("Honda","XXX1234","Azul","Sport",2019,198237);
    	Carro b = new Carro("FOrd","ABC1234","Preto","Familia",2015,8576293);
    	Locacao loca = new Locacao("Wagner","18:26",81727313);
    	loca.addCarro(c);
    	loca.addCarro(b);
    	locadora.locacoes.add(loca);
    	
    	
    	
    	
    	
    	setTitle("Menu Locacao");
    	setSize(500,300);
		setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        setLayout(new GridLayout(5,1));
        setVisible(true);
        JPanel panel = new JPanel(); 
        JLabel titulo = new JLabel("Menu Locacao",SwingConstants.CENTER);
        titulo.setFont(new Font("Arial",Font.BOLD,30));
        add(titulo);
        
        
        final JComboBox<String> cb = new JComboBox<String>(escolhas);
        cb.setBounds(200, 50,90,20);
        cb.setMaximumSize(null);
        cb.addActionListener(this);
        cb.setVisible(true);
        
        JButton conf = new JButton("Confirmar");
        add(cb);
        conf.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (cb.getItemAt(cb.getSelectedIndex()).equals("Voltar para o Menu Principal")) {
        			dispose();
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Visualizar todos os carros alugados em uma locacao")) {
        			VisualizaLocacoes(panel,locadora);
        			// Chama uma funcao para mostrar os carros
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Criar uma nova locacao")) {
        			// Funcao para criar locacao
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver um carro de uma locacao especifica")) {
        			// Funcao para devolver carro
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver uma locacao completa")){
        			//Funcao para devolver locacao
        		}
    		}
    	});
        panel.add(new JLabel("O que voce deseja fazer?\n",SwingConstants.CENTER));
        panel.add(cb);
        panel.add(conf);
        add(panel);
	}
    
       
    public void VisualizaLocacoes(JPanel panel,Locadora locadora) {
    	remove(panel);
    	panel = new JPanel();
    	panel.add(new JLabel("Marque qual locacao voce deseja verificar\n",SwingConstants.CENTER));
    	int i = 0;
    	ButtonGroup grupo = new ButtonGroup();
    	ArrayList<JRadioButton> botao = new ArrayList<JRadioButton>();
    	while (i < locadora.locacoes.size()) {
			botao.add(new JRadioButton (locadora.locacoes.toString()+"\n",false));
			botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			add(botao.get(i));
			grupo.add(botao.get(i));
			i++;
    	}    	
    	add(panel);
    	revalidate();
    	
    	
    }
//    public void Menu(Locadora locadora) throws InterruptedException{
//    	ArrayList<Carro> carro1 = new ArrayList<>();
//    	int i,entrada=0,entrada2 = 0,j;
//    	String nome=null;
//    	String data=null;
//        boolean menuloca = true, Iverifica, Iverifica2;
//        while (menuloca){
//            System.out.println("\n**************************************************");
//            System.out.println("                 MENU LOCACOES                     ");
//            System.out.println("**************************************************");
//            System.out.println("	     Digite o que deseja fazer");
//            System.out.println("0 - Voltar para o Menu Principal");
//            System.out.println("1 - Visualizar todos os carros alugados em uma locacao");
//            System.out.println("2 - Criar uma nova locacao");
//            System.out.println("3 - Devolver um carro de uma locacao especifica");
//            System.out.println("4 - Devolver uma locacao completa");
//            System.out.print("Opcao: ");
//            int opcao = in)put.nextInt();
//            System.out.println("**************************************************");
//            switch (opcao) {
//                case 0: // SAIR DO MENU DE LOCACAO
//                    menuloca = false;
//                    break;
//                    
//                case 1: // LISTAR TODOS OS CARROS QUE EXISTEM EM UMA LOCACAO
//                    if (locadora.locacoes.isEmpty()){
//                        System.out.println("Nao existem locacoes na locadora.");
//                    }else{
//                        System.out.println("Temos essas locacoes na locadora: \n");
//                        i = 0;
//                        while (i < locadora.locacoes.size()){
//                            System.out.println(i + " - "+locadora.locacoes.get(i).toString());
//                            i++;
//                        }
//                        Iverifica = true;
//                        System.out.println("Qual locacao deseja verificar [Digite o indice]:");
//                        while (Iverifica){
//                            entrada = input.nextInt();
//                            if (entrada >= 0 && entrada < locadora.locacoes.size()){
//                                i = 0;
//                                System.out.println("\nDono da locacao: "+locadora.locacoes.get(entrada).getCliente()+".\nReserva feita em: "+locadora.locacoes.get(entrada).getData()+".\nCarros que existem nessa locacao: ");
//                                while (i < locadora.locacoes.get(entrada).getTam()){
//                                    System.out.println(i + " - "+locadora.locacoes.get(entrada).getCarro(i).toString());
//                                    i++;
//                                }
//                                System.out.println();
//                                Iverifica = false;
//                            }else{
//                                System.out.println("Digite um indice valido por favor: ");
//                            }
//                        }
//                        Thread.sleep(1500);
//                    }
//                    break;
//                case 2: // CRIAR UMA NOVA LOCACAO
//                    if (locadora.cars.isEmpty()){
//                        System.out.println("\nNao existem carros para serem alugados na locadora.");
//                    }else{
//                        System.out.println("Temos "+locadora.cars.size()+" carro(s) para serem alugados. Quantos carros deseja alugar? ");
//                        int qtd = input.nextInt();
//                        if (qtd > locadora.cars.size()){
//                            System.out.println("Erro! Nao temos todos esses carros na locadora para serem alugados.");
//                        }else{
//                            System.out.print("\nDigite seu nome: ");
//                            nome = input.next();
//                            System.out.print("\nDigite a data de aluguel: ");
//                            data = input.next();
//                            i=0;
//                            while(i < qtd) {
//                                System.out.println("\nTemos esses carros para serem alugados na locadora: ");
//                                j = 0;
//                                while (j < (locadora.cars.size())) {
//                                    System.out.println(j+ " - "+locadora.ListarCarrosParaAlugar(j).toString());
//                                    j++;
//                                }
//                                Iverifica = true;
//                                System.out.print("\nDigite qual o indice do carro que voce deseja alugar: ");
//                                while (Iverifica){
//                                    entrada = input.nextInt();
//                                    if (entrada >= 0 && entrada < locadora.cars.size()){
//                                        locadora.cars.get(entrada).setAlugado(true);
//                                        carro1.add(locadora.cars.get(entrada));
//                                        locadora.carsAlugados.add(locadora.cars.get(entrada));
//                                        locadora.cars.remove(entrada);
//                                        System.out.println("\nCarro reservado com sucesso!");
//                                        Iverifica = false;
//                                    }else{
//                                        System.out.println("Digite um indice valido por favor: ");
//                                    }
//                                }
//                                i++;
//                            }
//                        int num = 0;
//                        Iverifica = true;
//                        while (Iverifica){
//                            entrada = 0;
//                            i = 0;
//                            num = gerador.nextInt(1000000);
//                            while (i < locadora.locacoes.size()){
//                                if (num == locadora.locacoes.get(i).identificador){
//                                    i = locadora.locacoes.size();
//                                    entrada++;
//                                }
//                                i++;
//                            }
//                            if (entrada == 0){
//                                Iverifica = false;
//                            }
//                        }
//                        locadora.CriarLocacao(carro1,nome,data,num); // Adiciona uma nova locacao no array de locacoes
//                        carro1.clear();
//                        System.out.println("\nLocacao criada com sucesso! O ID dessa locacao e "+num+".\n");
//                        }
//                        Thread.sleep(1500);
//                    }
//                    break;
//                    
//                case 3: // DEVOLVER CARROS DE UMA LOCACAO
//                    if (locadora.carsAlugados.isEmpty()){
//                        System.out.println("Nao existem carros para serem devolvidos na locadora.");
//                    }else{
//                        i = 0;
//                        System.out.println("\nTemos essas locacoes que podem devolver carros: ");
//                        while (i < (locadora.locacoes.size())) {
//                            System.out.println(i+" - "+locadora.locacoes.get(i).toString());
//                            i++;
//                        }
//                        System.out.println("Qual dessas voce ira acessar? [Digite o indice]: ");
//                        Iverifica = true;
//                        while(Iverifica){
//                            entrada = input.nextInt();
//                            if (entrada >= 0 && entrada < locadora.locacoes.size()) {
//                                System.out.println("Quantos carros voce deseja devolver? ");
//                                int qtd = input.nextInt();
//                            	i=0;
//                            	while (i < qtd) {
//	                                System.out.println("Temos esses carros na locacao escolhida: ");
//	                                j = 0;
//	                                while(j < locadora.locacoes.get(entrada).getTam()) {
//	                                        System.out.println(j+" - "+locadora.locacoes.get(entrada).getCarro(j).toString());
//	                                        j++;
//	                                }
//	                                System.out.println("\nQual carro voce deseja devolver? [Digite o indice]: ");
//	                                Iverifica2 = true;
//	                                while (Iverifica2) {
//	                                	entrada2 = input.nextInt();
//	                                	if (entrada2 >= 0 && entrada2 < locadora.locacoes.get(entrada).getTam()) {
//	                                		locadora.DevolverCarro(entrada,entrada2);
//	                                		System.out.println("Carro devolvido com sucesso!\n");
//	                                		Iverifica2 = false;
//	                                	}else{
//	                                		System.out.println("Digite um indice valido por favor: ");
//	                                	}
//	                                }
//                                	if (locadora.locacoes.get(entrada).getTam() == 0) {
//                                		locadora.locacoes.remove(entrada);
//                                		System.out.println("Como nao existem mais carros na locacao ela foi apagada!");
//                                		i = qtd + 1;
//                                	}
//                                	i++;
//                            	}
//                            	Iverifica=false;
//                            }else{
//                                System.out.println("Digite um indice valido por favor: ");
//                            }
//                        }
//                    }
//                    Thread.sleep(1500);
//                    break;
//                    
//                case 4: // DEVOLVER TODA UMA LOCACAO
//                	if (locadora.locacoes.isEmpty()) {
//                		System.out.println("Nao existem locacoes para serem devolvidas.");
//                	}else{
//                		System.out.println("Temos essas locacoes na locadora: ");
//                		i = 0;
//                		while (i < locadora.locacoes.size()) {
//                			System.out.println(i + " - "+locadora.locacoes.get(i).toString());
//                			i ++;
//                		}
//                		System.out.println("Qual locacao deseja devolver? [Digite o indice]: ");
//                		Iverifica = true;
//                		while (Iverifica) {
//                			entrada = input.nextInt();
//                			if (entrada >= 0 && entrada < locadora.locacoes.size()) {
//                				locadora.DevolverLocacao(entrada);
//                				System.out.println("\nLocacao devolvida com sucesso! Agradecemos a preferencia!");
//                				Iverifica = false;
//                			}else{
//                				System.out.println("Digite um indice valido por favor: ");
//                			}
//                		}
//                	}
//                	break;
//                default: // Comando invalido
//                    System.out.println("Erro, codigo digitado invalido!");
//                    break;
//            }
//        }
//    }
//


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

