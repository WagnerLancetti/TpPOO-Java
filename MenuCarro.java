package TrabalhoPratico;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.awt.Container;

public class MenuCarro extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
    Scanner input = new Scanner(System.in);
    Random gerador = new Random(System.currentTimeMillis());
    String[] escolhas = {"Voltar ao Menu Principal", "Cadastrar Carro",
    		"Remover Carro", "Buscar Carro"};
    ButtonGroup grupo = new ButtonGroup();
    MenuCarro(){
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
        
        add(new JLabel("O que voce deseja fazer?",SwingConstants.CENTER));
        
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
        			// Sair do programa
        			dispose();
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Cadastrar Carro")) {
        			CadastraCarro(panel);
        			
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Remover Carro")) {
        			System.out.println("Funciona aqui tambem");
        	
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Buscar Carro")) {
        			
        			System.out.println("Teste");
        		}
        		
        		
    		}
    	});
        add(conf);
	}
    
    void CadastraCarro(JPanel panel){
    	remove(panel);
    	JFrame f = new JFrame();
    	JPanel pane=(JPanel) getContentPane();
    	panel = new JPanel();
    	panel.add(new JLabel("Digite de informacoes do novo carro\n",SwingConstants.CENTER));
    	//f.add(panel);
    	JTextField campo = new JTextField(10);
    	campo.setToolTipText("Marca");
    	//campo.setToolTipText("Marca");
    	campo.setFont(new Font("ARIAL", Font.BOLD, 14));
		//add(campo, BorderLayout.NORTH);
    	//add(campo);
        JLabel marca = new JLabel("Marca: ");
        JLabel placa = new JLabel("Placa: ");
        JLabel modelo = new JLabel("Modelo: ");
        JLabel ano = new JLabel("Ano: ");
        JLabel cor = new JLabel("Cor: ");
        marca.setBounds(50,40,100,20);
        placa.setBounds(50,80,100,20);
        modelo.setBounds(50,120,100,20);
        ano.setBounds(50,160,100,20);
        cor.setBounds(50,160,100,20);
        MaskFormatter mascmarca = null;
        MaskFormatter mascplaca = null;
        MaskFormatter mascmodelo = null;
        MaskFormatter mascAno = null;
        MarkFormatter masccor = null;
        JFormattedTextField jFormattedTextMar = new JFormattedTextField(mascmarca);
        JFormattedTextField jFormattedTextPl = new JFormattedTextField(mascplaca);
        JFormattedTextField jFormattedTextMod = new JFormattedTextField(mascmodelo);
        JFormattedTextField jFormattedTextAno = new JFormattedTextField(mascAno);
        JFormattedTextField jFormattedTextCor = new JFormattedTextField(masccor);
        jFormattedTextMar.setBounds(150,40,100,20);
        jFormattedTextPl.setBounds(150,80,100,20);
        jFormattedTextMod.setBounds(150,120,100,20);
        jFormattedTextAno.setBounds(150,160,100,20);
        jFormattedTextCor.setBounds(150,160,100,20);
        jFormattedTextMar.setColumns(2);
        jFormattedTextPl.setColumns(2);
        jFormattedTextMod.setColumns(2);
        jFormattedTextCor.setColumns(2);
        /*jFormattedTextMar.
        jFormattedTextMar.
        jFormattedTextMar.
        jFormattedTextMar.*/
       
        marca.setAlignmentX(LEFT_ALIGNMENT);
        placa.setAlignmentX(LEFT_ALIGNMENT);
        modelo.setAlignmentX(LEFT_ALIGNMENT);
        ano.setAlignmentX(LEFT_ALIGNMENT);
        cor.setAlignmentX(LEFT_ALIGNMENT);
        add(marca);
        add(jFormattedTextMar);
        add(placa);
        add(jFormattedTextPl);
        add(modelo);
        add(jFormattedTextMod);
        add(ano);
        add(jFormattedTextAno);
        add(cor);
        add(jFormattedTextCor);
        JButton cad = new JButton("Cadastrar");
        
    	revalidate();
    }
    
       
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

