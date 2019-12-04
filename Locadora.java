package TrabalhoPratico;

import java.util.ArrayList;

public class Locadora extends Entidade{
    /* ATRIBUTOS */
    ArrayList<Carro> cars = new ArrayList<>();
    ArrayList<Carro> carsAlugados = new ArrayList<>();
    ArrayList<Locacao> locacoes = new ArrayList<>();
    
    /* METODOS */
    public Carro ListarCarrosParaAlugar(int i) { // Mostra um carro que pode ser alugado, geralmente dentro de um loop
    	return cars.get(i);
    }
    
    
    public void CriarLocacao (ArrayList<Carro> carro, String nome, String data,int num) { // Cria uma nova locacao
    	Locacao locacao = new Locacao(nome,data,num);
    	int i = 0;
    	while (i < carro.size()) {
    		locacao.addCarro(carro.get(i));
    		i++;
    	}
    	this.locacoes.add(locacao);
    }

    
    public void DevolverCarro(int entrada, int entrada2) { // Devolve um carro de uma locacao
    	this.locacoes.get(entrada).getCarro(entrada2).setAlugado(false);
    	int j = 0;
    	while (j < this.carsAlugados.size()) {
    		if (this.locacoes.get(entrada).getCarro(entrada2).getPlaca().equals(this.carsAlugados.get(j).getPlaca())) {
    			this.carsAlugados.get(j).setAlugado(false);
    			this.cars.add(this.carsAlugados.get(j));
    			this.carsAlugados.remove(j);
    			j = this.carsAlugados.size();
    		}
    		j ++;
    	}
    	this.locacoes.get(entrada).DelCarro(entrada2);
    }
    
    
    public void DevolverLocacao(int entrada) { // Devolve toda uma locacao e os carros que nela existem
    	int h = 0;
    	int k;
    	while (h < this.locacoes.get(entrada).getTam()) {
    		k = 0;
    		while (k < this.carsAlugados.size()) {
    			if (this.carsAlugados.get(k).getPlaca().equals(this.locacoes.get(entrada).getCarro(h).getPlaca())) {
    				this.locacoes.get(entrada).getCarro(h).setAlugado(false);
    				this.cars.add(this.locacoes.get(entrada).getCarro(h));
    				this.carsAlugados.remove(k);
    				k = k + this.carsAlugados.size();
    			}
    			k ++;
    		}
    		h ++;
    	}
    	this.locacoes.remove(entrada);
    }
    
    
    public ArrayList<Carro> BuscarCarro(int entrada,String valor){ // Busca carros com opcao de valor em String
        ArrayList<Carro> especificidade = new ArrayList<>();
        Carro Carro0;
        int i;
        switch (entrada){
            case 1: // Buscar por Marcas
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.getMarca().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.getMarca().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;

            case 2: // Buscar por Modelos
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.getModelo().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.getModelo().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;

            case 3: // Buscar por Cor
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.getCor().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.getCor().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;

            case 5: // Buscar por Placas
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.getPlaca().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.getPlaca().equals(valor)){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;
        }
        return especificidade;
    }
    
    
    public ArrayList<Carro> BuscarCarroInt(int entrada,int verificacao1){ // Busca carros com opcao de valor em inteiros (int)
        ArrayList<Carro> especificidade = new ArrayList<>();
        Carro Carro0;
        int i;
        switch (entrada){
            case 4: // Buscar por Ano
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.getAno() == verificacao1){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.getAno()== verificacao1){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;
                
            case 6: // Buscar por ID
                i = 0;
                while (i < this.cars.size()){
                    Carro0 = this.cars.get(i);
                    if (Carro0.identificador == verificacao1){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                i = 0;
                while (i < this.carsAlugados.size()){
                    Carro0 = this.carsAlugados.get(i);
                    if (Carro0.identificador == verificacao1){
                        especificidade.add(Carro0);
                    }
                    i++;
                }
                break;
        }
        return especificidade;
    }
}