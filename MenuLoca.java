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
    	setSize(600,600);
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
    public void VisualizaLocacoes(JPanel panel,Locadora locadora){
        if (locadora.locacoes.isEmpty()){
            remove(panel);
            panel = new JPanel();   	
            panel.add(new JLabel("Ainda nao existem locacoes feitas na locadora!",SwingConstants.CENTER));
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
            panel.setLayout(new GridLayout(locadora.locacoes.size()+3,1));    	
            panel.add(new JLabel("Marque qual locacao voce deseja verificar:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.locacoes.size()) {
                botao.add(new JRadioButton (locadora.locacoes.get(i).toString()+"\n",false));
                botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(botao.get(i));
                grupo.add(botao.get(i));
                i++;
            }
            add(panel);
            JButton v = new JButton("Consultar");
            v.addActionListener((ActionEvent e) -> {
                i = 0;
                while (i < locadora.locacoes.size()) {
                    if(botao.get(i).isSelected()){
                        TabelaCarros(locadora,i);
                    }
                    i++;
                }
            });
            JButton v1 = new JButton("Voltar");
            v1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    dispose();
                }
             });
            panel.add(v);
            panel.add(v1);
            revalidate();
        }
    }
    public void TabelaCarros(Locadora locadora, int id){
        System.out.print(id);
        JFrame f = new JFrame("Carros");
        f.setLayout(new GridLayout(3,1));  
        int j = 0;
        f.add(new JLabel("\nDono da locacao: "+locadora.locacoes.get(id).getCliente()+".\nReserva feita em: "+locadora.locacoes.get(id).getData()),SwingConstants.CENTER);
        String data[][]= new String[locadora.locacoes.get(id).getTam()][5];
        i =0;
        while(i < locadora.locacoes.get(id).getTam()){
            data[i][0] = locadora.locacoes.get(id).getCarro(i).getMarca();
            data[i][2] = locadora.locacoes.get(id).getCarro(i).getModelo();
            data[i][1] = locadora.locacoes.get(id).getCarro(i).getPlaca();
            data[i][3] = locadora.locacoes.get(id).getCarro(i).getCor();
            data[i][4] = Integer.toString(locadora.locacoes.get(id).getCarro(i).getAno());
            i++;
        }
        JButton v = new JButton("Voltar");
            v.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    f.dispose();
                }
        });
        String column[]={"Marca","Placa","Cor","Modelo","Ano"};
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        f.setLocationRelativeTo(null);
        f.add(sp);      
        f.add(v);
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
            add(panel);
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
            add(panel);
            add(v);
            revalidate();
        }else{
            remove(panel);
            JPanel panel1 = new JPanel();
            grupo = new ButtonGroup();
            ArrayList<JRadioButton> botao = new ArrayList<>();
            panel1.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.size()+0,1));    	
            panel1.add(new JLabel("Marque qual locacao voce deseja devolver carros:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.locacoes.size()) {
		botao.add(new JRadioButton (locadora.locacoes.get(i).toString()+"\n",false));
		botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(botao.get(i));
		grupo.add(botao.get(i));
		i++;
            }
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
            panel1.add(v);
            add(panel1);
            revalidate();
        }
}
    public void Carros(JPanel panel1, Locadora locadora, int id){
        remove(panel1);
        JPanel panel2 = new JPanel();
        grupo = new ButtonGroup();
        ArrayList<JRadioButton> botao = new ArrayList<>();
        panel2.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.get(i).getTam(),1));    	
        panel2.add(new JLabel("Marque qual carro voce deseja devolver:",SwingConstants.CENTER));
        i = 0;
        while (i < locadora.locacoes.get(id).getTam()){
            botao.add(new JRadioButton (locadora.locacoes.get(id).getCarro(i).toString()+"\n",false));
            botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(botao.get(i));
            grupo.add(botao.get(i));
            i++;
        }
        add(panel2);
        JButton v = new JButton("Devolver");
        v.addActionListener((ActionEvent e) -> {
            int j = 0;
            while (j < locadora.locacoes.get(id).getTam()) {
                if(botao.get(j).isSelected()){
                    locadora.DevolverCarro(id,j);
                    if (locadora.locacoes.get(id).getTam() == 0){
                        locadora.locacoes.remove(id);
                        JOptionPane.showMessageDialog(this,"Carro devolvido com sucesso e Locacao apagada!");
                    }else{
                        JOptionPane.showMessageDialog(this,"Carro devolvido com sucesso!");
                    }
                    dispose();
                    break;
                }
                j++;
            }
        });
        
        add(v);
        revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

    }
}
