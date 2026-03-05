public class Carro {
    //Atributos
    private String marca;
    private String modelo;
    private int ano;

    //Contrutor
    public Carro(String brand, int year, String model) {
        this.marca = brand;
        this.ano = year;
        this.modelo = model;
    }

    //Getters e setters
    public int getAno() {
        return ano;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
