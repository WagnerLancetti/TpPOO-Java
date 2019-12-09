package TrabalhoPratico;

import java.util.ArrayList;

public class Locacao extends Entidade{
	/* ATRIBUTOS */
	private String Cliente;
	private String Data;
	private ArrayList<Carro> carros = new ArrayList<>();
	
	/* CONSTRUTORES */
    public Locacao() {
        this.Cliente = null;
        this.Data = null;
		
	}
	public Locacao(String Cliente, String Data, int num) {
            this.Cliente = Cliente;
            this.Data = Data;
            this.identificador = num;
	}
	
	/* METODOS GETTERS E SETTERS */
	public int getTam() {
            return this.carros.size();
	}
	public Carro getCarro(int indice) {
		return this.carros.get(indice);
	}
	public String getCliente() {
		return this.Cliente;
	}
	public void setCliente(String cliente) {
		this.Cliente = cliente;
	}
	public String getData() {
		return this.Data;
	}
	public void setData(String data) {
		this.Data = data;
	}
	public void setCarros(ArrayList<Carro> carros) {
		this.carros = carros;
	}
	public ArrayList<Carro> getCarros() {
		return carros;
	}
	/* MÃ‰TODOS */
    @Override
    public String toString() {
            return "Locacao do cliente: "+this.getCliente()+" - Reserva feita em: "+this.getData();
    }
    public void DelCarro(int entrada) {
            this.carros.remove(entrada);
    }
    public void addCarro(Carro carro0){
        this.carros.add(carro0);
    }

}
