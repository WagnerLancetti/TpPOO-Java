package tp2poo;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
 
public class Arquivo extends Entidade{
		
	/*public void carregaArquivo(String nome,Locadora locadora) throws FileNotFoundException{    
		    try {   
		    	
		    	Carro c = new Carro();
		    	String marca=null;
	            String modelo=null;
	            int ano=0;
	            String cor=null;
	            boolean alugado=false;
	            String placa=null;
	            String cliente = null;
	        	String data = null;
	        	String carro = null;
	            File f = new File(nome);
		        Scanner scan = new Scanner(f);
		        while (scan.hasNextLine()) { 
		        	String line = scan.nextLine();
		        	if (line.equals(""))
		        	    continue;
		        if("carros.txt" == nome) {
		        	 String[] particao= line.split("; ");
		             marca = particao[0];
		             modelo = particao[1];
		             ano = Integer.parseInt(particao[2]);
		             cor = particao[3];
		             placa = particao[5];
		             alugado = false;
		             alugado = Boolean.parseBoolean(particao[5]);
		             Carro novoCar;
		             novoCar.setMarca(marca);
		             novoCar.setPlaca(placa);
		             novoCar.setCor(cor);
		             novoCar.setModelo(modelo);
		             novoCar.setAno(ano);
		             locadora.cars.add(novoCar);
		           //System.out.println("RESULT marca="+marca+" modelo="+modelo+" ano="+ano+"cor="+cor+" alugado="+alugado+" placa="+placa);
		        }else if("locacoes.txt" == nome) { 
		        	String line2 = scan.nextLine();
		        	String line3 = scan.nextLine();
		        	String[] particao1 = line.split(": "); //separa cada linha no ":"
		        	String[] particao2 = line2.split(": ");
		        	String[] particao3 = line3.split(": ");
		        	//String[] particao4 = line4.split(": ");
		        	//recebem a segunda parte na partição
		        	 cliente = particao1[1]; 
		        	 data = particao2[1];
		        	 carro = particao3[1];//todos os carros contidos na mesma string mas serao separados
		        	 System.out.println("cliente = "+cliente+" data = "+data+"carro = "+carro);
		        	 //a string carro é separada onde tem "," 
		        	 //cada partição é um carro alugado
		        	 String[] particao4 = carro.split(", "); 
		        	 int i=0;
		        	 String[] cars = new String[particao4.length]; 
		        	 //o vetor cars recebe as partes separadas
		        	 while(i<particao4.length) {//particao4.length = quantidade de carros
		        		 cars[i] = particao4[i];
		        		// System.out.println("carro0="+cars[i]);
		        		  i++;
		        	 } 
		        	 //Separa os atributos do carro pra mandar pro vetor de carros alugados
		        	 String Marca;
		        	 String Modelo;
		        	 int Ano ;
			         String Cor;
			         boolean Alugado;
			         String Placa;
		        	 int j=0;
		        	 ArrayList<Carro> carsAlugados = new ArrayList<>();
		        	 while(j < cars.length) { 
		        		 String[] particao5 = cars[j].split("; ");
			             Marca = particao5[0];
			             //System.out.println("TESTE MARCA="+Marca);
			             Modelo = particao5[1];
			            // System.out.println("TESTE MOD="+Modelo);
			             Ano = Integer.parseInt(particao5[2]);
			           //  System.out.println("TESTE ANO="+Ano);
			             Cor = particao5[3];
			             Placa = particao5[4];
			            // System.out.println("TESTE PLACA="+Placa);
			             Alugado = true;		
			            // System.out.println("RESULT2 marca= "+Marca+"modelo="+Modelo+"ano="+Ano+"cor="+Cor+"alugado="+Alugado+"placa="+Placa);
			             Carro c3;
			             c3.setMarca(marca);
			             c3.setPlaca(placa);
			             c3.setCor(cor);
			             c3.setModelo(modelo);
			             c3.setAno(ano);
			             locadora.cars.add(c3);
			             System.out.println(c3);
			             carsAlugados.add(c3);
			             j++;
			            }
		        	 locadora.CriarLocacao(carsAlugados,cliente,data,num);
		        }
		        }
		        scan.close();
		    } catch (IOException ioe) {
		        ioe.printStackTrace();
		    }
		}
	*/
	public void gravaArquivo(ArrayList carro,String nome) {
		    try {
		    	FileWriter fw = new FileWriter(nome);
		        BufferedWriter output = new BufferedWriter(fw);
		        for (int i = 0; i < carro.size(); i++){
		            output.write(carro.get(i).toString());
		            output.newLine();
		        }
		        output.close();
		    } catch (Exception e ) {
				JOptionPane.showMessageDialog(null, "erro");
		    }
		}
	

	}

