package Exercicios;

import java.util.Scanner;

public class Calculadora_de_notas {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        int[][] notas = new int[4][3];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("Insira a nota do aluno " + i + " na disciplina " + j + ":");
                notas[i][j] = scanner.nextInt();
            }
        }

        System.out.println();
    }
}
