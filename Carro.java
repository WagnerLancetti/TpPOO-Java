package TrabalhoPratico;

public class Carro extends Entidade{
    /* ATRIBUTOS */
    private String Marca;
    private String Placa;
    private String Cor;
    private String Modelo;
    private int Ano;
    private boolean Alugado;


    /* CONSTRUTORES */
    public Carro() {
        this.Marca = null;
        this.Placa = null;
        this.Cor = null;
        this.Modelo = null;
        this.Ano = 0;
        this.Alugado = false;
        this.identificador = -1;
    }

    public Carro(String marca,String placa,String cor,String modelo,int ano,int num) {
        this.Marca = marca;
        this.Placa = placa;
        this.Cor = cor;
        this.Modelo = modelo;
        this.Ano = ano;
        this.Alugado = false;
        this.identificador = num;
    }
    
    /* METODOS SETTERS E GETTERS */
    public boolean isAlugado() {
        return Alugado;
    }
    public void setAlugado(boolean Alugado) {
        this.Alugado = Alugado;
    }
    
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public String getPlaca() {
        return Placa;
    }
    public void setPlaca(String placa) {
        Placa = placa;
    }
    public String getCor() {
        return Cor;
    }
    public void setCor(String cor) {
        Cor = cor;
    }
    public String getModelo() {
        return Modelo;
    }
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    public int getAno() {
        return Ano;
    }
    public void setAno(int ano) {
        Ano = ano;
    }
    

    /* METODOS */
    @Override
    public String toString(){
        return "Carro: "+this.getMarca()+", "+this.getModelo()+", "+this.getAno()+", "+this.getCor()+", placa "+this.getPlaca()+", ID do carro "+this.identificador+".";
    }
	
        
}