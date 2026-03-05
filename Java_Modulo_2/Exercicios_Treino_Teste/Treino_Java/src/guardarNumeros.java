import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class guardarNumeros {
    //Atributos
    private List<Integer> numeros;
    private int totalNumeros;
    private int soma;

    //Contrutor
    public guardarNumeros() {
        this.numeros = new ArrayList<>();
        this.totalNumeros = 0;
        this.soma = 0;
    }


    //metodos

    public void lerDocumento() throws IOException {
        BufferedReader ler = new BufferedReader(new FileReader("numeros.txt"));
        String numero;
        while ((numero = ler.readLine()) != null) {
            numeros.add(Integer.valueOf(numero));
        }

        totalNumeros = numeros.size();
        System.out.println("O total de numeros é " + totalNumeros);

    }

    public int somaNumeros() {
        int numero;
        for (int i = 0; i < numeros.size(); i++) {
            numero = numeros.get(i);
            soma = numero + soma;
        }
        System.out.println("A soma é " + soma);
        return soma;
    }

    public int mediaNumeros(){
        int media = soma/totalNumeros;
        System.out.println("A media é " + media);
        return media;
    }

    //Getters e Setters

    public int getTotalNumeros() {
        return totalNumeros;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setTotalNumeros(int totalNumeros) {
        this.totalNumeros = totalNumeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }
}
