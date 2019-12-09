package TrabalhoPratico;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuLocacao extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	/* Atributos */
    Scanner input = new Scanner(System.in);
    Random gerador = new Random(System.currentTimeMillis());
    String[] escolhas = {"Voltar para o Menu Principal", "Visualizar as locacoes existentes", "Criar uma nova locacao", "Devolver um carro de uma locacao especifica", "Devolver uma locacao completa"};
    ButtonGroup grupo = new ButtonGroup();
    int i = 0;
    
    
    public void Menu(Locadora locadora){
    	setTitle("Menu Locacao");
    	setSize(600,300);
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
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Visualizar as locacoes existentes")) {
        			VisualizaLocacoes(panel,locadora);
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Criar uma nova locacao")) {
        			CriarLocacao(panel,locadora);
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver um carro de uma locacao especifica")) {
                                DevolverCarrosLocacao(panel,locadora);
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver uma locacao completa")){
                                DevolverLocacaoCompleta(panel,locadora);
        		}
    		}
    	});
        panel.add(new JLabel("O que voce deseja fazer?\n",SwingConstants.CENTER));
        panel.add(cb);
        panel.add(conf);
        add(panel);
	}
    
    //=============================================================================================================
   public void TabelaCarros(Locadora locadora, int id){
        JFrame f = new JFrame("Carros");
        int j = 0;
        f.add(new Label("\nDono da locacao: "+locadora.locacoes.get(id).getCliente()+".\nReserva feita em: "+locadora.locacoes.get(id).getData()+".\nCarros que existem nessa locacao: "));
        while (j < locadora.locacoes.get(id).getTam()){
            System.out.println(id + " - " + locadora.locacoes.get(id).getCarro(j).toString());
            j++;
        }
        String data[][]= new String[locadora.locacoes.get(id).getTam()][6];
        while(i < locadora.locacoes.get(id).getTam()){
            data[i][0] = locadora.locacoes.get(id).getCarro(i).getMarca();
            data[i][1] = locadora.locacoes.get(id).getCarro(i).getPlaca();
            data[i][2] = locadora.locacoes.get(id).getCarro(i).getCor();
            data[i][3] = locadora.locacoes.get(id).getCarro(i).getModelo();
            data[i][4] = Integer.toString(locadora.locacoes.get(id).getCarro(i).getAno());
            if (locadora.locacoes.get(id).getCarro(i).isAlugado()){
                data[i][5] = "True";
            }else{
                data[i][5] = "False";
            }
            i++;
        }
        String column[]={"Marca","Placa","Cor","Modelo","Ano","Status"};
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        f.setLocationRelativeTo(null);
        f.add(sp);          
        f.setSize(300,400);    
        f.setVisible(true); 

    }
    //=============================================================================================================
  public void CriarLocacao(JPanel panel, Locadora locadora){
        if (locadora.cars.size() > 0){
            remove(panel);
            panel = new JPanel();
            panel.setLayout(new GridLayout(locadora.cars.size()+ 3,2));   
            JTextField nome = new JTextField("Digite o nome de quem vai alugar");
            JTextField data = new JTextField("Digite a data de locacao");
            nome.setBounds(50,150, 200,30);
            data.setBounds(50,150, 200,30);
            nome.setHorizontalAlignment(JTextField.CENTER); // alinha a direita
            data.setHorizontalAlignment(JTextField.CENTER);
            i = 0;
            panel.add(nome);
            panel.add(data);
            ArrayList<JCheckBox> botoes = new ArrayList<>();
            while (i < locadora.cars.size()){
                botoes.add(new JCheckBox(locadora.cars.get(i).toString()));
                botoes.get(i).setBounds(100,200,150,20);
                panel.add(botoes.get(i));
                i++;
            }           
            ArrayList<Carro> carros = new ArrayList<>();
            JButton b = new JButton("Confirmar");
            b.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    i = 0;
                    while (i < locadora.cars.size()){
                        if (botoes.get(i).isSelected()){
                            carros.add(locadora.cars.get(i));
                        }
                        i++;
                    }
                    locadora.CriarLocacao(carros, nome.getText(), data.getText(),gerador.nextInt(9999999));
                    dispose();
                }
            });
            panel.add(b);
            add(panel);
            revalidate();
        }else{
            remove(panel);
            panel = new JPanel();
            panel.add(new Label("Não existem carros para serem alugados na locadora!",SwingConstants.CENTER));
            JButton v = new JButton("Voltar");
            v.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    dispose(); 
                }
            });
            add(v);
            revalidate();
        }
    }
    
    //============================================================================================================= FUNCIONANDO OK
    public void DevolverLocacaoCompleta(JPanel panel, Locadora locadora){
        if (locadora.locacoes.isEmpty()){
            remove(panel);
            panel = new JPanel();   	
            panel.add(new JLabel("Nao existem locacoes para serem devolvidas!",SwingConstants.CENTER));
            add(panel);
            JButton v = new JButton("Voltar");
            v.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    dispose(); 
                }
            });
            add(v);
            revalidate();
        }else{
            remove(panel);
            panel = new JPanel();
            ButtonGroup grupo = new ButtonGroup();
            ArrayList<JRadioButton> botao = new ArrayList<JRadioButton>();
            panel.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.get(i).getTam()+0,1));    	
            panel.add(new JLabel("Marque qual locacao voce deseja devolver:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.locacoes.size()) {
		botao.add(new JRadioButton (locadora.locacoes.get(i).toString()+"\n",false));
		botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(botao.get(i));
		grupo.add(botao.get(i));
		i++;
            }
            add(panel);
            JButton v = new JButton("Devolver");
            v.addActionListener((ActionEvent e) -> {
                i = 0;
                while (i < locadora.locacoes.size()) {
                    if(botao.get(i).isSelected()){
                            locadora.DevolverLocacao(i);
                            JOptionPane.showMessageDialog(this,"Locacao devolvida com sucesso! Agradecemos a preferencia!");
                            dispose();
                    }
                    i++;
                }
            });
            add(v);
            revalidate();
        }
    }
    
    //=====================================================================================================================
    public void DevolverCarrosLocacao(JPanel panel, Locadora locadora){
        if (locadora.locacoes.isEmpty()){
            remove(panel);
            panel = new JPanel();   	
            panel.add(new JLabel("Nao existem locacoes para serem devolvidas!",SwingConstants.CENTER));
            add(panel);
            JButton v = new JButton("Voltar");
            v.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    dispose(); 
                }
            });
            add(v);
            revalidate();
        }else{
            remove(panel);
            JPanel panel1 = new JPanel();
            ButtonGroup grupo = new ButtonGroup();
            ArrayList<JRadioButton> botao = new ArrayList<JRadioButton>();
            panel1.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.get(i).getTam()+0,1));    	
            panel1.add(new JLabel("Marque qual locacao voce deseja devolver carros:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.locacoes.size()) {
		botao.add(new JRadioButton (locadora.locacoes.get(i).toString()+"\n",false));
		botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(botao.get(i));
		grupo.add(botao.get(i));
		i++;
            }
            add(panel1);
            JButton v = new JButton("Confirmar");
            v.addActionListener((ActionEvent e) -> {
                i = 0;
                while (i < locadora.locacoes.size()){
                    if(botao.get(i).isSelected()){
                        Carros(panel1,locadora,i);
                    }
                    i++;
                }
            });
            add(v);
            revalidate();
        }
}
    public void Carros(JPanel panel1, Locadora locadora, int id){
        remove(panel1);
        JPanel panel2 = new JPanel();
        ButtonGroup grupo = new ButtonGroup();
            ArrayList<JRadioButton> botao = new ArrayList<JRadioButton>();
            panel2.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.get(i).getTam()+0,1));    	
            panel2.add(new JLabel("Marque qual carro voce deseja devolver:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.locacoes.get(id).getTam()){
                System.out.println(locadora.locacoes.get(id).getCarro(i).toString());
		botao.add(new JRadioButton (locadora.locacoes.get(id).getCarro(i).toString()+"\n",false));
		botao.get(id).setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(botao.get(id));
		grupo.add(botao.get(id));
		i++;
            }
            add(panel2);
            JButton v = new JButton("Devolver");
            v.addActionListener((ActionEvent e) -> {
                int j = 0;
                while (j < locadora.locacoes.get(id).getTam()) {
                    if(botao.get(j).isSelected()){
                            locadora.DevolverCarro(id,j);
                            JOptionPane.showMessageDialog(this,"Carro devolvido com sucesso!");
                            dispose();
                    }
                    j++;
                }
            });
            add(v);
            revalidate();
    }
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
