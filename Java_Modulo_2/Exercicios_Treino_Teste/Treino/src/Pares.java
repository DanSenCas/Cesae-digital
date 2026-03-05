import java.util.ArrayList;
import java.util.Scanner;


//Programa que lê 5 números e diz quantos são pares

public class Pares {

    static Scanner scanner = new Scanner(System.in);
    static int[] numeros = new int[5]; //guardar na memoria 5 espaços
    static int counter = 0;
    static ArrayList<Integer> numerosPares = new ArrayList<>();

    static void main(String[] args) {
        executar();
    }

    static void executar(){

        for (int i = 0; i < 5; i++) {
            System.out.println("Digite os 5 numeros que deseja resolver, Vai no " + (i+1) + ":");
            numeros[i] = scanner.nextInt();
        }

        pares();

        System.out.println("Este é o nuemro de pares existentes "+ counter + " e estes sao os numeros pares ");

        for (int i = 0; i < numerosPares.size(); i++) {
            System.out.println(numerosPares.get(i));
        }
    }

    static void pares(){

        for (int i = 0; i < numeros.length; i++) {
            if(numeros[i] % 2 == 0){
                counter++;
                numerosPares.add(numeros[i]);
            }
        }
    }
}
