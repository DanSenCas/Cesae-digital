package exercicios;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Array_utilizador {
    static void main() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o tamanho do array que quer! ");
        int tamanho = scanner.nextInt(); // vai guardar espaço na memoria para tamanho do array

        int[] num = new int[tamanho];

        for (int i = 0; i < num.length; i++) { // vai percorrer o tamanho que foi dado ate ao fim

            System.out.print("Escreva o numero para a posiçao " + i + " : ");
            num[i] = scanner.nextInt(); // vai dar scan a posiçao atual de i
        }
        //System.out.println(Arrays.toString(num)); // so para verificar se o array esta bem inserido

        System.out.println("-----------------------------------------");
        int soma = 0;

        for (int i = 0; i < num.length; i++) {
            soma = soma+num[i];
        }
        System.out.println("soma: " + soma);
        int media = soma/tamanho;
        System.out.println("media: " + media);


    }
}
