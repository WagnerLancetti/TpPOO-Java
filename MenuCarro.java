package TrabalhoPratico;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.Scanner;
import java.awt.Container;
import javax.swing.text.MaskFormatter;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class MenuCarro extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    Scanner input = new Scanner(System.in);
    Random gerador = new Random(System.currentTimeMillis());
    int i=0;
    private String verificacao = null;
    private int entrada =0;
  //  ArrayList<Carro> especificidade;
    
    void MenuCarro(Locadora locadora) throws InterruptedException{
    	
    	String[] escolhas = {"Voltar ao Menu Principal", "Cadastrar Carro",
        		"Remover Carro", "Buscar Carro","Exibir Tabela"};
    	setTitle("Menu Carro");
    	setSize(500,300);
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
        			//exibirTabela(panel,locadora);
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
    	JButton voltar = new JButton("voltar");
    	cad.addActionListener((ActionEvent e) -> {
        int i = 0;
        boolean continua;
        	if(cad.isSelected()){
            	continua = true;
                while (i < locadora.cars.size()){
                    // Verificar se ja foi cadastrado
                    if (locadora.cars.get(i).getPlaca().equals(field2)){
                        continua = false;
                        i = locadora.cars.size() + 1;
                    }
                    i++;
                }
                i = 0;
                while (i < locadora.carsAlugados.size()){
                    if (locadora.carsAlugados.get(i).getPlaca().equals(field2)){
                        continua = false;
                        i = locadora.carsAlugados.size() + 1;
                    }
                    i++;
                }
            }
            Carro car = new Carro();
	    	String marca=null;
            String modelo=null;
            int ano=0;
            String cor=null;
            boolean alugado=false;
            String placa=null;
        	String data = null;
        	String carro = null;
        	int id=0;
        	marca = field1.getText();
        	placa = field2.getText();
        	modelo = field3.getText();
        	ano =  Integer.parseInt(field4.getText());
        	cor = field5.getText();
        	//System.out.println("RESULT marca="+marca+" modelo="+modelo+" ano="+ano+"placa="+placa+"cor="+cor);
        	// locadora.cars.add(car);   
        	boolean Iverifica = true;
        	int num=0;
            while (Iverifica){
                num = gerador.nextInt(1000000);
                i = 0;
                int entrada = 0;
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
            Carro c = new Carro();
            c.setMarca(marca);
            c.setPlaca(placa);
            c.setCor(cor);
            c.setModelo(modelo);
            c.setAno(ano);
            c.setCor(cor);
            c.identificador = num;
            locadora.cars.add(c);
            
   
    });
    voltar.addActionListener((ActionEvent e) -> {
    	dispose();
    });
	add(cad);
	add(voltar);
	setVisible(true);
	revalidate();
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
         panel.setLayout(new GridLayout(locadora.locacoes.size()+locadora.locacoes.get(i).getTam()+0,1));  
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
                	 locadora.cars.remove(i);
                         JOptionPane.showMessageDialog(this,"carro removido com sucesso!");
                         dispose();
                 }
                 i++;
             }
         }); 
         JButton voltar = new JButton("voltar");
         voltar.addActionListener((ActionEvent e) -> {
     		dispose();
     	});
         add(rem);
         add(voltar);
         setVisible(true);
         revalidate();
     }
}



