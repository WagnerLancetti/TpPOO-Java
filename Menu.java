package locadora;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class Menu extends JFrame implements ActionListener{
    static Scanner input = new Scanner(System.in);
    Locadora locadora = new Locadora();
    JRadioButton sa = new JRadioButton("Sair do Programa",true);
    JRadioButton lo = new JRadioButton("Entrar no Menu de Locacoes");
    JRadioButton ca = new JRadioButton("Entrar no Menu de Carros");
    JLabel titulo = new JLabel("Menu Principal",SwingConstants.CENTER);
    JLabel titulo2 = new JLabel("Marque o que deseja fazer:",SwingConstants.CENTER);
    JButton conf = new JButton("Confirmar");
    ButtonGroup grupo = new ButtonGroup();
    
    Menu(){
        setTitle("Menu");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(6,1));
        setResizable(false);

        titulo.setFont(new Font("Arial",Font.BOLD,30));
        add(titulo);
        add(titulo2);
        
        sa.setHorizontalAlignment(SwingConstants.CENTER);
        lo.setHorizontalAlignment(SwingConstants.CENTER);
        ca.setHorizontalAlignment(SwingConstants.CENTER);
        add(sa);grupo.add(sa);
        add(lo);grupo.add(lo);
        add(ca);grupo.add(ca);
        
        conf.setPreferredSize(new Dimension(10,10));
        conf.addActionListener(this);
        add(conf);
    }
    public void actionPerformed(ActionEvent e){   
        if(sa.isSelected()){    
            System.exit(0); 
        }    
        else if(lo.isSelected()){    
            try {
				mLoca();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }
        else if(ca.isSelected()){
            try {
				mCarros();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }
    }
    public void mLoca() throws InterruptedException{
        MenuLocacao menuLoca = new MenuLocacao();
        menuLoca.Menu(locadora);
    }
    public void mCarros() throws InterruptedException{
        
        MenuCarro menuCarros = new MenuCarro();
        menuCarros.Menu(locadora);
    }
    public static void main (String []args){
    	new Menu();
    }
}
