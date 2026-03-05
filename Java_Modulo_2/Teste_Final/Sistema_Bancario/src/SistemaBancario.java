import java.util.Scanner;

public class SistemaBancario {
    static Scanner scanner = new Scanner(System.in);

    //Colocar as variaveis assim como o Scanner disponiveis para todas as funçoes
    static String nomeTitular = ""; //placeholder para ter uma variavel guardada
    static double saldo = 0;
    static int numTransacoes = 0;
    static boolean contaCriada = false;
    static String estadoConta = "Inativo";

    static void main(String[] args) {

        boolean ativo = true; // para manter o programa ativo

        while(contaCriada==false){
            criarConta();
        }

        while(ativo){
            System.out.println("\n\n-----Bem Vindo Ao Banco Java-----");
            System.out.println();
            System.out.println("MENU:\n" +
                    "1 - Realizar depósito\n" +
                    "2 - Realizar levantamento\n" +
                    "3 - Verificar saldo\n" +
                    "4 - Sair\n");
            System.out.println("\n Escolha uma das opções do menu! (1-4)");

            String escolharaw = scanner.nextLine();//para poder receber 1- ou 1) no input sem dar erro
            char escolha = escolharaw.charAt(0); // caso o utilizador decida usar um simbulo depois do numero

            switch (escolha){
                case '1':
                    fazerDeposito();
                    break;
                case '2':
                    fazerLevantamento();
                    break;
                case '3':
                    verificarSaldo();
                    break;
                case '4':
                    System.out.println("Obrigado por usar o Banco Java");
                    ativo=false; // para desativar o while
                    break;
                default:
                    System.out.println("opçao invalida");
            }
        }
    }

    static void criarConta(){
        double depositoInicial = 0;
        System.out.println("\nDigite o nome do titular:");
        nomeTitular = scanner.nextLine();
        while(depositoInicial<100){ // o minimo que coloquei foi 100
            System.out.println("\nDigite o depósito inicial:");
            depositoInicial = scanner.nextDouble();
            scanner.nextLine();
            if(depositoInicial<100 && depositoInicial>0){  // para lidar apenas com o valor minimo
                System.out.println("Valor de deposito muito baixo! valor minimo de 100");
            } else if (depositoInicial<0) { // para lidar apenas com numeros negativos
                System.out.println("Nao se aceitao valores negativos");
            } else {
                saldo = saldo + depositoInicial;
                numTransacoes++; // aumento de transaçoes
                contaCriada = true;
            }
        }
        estadoConta = "Ativo"; // mudar diretamente o estado da conta

        System.out.println("\n=== Conta criada com sucesso! ===\n");
        System.out.println("Titular: " + nomeTitular);
        System.out.println("Saldo: " + saldo + "€");
        System.out.println("Estado: " + estadoConta);
        System.out.println("Transações: " + numTransacoes);
    }
    static void fazerDeposito(){
        double valorDeposito = 0;

        System.out.println("\n\n--- Depósito ---\n");
        System.out.print("Digite o valor para depósito:");
        valorDeposito = scanner.nextDouble();
        scanner.nextLine();
        if (valorDeposito<0){
            System.out.println("Nao se aceitao valores negativos");
        }else if (valorDeposito == 0) { // lidar se o utilizador nao depositar nada
            System.out.println("Tente introduzir um valor");
        }else {
            saldo = saldo + valorDeposito;
            numTransacoes++;

            System.out.println("=== Depósito realizado com sucesso! ===");
            System.out.println("Novo saldo: " + saldo + "€");
            System.out.println("Transações: " + numTransacoes);
        }
    }
    static void fazerLevantamento(){
        double valorLevantamento = 0;

        System.out.println("\n\n--- Depósito ---\n");
        System.out.print("Digite o valor para depósito:");
        valorLevantamento = scanner.nextDouble();
        scanner.nextLine();

        if (valorLevantamento <0){
            System.out.println("Nao se aceitao valores negativos");
        } else if (valorLevantamento == 0) {
            System.out.println("Tente introduzir um valor");
        } else if (saldo < valorLevantamento) {
            System.out.println("Nao tem saldo suficiente");
        } else {
            saldo = saldo - valorLevantamento;
            numTransacoes++;

            System.out.println("\n\n=== Depósito Levantamento com sucesso! ===");
            System.out.println("Novo saldo: " + saldo + "€");
            System.out.println("Transações: " + numTransacoes);
        }
    }
    static void verificarSaldo(){
        System.out.println("\n\n=== Resumo da Conta ===");
        System.out.println("Titular: " + nomeTitular);
        System.out.println("Saldo inicial: " + saldo + "€");
        System.out.println("Estado: " + estadoConta);
        System.out.println("Transações: " + numTransacoes);
    }
}
