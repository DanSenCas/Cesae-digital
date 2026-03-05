
// Cria um método somarPositivos(int[] numeros) que devolve a soma apenas dos valores positivos.

public class teste {

    static void main(String[] args) {
        int[] numeros;
        numeros = new int[]{1, 2, -2, -3, 5};

        System.out.println("Esta é a soma " + somarPositivos(numeros));
    }

    static int somarPositivos(int [] numeros){

        int soma = 0;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] >= 0){
                soma = soma + numeros[i];
            }
        }

        return soma;
    }
}
