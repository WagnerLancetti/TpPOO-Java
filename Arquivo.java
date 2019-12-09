package TrabalhoPratico;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
 
public class Arquivo {
    private static Arquivo instance = null;

    private Arquivo(){}

    public static Arquivo getInstance(){
        if (instance == null){
            instance = new Arquivo();
        }
        return instance;
    }
    //private Carro c3;
    public void carregaArquivo(String nome,Locadora locadora) throws FileNotFoundException, IOException{    
                try {
                    FileReader reader = new FileReader ("locadora.txt");
                    BufferedReader br = new BufferedReader(reader);
                    boolean continua = true;
                    String VLinha,LinhaCars = null,LinhaAlugados = null,LinhaLoca = null;
                    VLinha = br.readLine();
                    String[] textoSeparado;
                    Locacao locas;
                    if (VLinha.equals("Cars")){
                        while(continua){
                            LinhaCars = br.readLine();
                            if (LinhaCars.equals("CarsAlugados")){
                                continua = false;
                            }else{
                                Carro carros;
                                carros = new Carro();
                                textoSeparado = LinhaCars.split(" ");
                                carros.identificador = Integer.parseInt(textoSeparado[0]);
                                carros.setMarca(textoSeparado[1]);
                                carros.setPlaca(textoSeparado[2]);
                                carros.setCor(textoSeparado[3]);
                                carros.setModelo(textoSeparado[4]);
                                carros.setAno(Integer.parseInt(textoSeparado[5]));
                                carros.setAlugado(false);
                                locadora.cars.add(carros);
                            }
                        }
                    }
                    continua = true;
                    if(VLinha.equals("CarsAlugados") || LinhaCars.equals("CarsAlugados")){
                        while(continua){
                            LinhaAlugados = br.readLine();
                            if (LinhaAlugados.equals("Locacoes")){
                                continua = false;
                            }else{
                                Carro carros;
                                carros = new Carro();
                                textoSeparado = LinhaAlugados.split(" ");
                                carros.identificador = Integer.parseInt(textoSeparado[0]);
                                carros.setMarca(textoSeparado[1]);
                                carros.setPlaca(textoSeparado[2]);
                                carros.setCor(textoSeparado[3]);
                                carros.setModelo(textoSeparado[4]);
                                carros.setAno(Integer.parseInt(textoSeparado[5]));
                                carros.setAlugado(true);
                                locadora.carsAlugados.add(carros);
                            }
                        }
                    }
                    if(VLinha.equals("Locacoes") || LinhaAlugados.equals("Locacoes")){
                        int tam = 0,i,k;
                        while((LinhaLoca = br.readLine()) != null){
                            System.out.println(LinhaLoca);
                            textoSeparado = LinhaLoca.split(" ");
                            locas = new Locacao();
                            locas.identificador = (Integer.parseInt(textoSeparado[0]));
                            locas.setCliente(textoSeparado[1]);
                            locas.setData(textoSeparado[2]);
                            tam = Integer.parseInt(textoSeparado[3]);
                            i = 0;
                            k = 4;
                            while (i < tam){
                                Carro carros;
                                carros = new Carro();
                                carros.identificador = Integer.parseInt(textoSeparado[k]);
                                k++;
                                carros.setMarca(textoSeparado[k]);
                                k++;
                                carros.setPlaca(textoSeparado[k]);
                                k++;
                                carros.setCor(textoSeparado[k]);
                                k++;
                                carros.setModelo(textoSeparado[k]);
                                k++;
                                carros.setAno(Integer.parseInt(textoSeparado[k]));
                                k++;
                                carros.setAlugado(true);
                                k++;
                                i++;
                                locas.addCarro(carros);
                            }
                            locadora.locacoes.add(locas);
                        }
                    }
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
    public void gravaArquivo(Locadora locadora,String nome) {
        int i,j;
        try {
            FileWriter fw = new FileWriter("teste.txt");
            BufferedWriter saida = new BufferedWriter(fw);
            if (locadora.cars.size() > 0){
                saida.write("Cars\n");
                i = 0;
                while (i < locadora.cars.size()){
                    saida.write((Integer.toString(locadora.cars.get(i).identificador))+" ");
                    saida.write(locadora.cars.get(i).getMarca()+" ");
                    saida.write(locadora.cars.get(i).getPlaca()+" ");
                    saida.write(locadora.cars.get(i).getCor()+" ");
                    saida.write(locadora.cars.get(i).getModelo()+" ");
                    saida.write((Integer.toString(locadora.cars.get(i).getAno()))+" ");
                    saida.write("false\n");
                    i++;
                }
            }
            if (locadora.carsAlugados.size() > 0){
                saida.write("CarsAlugados\n");
                i = 0;
                while (i < locadora.carsAlugados.size()){
                    saida.write((Integer.toString(locadora.carsAlugados.get(i).identificador))+" ");
                    saida.write(locadora.carsAlugados.get(i).getMarca()+" ");
                    saida.write(locadora.carsAlugados.get(i).getPlaca()+" ");
                    saida.write(locadora.carsAlugados.get(i).getCor()+" ");
                    saida.write(locadora.carsAlugados.get(i).getModelo()+" ");
                    saida.write((Integer.toString(locadora.carsAlugados.get(i).getAno()))+" ");
                    saida.write("true\n");
                    i++;
                }
            }
            if (locadora.locacoes.size() > 0){
                saida.write("Locacoes\n");
                i = 0;
                while (i < locadora.locacoes.size()){
                    saida.write(Integer.toString(locadora.locacoes.get(i).identificador)+" ");
                    saida.write(locadora.locacoes.get(i).getCliente()+" ");
                    saida.write(locadora.locacoes.get(i).getData()+" ");
                    saida.write(Integer.toString(locadora.locacoes.get(i).getTam())+" ");
                    j = 0;
                    while (j < locadora.locacoes.get(i).getTam()){
                        saida.write(Integer.toString(locadora.locacoes.get(i).getCarro(j).identificador)+" ");
                        saida.write(locadora.locacoes.get(i).getCarro(j).getMarca()+" ");
                        saida.write(locadora.locacoes.get(i).getCarro(j).getPlaca()+" ");
                        saida.write(locadora.locacoes.get(i).getCarro(j).getCor()+" ");
                        saida.write(locadora.locacoes.get(i).getCarro(j).getModelo()+" ");
                        saida.write(Integer.toString(locadora.locacoes.get(i).getCarro(j).getAno())+" ");
                        saida.write("true" + " ");
                        j++;
                    }
                    saida.write("\n");
                    i++;
                }
            }
            saida.close();
        } catch (Exception e ) {
                    JOptionPane.showMessageDialog(null, "erro");
        }
    }


}

