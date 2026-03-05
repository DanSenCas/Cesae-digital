import java.util.Scanner;

public class jogo_da_adivinha {
    static Scanner scanner = new Scanner(System.in);
    static String nomeUtilizador;
    static int dificuldadeJogo;
    static String dificuldadeEscrita;
    static int numeroAleatorio;
    static int tentativas;
    static int contador = 0;
    static boolean vitoria = false;
    static int[] guardarTentativa;


    static void main() {
        System.out.println("=== Jogo de Adivinhação ===");
        utilizador();
        String pergunta;
        do {
            jogoReset(); // reset em cada jogo
            dificuldade();
            System.out.println("Bem-vindo " + nomeUtilizador + "!" + "Vamos começar!");
            System.out.println("Dificuldade: " + dificuldadeEscrita);
            System.out.println("Tentativas máximas: " + tentativasJogo());
            numAleatorio();
            tentativaJogo();
            estatisticas();

            scanner.nextLine();

            do{
                System.out.println("Desejas jogar novamente? (S/N): ");
                pergunta = scanner.nextLine();

                if((!pergunta.equalsIgnoreCase("s") && !pergunta.equalsIgnoreCase("n"))){
                    System.out.println("Resposta invalida!");
                }

            }while(!pergunta.equalsIgnoreCase("s") && !pergunta.equalsIgnoreCase("n"));

        } while (pergunta.toLowerCase().equals("s"));
    }

    static void utilizador(){
        System.out.print("Digite o seu nome: ");
        nomeUtilizador = scanner.nextLine();
    }

    static void dificuldade(){
        do {
            System.out.println("Escolha a dificuldade:");
            System.out.println("1. Fácil (1-50)");
            System.out.println("2. Médio (1-100)");
            System.out.println("3. Difícil (1-200)");
            System.out.print("Opçao: ");
            dificuldadeJogo = scanner.nextInt();
            System.out.println();

            if (dificuldadeJogo < 1 || dificuldadeJogo > 3) {
                System.out.println("Opçao invalida\n");
            }

            if (dificuldadeJogo == 1){
                dificuldadeEscrita = "Fácil (1-50)";
            } else if (dificuldadeJogo == 2) {
                dificuldadeEscrita = "Médio (1-100)";
            }else {
                dificuldadeEscrita = "Difícil (1-200)";
            }
        }while (dificuldadeJogo < 1 || dificuldadeJogo > 3);
    }

    static int tentativasJogo(){
        if (dificuldadeJogo == 1){
            tentativas = 6;
        } else if (dificuldadeJogo == 2) {
            tentativas = 10;
        }else{
            tentativas = 15;
        }
        return  tentativas;
    }

    static void numAleatorio(){

        if (dificuldadeJogo == 1){
            numeroAleatorio= (int)(Math.random() * (50 - 1 + 1)) + 1;
        } else if (dificuldadeJogo == 2) {
            numeroAleatorio= (int)(Math.random() * (100 - 1 + 1)) + 1;
        }else {
            numeroAleatorio= (int)(Math.random() * (200 - 1 + 1)) + 1;
        }
    }

    static void tentativaJogo() {
        int adivinha = 0;
        guardarTentativa = new int[tentativas];
        do {
            boolean inputValido = false; // validar se é um numero no intrevalo

            int minimo = 1;
            int maximo;
            if (dificuldadeJogo == 1) {
                maximo = 50;
            } else if (dificuldadeJogo == 2) {
                maximo = 100;
            } else {
                maximo = 200;
            }

            while(!inputValido){  // Repete ATÉ o input ser válido
                System.out.print("\nTentativa #" + (contador+1) + " (entre " + minimo + " e " + maximo + "): ");
                adivinha = scanner.nextInt();

                if(adivinha < minimo || adivinha > maximo){
                    System.out.println("Número inválido! Deve estar entre " + minimo + " e " + maximo + ".");
                } else {
                    inputValido = true;
                }
            }

            guardarTentativa[contador] = adivinha;

            dicaJogo(adivinha);

            contador++;

            System.out.print("Tentativas anteriores: ");
            for(int i = 0; i < contador; i++){
                System.out.print(guardarTentativa[i] + " ");
            }
            System.out.println();

        } while (adivinha != numeroAleatorio && contador < tentativas);

        if (adivinha == numeroAleatorio){
            vitoria = true;
        }
    }

    static void dicaJogo(int adivinha){
        if (adivinha <numeroAleatorio){
            System.out.println("Numero baixo!");
        }else if (adivinha > numeroAleatorio){
            System.out.println("Numero alto!");
        } else {
            System.out.println("Numero correcto");
        }
    }

    static void estatisticas(){
        System.out.println("\n=== Estatísticas do Jogo ===");
        System.out.println("Jogador: " + nomeUtilizador);
        System.out.println("Número sorteado: " + numeroAleatorio);
        System.out.println("Tentativas usadas: " + contador+"/"+tentativas);
        System.out.println("Histórico: " );
        for(int i = 0; i < contador; i++){
            System.out.print(guardarTentativa[i]);
            if(i < contador - 1) System.out.print(", ");
        }
        System.out.println();
        String estado;
        if(vitoria){
            estado = "VITORIA";
        }else {
            estado = "DERROTA";
        }
        System.out.println("Resultado: " + estado);
    }

    static void jogoReset(){
        contador = 0;
        vitoria = false;
    }
}
