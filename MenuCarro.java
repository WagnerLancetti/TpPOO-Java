package TrabalhoPratico;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

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
        			// Chama uma funcao para mostrar os carros
        			
        		
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Remover Carro")) {
        			System.out.println("Funciona aqui tambem");
        			// Funcao para criar locacao
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver um carro de uma locacao especifica")) {
        			// Funcao para devolver carro
        			System.out.println("Teste");
        		}else if (cb.getItemAt(cb.getSelectedIndex()).equals("Devolver uma locacao completa")){
        			//Funcao para devolver locacao
        			System.out.println("Franciele caladona!\n");
        		}
        		
    		}
    	});
        add(conf);
	}
    
       
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

