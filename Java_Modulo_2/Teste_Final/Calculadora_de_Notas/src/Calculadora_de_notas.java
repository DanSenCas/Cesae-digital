import java.util.Scanner;

public class Calculadora_de_notas {
    static Scanner scanner = new Scanner(System.in);
    static String nomeAluno = ""; // variavel global
    static double notaAluno = 0; // variavel global

    static void main(String[] args) {
        boolean ativo = true;
        System.out.println("\n=== Calculadora de Notas ===");
        while(ativo){
            System.out.print("\nDigite o nome do aluno: ");
            nomeAluno = scanner.nextLine();
            System.out.print("Digite a nota (0-100): ");
            notaAluno = scanner.nextDouble();
            while(notaAluno>100 || notaAluno<0){
                if (notaAluno>100 || notaAluno<0){
                    System.out.println("Nota invalida tente novamente!");
                }
                System.out.print("Digite a nota (0-100): ");
                notaAluno = scanner.nextDouble();
                scanner.nextLine();
            }
            scanner.nextLine();

            System.out.println();
            System.out.println("=== Resultado ===\n");
            System.out.println("Aluno: " + nomeAluno);
            System.out.println("Nota numérica: " + notaAluno);
            System.out.println("Conceito: " + notaAmerica());
            System.out.println("Situação: " + situacao());
            System.out.println("Feedback baseado no conceito: " + feedback());
            System.out.println("Horas recomendadas de estudo: " + horasEstudo());
            System.out.println("\n\nDesejas calcular outra nota? (S/N): ");
            String verificacao = scanner.nextLine();
            char resposta = verificacao.charAt(0);
            if (resposta == 'N' || resposta == 'n'){
                ativo = false;
            }
        }

    }

    static String notaAmerica(){
        String nota = "";
        if (notaAluno >= 90 || notaAluno <= 100){
           nota = "A";
        } else if (notaAluno >= 80 || notaAluno < 90) {
            nota = "B";
        } else if (notaAluno >= 70 || notaAluno < 80) {
            nota = "C";
        } else if (notaAluno >= 60 || notaAluno < 70) {
            nota = "D";
        } else{
            nota = "F";
        }
        return nota;
    }

    static String situacao(){
        String aprovadoReprovado = "";
        if (notaAluno>0 && notaAluno<= 59){
            aprovadoReprovado = "Reprovado";
        }else {
            aprovadoReprovado = "Aprovado";
        }
        return aprovadoReprovado;
    }

    static String feedback(){
        String feedback = "";
        if (notaAluno >= 90 || notaAluno <= 100){
            feedback = "A - Excelente, tiveste um otimo desempenho.";
        } else if (notaAluno >= 80 || notaAluno < 90) {
            feedback = "B - Muito bom desempenho! Continua a esforçar-te.";
        } else if (notaAluno >= 70 || notaAluno < 80) {
            feedback = "C - Bom desempenho, continua nesse caminho!";
        } else if (notaAluno >= 60 || notaAluno < 70) {
            feedback = "D - Suficiente, Nao te preocupes estas quase a chegar la!";
        } else{
            feedback = "F - Reprovado";
        }
        return feedback;
    }

    static String horasEstudo(){
        String horas = "";
        if (notaAluno >= 90 || notaAluno <= 100){
            horas = "1 horas/dia";
        } else if (notaAluno >= 80 || notaAluno < 90) {
            horas = "2 horas/dia";
        } else if (notaAluno >= 70 || notaAluno < 80) {
            horas = "3 horas/dia";
        } else if (notaAluno >= 60 || notaAluno < 70) {
            horas = "5 horas/dia";
        } else{
            horas = "Reprovado";
        }
        return  horas;
    }

}
