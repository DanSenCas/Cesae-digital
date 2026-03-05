package Exercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo_da_Forca_Simples {
    static Scanner scanner = new Scanner(System.in);
    static void main() {

        String[] palavra = palavrasArray();
        boolean ativo = true;
        ArrayList letrasTentadas = new ArrayList();

        int tentativas = 6;
        String palavraEscolhida = palavraRandom(palavra);
        String palavraEscondida = palavraEscolhida.replaceAll("[a-z]", "*");

        while (ativo){
            System.out.println("=== JOGO DA FORCA ===");
            System.out.println("Palavra: " + palavraEscondida);

            System.out.println("Tentativa: " + tentativas);
            System.out.println("Letras Tentadas: " + letrasTentadas);
            System.out.println();

            System.out.print("Digite uma letra:");
            char letra = scanner.next().charAt(0);

            if (letrasTentadas.contains(letra)) {
                System.out.println("Já tentaste essa letra!");
                continue;
            }

            letrasTentadas.add(letra);

            if (palavraEscolhida.indexOf(letra) == -1){ // vai dizer o lugar onde esta a letra se nao existir devolve -1
                tentativas--;
            }

            palavraEscondida = revelarLetras(palavraEscolhida, palavraEscondida, letra);

            System.out.println();

            if (tentativas == 0){
                System.out.println("A palavra era: " + palavraEscolhida);
                System.out.println("Fim de tentativas melhor sorte apra a proxima");
                ativo = false;
            }

            if(palavraEscolhida.equals(palavraEscondida)){
                System.out.println("A palavra era: " + palavraEscolhida);
                System.out.println("Parabens ganhaste o jogo!");
                ativo = false;
            }
        }
    }

    static String[] palavrasArray()  {
        String palavras[] = new String[]{"arroz", "batata", "massa", "cogumelo", "mandioca"};
        return palavras;
    }

    static String palavraRandom(String[] palavra) {
        int n = (int)(Math.random() * 5);
        return  palavra[n];
    }

    static  String revelarLetras(String palavraEscolhida, String palavraEscondida, char letra){
        char[] escondida = palavraEscondida.toCharArray(); // vai tornar a String escondida em um array de chars parar se poder manipular

        for (int i = 0; i < palavraEscolhida.length(); i++) {
            if (palavraEscolhida.charAt(i) == letra){
                escondida[i] = letra;
            }
        }
        return new String(escondida);
    }
}
