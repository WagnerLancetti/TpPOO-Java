package TrabalhoPratico;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuCarro extends JFrame implements ActionListener{
	
    private static final long serialVersionUID = 1L;
    Random gerador = new Random(System.currentTimeMillis());
    int i=0;
    private String verificacao = null;
    private int entrada =0;
  //  ArrayList<Carro> especificidade;
    
    void MenuCarro(Locadora locadora){
    	String[] escolhas = {"Voltar ao Menu Principal", "Cadastrar Carro","Remover Carro", "Buscar Carro","Exibir Tabela"};
    	setTitle("Menu Carro");
    	setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        setLayout(new GridLayout(10,1));
        setVisible(true);
        JPanel panel = new JPanel();
        JLabel titulo = new JLabel("Menu Carro",SwingConstants.CENTER);
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
                if (cb.getItemAt(cb.getSelectedIndex()).equals("Voltar ao Menu Principal")) {
                        dispose();
                }else if (cb.getItemAt(cb.getSelectedIndex()).equals("Cadastrar Carro")) {
                        CadastraCarro(panel,locadora);
                }else if (cb.getItemAt(cb.getSelectedIndex()).equals("Remover Carro")) {
                        removeCarro(panel,locadora);
                }else if (cb.getItemAt(cb.getSelectedIndex()).equals("Buscar Carro")) {
                        //buscarCarro(panel,locadora);
                }else if (cb.getItemAt(cb.getSelectedIndex()).equals("Exibir Tabela")) {
                        TabelaCarros(locadora);
                }
            }
    	});
        panel.add(cb);
        panel.add(conf);
        add(panel);
    }
   

    void CadastraCarro(JPanel panel,Locadora locadora){
    	remove(panel);
    	JTextField field1, field2, field3,field4,field5;
    	panel = new JPanel();
    	JLabel label1, label2, label3,label4,label5 = null;
    	JFrame jf;
    	jf = new JFrame("Cadastrar Carro");
    	jf.setSize(400,150);
    	
    	label1= new JLabel("Marca:");
    	label2= new JLabel("Placa:");
    	label3= new JLabel("Modelo:");
    	label4= new JLabel("Ano:");
    	label5= new JLabel("Cor:");
    	
    	label1.setAlignmentX(LEFT_ALIGNMENT);
    	label2.setAlignmentX(LEFT_ALIGNMENT);
    	label3.setAlignmentX(LEFT_ALIGNMENT);
    	label4.setAlignmentX(LEFT_ALIGNMENT);
    	label5.setAlignmentX(LEFT_ALIGNMENT);
    	
    	field1 = new JTextField(20);
    	field2 = new JTextField(20); 
    	field3 = new JTextField(20);
    	field4 = new JTextField(20);
    	field5 = new JTextField(20);
    	
    	add(new JLabel("Digite de informacoes do novo carro.\n",SwingConstants.CENTER));
    	add(label1);
    	add(field1);
    	add(label2);
    	add(field2);
    	add(label3);
    	add(field3);
    	add(label4);
    	add(field4);
    	add(label5);
    	add(field5);
    	JButton cad = new JButton("Cadastrar");
    	JButton voltar = new JButton("Voltar");
    	cad.addActionListener((ActionEvent e) -> {
            boolean continua = true;
            int i = 0;
            while (i < locadora.cars.size()){
                // Verificar se ja foi cadastrado
                if (locadora.cars.get(i).getPlaca().equals(field2.getText())){
                    continua = false;
                    i = locadora.cars.size() + 1;
                }
                i++;
            }
            i = 0;
            while (i < locadora.carsAlugados.size()){
                if (locadora.carsAlugados.get(i).getPlaca().equals(field2.getText())){
                    continua = false;
                    i = locadora.carsAlugados.size() + 1;
                }
                i++;
            }
            if (continua){
                Carro c = new Carro(field1.getText(),field2.getText(),field5.getText(),field3.getText(),Integer.parseInt(field4.getText()),gerador.nextInt(1000000));
                locadora.cars.add(c);
                JOptionPane.showMessageDialog(this,"Carro Cadastrado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(this,"Erro! Essa Placa já existe no nosso sistema!");
            }
        });
        voltar.addActionListener((ActionEvent e) -> {
            dispose();
        });
	add(cad);
	add(voltar);
	setVisible(true);
	revalidate();
    }
   	
    void removeCarro(JPanel panel,Locadora locadora){
        if (locadora.cars.isEmpty()){
            remove(panel);
            panel = new JPanel();   	
            panel.add(new JLabel("Nao existem carros para serem removidos!",SwingConstants.CENTER));
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
            panel.setLayout(new GridLayout(locadora.cars.size()+1,1));  
            panel.add(new JLabel("Marque qual carro deseja remover:",SwingConstants.CENTER));
            i = 0;
            while (i < locadora.cars.size()) {
                botao.add(new JRadioButton (locadora.ListarCarrosParaAlugar(i)+"\n",false));
                botao.get(i).setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(botao.get(i));
                grupo.add(botao.get(i));
                i++;
            }
            add(panel);
            JButton rem = new JButton("Remover");
            rem.addActionListener((ActionEvent e) -> {
                i = 0;
                while (i < locadora.locacoes.size()) {
                    if(botao.get(i).isSelected()){
                        break;
                    }
                    i++;
                }
                locadora.cars.remove(i);
                JOptionPane.showMessageDialog(this,"carro removido com sucesso!");
                dispose();
            }); 
            JButton voltar = new JButton("Voltar");
            voltar.addActionListener((ActionEvent e) -> {
                dispose();
            });
            add(rem);
            add(voltar);
            setVisible(true);
            revalidate();
        }
    }
  	public void buscarCarro(JPanel panel, Locadora locadora) {
	    remove(panel);
	    panel = new JPanel();   	
		  JLabel title = new JLabel("Selecione por qual opcao deseja buscar:");
	    title.setAlignmentX(CENTER_ALIGNMENT);
	    title.setAlignmentY(TOP_ALIGNMENT);
	    panel.add(title);
	    add(panel);
	    JRadioButton marca = new JRadioButton("Marca");
	    marca.setBounds(119, 58, 109, 23);
	    marca.setAlignmentY(CENTER_ALIGNMENT);
	    JRadioButton modelo = new JRadioButton("Modelo");
	    modelo.setBounds(100,50,100,30); 
	    modelo.setAlignmentY(CENTER_ALIGNMENT);
	    JRadioButton ano = new JRadioButton("Ano");
	    ano.setBounds(100,50,100,30); 
	    JRadioButton placa = new JRadioButton("Placa");
	    placa.setBounds(100,50,100,30); 
	    JRadioButton cor = new JRadioButton("Cor");
	    cor.setBounds(100,50,100,30); 
	    JRadioButton id = new JRadioButton("ID");
	    id.setBounds(100,50,100,30); 
	    panel.add(marca);
	    panel.add(modelo);
	    panel.add(ano);
	    panel.add(placa);
	    panel.add(cor);
	    panel.add(id);
	    JButton conf = new JButton("Confirmar");
	    add(conf);
	    setVisible(true);
	    int entrada=0;
	    conf.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (marca.isSelected()) {
			    JLabel title = new JLabel("Digite a marca que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(1,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				}

			   }

		     });

		}

			if (modelo.isSelected()) {
			    JLabel title = new JLabel("Digite o Modelo que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(2,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				}

				}

			 });


			}

			if (cor.isSelected()) {
			    JLabel title = new JLabel("Digite a cor que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(3,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				 }

		            }

		       });

		}

			if (ano.isSelected()) {
			    JLabel title = new JLabel("Digite o Ano que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(4,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				}

				}

			 });


			}

			if (placa.isSelected()) {
			    JLabel title = new JLabel("Digite a Placa que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(5,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				  }

			      }

			 });

		}

			if (id.isSelected()) {
			    JLabel title = new JLabel("Digite o iD que deseja buscar:");
			    add(title);
			    JTextField field = new JTextField(10);
			    add(field);
			    verificacao = field.getText();
			    JButton buscar = new JButton("Buscar");
			    add(buscar);
			    setVisible(true);
			    buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
			    if(buscar.isSelected()) {
				ArrayList<Carro> especificidade = locadora.BuscarCarro(6,verificacao);
				int idn = 0;
				    while (idn < especificidade.size()){
					JLabel label = new JLabel(especificidade.get(idn).toString());
					idn++;
					add(label);
					setVisible(true);
				    }
				  }
			       }
			    });
			}
		  }
	  });

}
	
	
	
    public void TabelaCarros(Locadora locadora){
        JFrame f = new JFrame("Carros");
        f.setLayout(new GridLayout(3,1));  
        int j;
        f.add(new JLabel("Carros que existem na Locadora ",SwingConstants.CENTER));
//        public Carro(String marca,String placa,String cor,String modelo,int ano,int num) 
        String data[][] = new String[locadora.cars.size() + locadora.carsAlugados.size()][6];
        int k = 0;
        while(k < locadora.cars.size()){
            data[k][0] = locadora.cars.get(k).getMarca();
            data[k][2] = locadora.cars.get(k).getModelo();
            data[k][1] = locadora.cars.get(k).getPlaca();
            data[k][3] = locadora.cars.get(k).getCor();
            data[k][4] = Integer.toString(locadora.cars.get(k).getAno());
            data[k][5] = Integer.toString(locadora.cars.get(k).identificador);
            k++;
        }
        j = 0;
        while (j < locadora.carsAlugados.size()){
            data[k][0] = locadora.carsAlugados.get(j).getMarca();
            data[k][2] = locadora.carsAlugados.get(j).getModelo();
            data[k][1] = locadora.carsAlugados.get(j).getPlaca();
            data[k][3] = locadora.carsAlugados.get(j).getCor();
            data[k][4] = Integer.toString(locadora.carsAlugados.get(j).getAno());
            data[k][5] = Integer.toString(locadora.carsAlugados.get(j).identificador);
            k++;
            j++;
        }
        JButton v = new JButton("Voltar");
            v.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    f.dispose();
                }
        });
        String column[]={"Marca","Placa","Cor","Modelo","Ano","ID"};
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        f.setLocationRelativeTo(null);
        f.add(sp);      
        f.add(v);
        f.setSize(300,400);    
        f.setVisible(true); 

    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

    }
}



