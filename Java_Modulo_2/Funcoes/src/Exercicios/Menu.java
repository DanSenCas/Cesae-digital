package Exercicios;

import java.util.Scanner;
import java.lang.Thread;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static void main() {

        boolean funciona = true;

        while (funciona) {
            System.out.println();
            System.out.println("MENU:");
            System.out.println();

            System.out.println("a) Dobrar numero \n" +
                    "b) Retorno do maior de 3 numeros\n" +
                    "c)Soma dos numeros ate n\n" +
                    "d)Numero primo\n" +
                    "e)Inverter string\n" +
                    "f)Numero de vogais\n" +
                    "g)Factorial\n" +
                    "h)segundo maior valor de um array\n" +
                    "i)Palindromo\n" +
                    "j)Organizador de arrays\n" +
                    "k)Sair");

            System.out.println("\nEscola uma das opçoes a cima:");

            String escolharaw = scanner.nextLine();

            char escolha = escolharaw.toLowerCase().charAt(0);

            switch (escolha) {
                case 'a':
                    dobrarNumero();
                    break;
                case 'b':
                    maiorNumTres();
                    break;
                case 'c':
                    somaNum();
                    break;
                case 'd':
                    numPrimo();
                    break;
                case 'e':
                    inverterString();
                    break;
                case 'f':
                    contarVogais();
                    break;
                case 'g':
                    factorial();
                    break;
                case 'h':
                    segundoMaior();
                    break;
                case 'i':
                    palindromo();
                    break;
                case 'j':
                    Ordem_crescente();
                    break;
                case 'k':
                    System.out.println("Escolheste K");
                    funciona = false;
                    break;

                default:
                    System.out.println("opçao invalida");
            }

        }
    }
    static void dobrarNumero(){
        System.out.println("Insira um numero");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("O dobro é: " + (n*2));
    }
    static void maiorNumTres(){
        System.out.println("\nInsira o primeiro numero");
        int n1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nInsira o segundo numero");
        int n2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nInsira o terceiro numero");
        int n3 = scanner.nextInt();
        scanner.nextLine();

        if (n1 > n2 && n1 > n3){
            System.out.println("\n O maior numero é:" + n1);
        } else if (n2 > n1 && n2 > n3) {
            System.out.println("\n O maior numero é:" + n2);
        }else {
            System.out.println("\n O maior numero é:" + n3);
        }

        System.out.println("--------------------------------------------");

    }
    static void somaNum(){
        System.out.println("Insira um numero para fazer a soma");
        int n = scanner.nextInt();
        scanner.nextLine();
        int soma = 0;

        for (int i = 0; i <= n ; i++) {
            soma += i;
        }
        System.out.println("O valor da soma é: " + soma);
    }
    static void numPrimo(){
        System.out.println("Insira um numero para saber se é primo");
        int n = scanner.nextInt();
        scanner.nextLine();

        if(n <= 1){
            System.out.println("Nao é numero primo");
        }

        boolean  primo = true;

        for (int i = 2; i < n; i++) { // vai verificar se é divisivel por algo menos por ele proprio
            if (n%i == 0){
                primo = false;
                break;
            }

        }
        if(primo){
            System.out.println("é primo");
        }else {
            System.out.println("Nao é primo");
        }
    }
    static void Ordem_crescente(){

        System.out.println("Insira o tamanho do array que quer! ");
        int tamanho = scanner.nextInt(); // vai guardar espaço na memoria para tamanho do array

        int[] num = new int[tamanho];

        for (int i = 0; i < num.length; i++) { // vai percorrer o tamanho que foi dado ate ao fim

            System.out.print("Escreva o numero para a posiçao " + i + " : ");
            num[i] = scanner.nextInt(); // vai dar scan a posiçao atual de i
        }

        for(int i = 0; i < num.length - 1; ++i) {
            for(int j = 0; j < num.length - 1; ++j) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
        System.out.println();
        for(int i = 0; i < num.length; ++i) {
            int n = num[i];
            System.out.print(n + " ");
        }
        System.out.println();

        scanner.nextLine();
    }
    static void factorial() {
        System.out.println("Insira um numero");

        int n = scanner.nextInt();
        int y = 1;

        for (int i = 1; i <= n; i++) {
            y = y * i;
        }

        System.out.println("Numero factorial de " + n + " é " + y);
        scanner.nextLine();

    }
    static void inverterString(){
        System.out.println("Insira uma palavra para inverter: ");
        String texto = scanner.nextLine();

        String invertido = "";

        for (int i = texto.length() - 1 ; i >= 0 ; i--) { // começar do fim para o inicio
            invertido += texto.charAt(i);
        }

        System.out.println("Invertido: " + invertido);
    }
    static void palindromo(){
        System.out.println("Insira uma palavra para inverter: ");
        String texto = scanner.nextLine();

        String invertido = "";

        for (int i = texto.length() - 1 ; i >= 0 ; i--) { // começar do fim para o inicio
            invertido += texto.charAt(i);
        }

        if (!texto.equals(invertido)){
            System.out.println("Nao é palindromo");
        }else {
            System.out.println("É palindromo");
        }
    }
    static void contarVogais(){
        System.out.println("Insira uma frase ou palavra");
        String texto = scanner.nextLine().toLowerCase();

        int contador = 0;

        for (int i = 0; i < texto.length() ; i++) {
            char a = texto.charAt(i);
            if (a == 'a' || a == 'e' ||a == 'i' ||a == 'o' ||a == 'u' ){
                contador++;
            }
        }
        System.out.println("O numeor de vogais é : " + contador);
    }
    static void segundoMaior(){

        System.out.println("Insira o tamanho do array que quer! ");
        int tamanho = scanner.nextInt(); // vai guardar espaço na memoria para tamanho do array

        if (tamanho < 2){
            System.out.println("O array tem menos de 2 elementos!");
            return;
        }

        int[] num = new int[tamanho];

        for (int i = 0; i < num.length; i++) { // vai percorrer o tamanho que foi dado ate ao fim

            System.out.print("Escreva o numero para a posiçao " + i + " : ");
            num[i] = scanner.nextInt(); // vai dar scan a posiçao atual de i
        }

        int maior = Integer.MIN_VALUE;
        int segundoMaior = Integer.MIN_VALUE;

        for (int i = 0; i < num.length; i++) {
            
            if(num[i]>maior) {
                segundoMaior = maior;
                maior = num[i];
            } else if (num[i] > segundoMaior && num[i] != maior) {
                segundoMaior = num[i];
            }

        }

        System.out.println(" o segundo maior numero é " + segundoMaior);
        scanner.nextLine();

    }

}
