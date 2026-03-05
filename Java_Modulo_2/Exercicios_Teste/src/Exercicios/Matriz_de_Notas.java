package Exercicios;

import java.util.Scanner;

public class Matriz_de_Notas {
    static void main() {
        String[] disciplinas = {"Mat", "Port", "Ing", "Hist"};
        Scanner scanner = new Scanner(System.in);
        double[][] notas = gerarMatriz();
        for (int i = 0; i < 5; i++) {
            System.out.println("Notas do Aluno " + (i+1));
            for (int j = 0; j < 4; j++) {
                System.out.print("Nota da disciplina " + disciplinas[j] + ": ");
                notas[i][j] = scanner.nextDouble();
            }
            System.out.println();
        }
        imprimirMatriz(notas);
        mediaAluno(notas);
        mediaDisciplinas(notas, disciplinas);
        maiorNota(notas, disciplinas);
        menorNota(notas, disciplinas);
        contarNotas(notas, disciplinas);

    }

    static double[][] gerarMatriz(){
        double[][] matriz = new double[5][4]; // double pq as notas podem ter decimais
        return matriz;
    }

    static void imprimirMatriz(double[][] notas){
        System.out.println("Verificar notas");

        for (int i = 0; i < 5; i++) {
            System.out.println("Aluno " + (i+1) + ": ");
            for (int j = 0; j < 4; j++) {
                System.out.println(notas[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    static void mediaAluno(double[][] notas){
        for (int i = 0; i < 5; i++) {
            double somaNotas = 0;
            for (int j = 0; j < 4; j++) {
                somaNotas += notas[i][j];
            }
            double media = somaNotas/4;
            System.out.println("Media do aluno " + (i+1) + ": " + media);
        }
    }

    static void mediaDisciplinas(double[][] notas, String[] disciplinas){
        for (int j = 0; j < 4; j++) {
            double somaDisciplinas = 0;
            for (int i = 0; i < 5; i++) {
                somaDisciplinas += notas[i][j]; // o j vai se manter 0 (as disciplinas) e vai percorer percorer os i (alunos) ou seja vai prender em mat e percorrer os alunos em mat
            }
            double media = somaDisciplinas /5;
            System.out.println("Media da disciplina " + disciplinas[j] + ": " + media);
        }
    }

    static void maiorNota(double[][] notas, String[] disciplinas) {
        double notaAlta = notas[0][0]; // começa pela priemira nota
        int aluno = 0;
        int materia = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (notas[i][j] > notaAlta) { // compara com a maxima atual incluindo se o i ou o j mudar
                    notaAlta = notas[i][j];
                    aluno = i;
                    materia = j;
                }
            }
        }
        System.out.println("A nota mais alta é " + notaAlta + "do aluno" + (aluno+1) + " na disciplina " + disciplinas[materia]);
    }

    static void menorNota(double[][] notas, String[] disciplinas){
        double notaBaixa = notas[0][0];
        int aluno = 0;
        int materia = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (notas[i][j] < notaBaixa){
                    notaBaixa = notas[i][j];
                    aluno = i;
                    materia = j;
                }
            }
        }
        System.out.println("A nota mais baixa é " + notaBaixa + " do aluno " + (aluno+1) + " na disciplina " + disciplinas[materia]);
    }

    static void contarNotas(double[][] notas, String[] disciplinas){
        for (int j = 0; j < 4; j++) {
            int contador = 0;
            for (int i = 0; i < 5; i++) {
                if (notas[i][j] >= 10){
                    contador++;
                }
            }
            System.out.println(disciplinas[j] + " passou " + contador + " alunos!");
        }
    }
}
