package Projecto;

import java.util.Scanner;

public class Sudoku2 {
    static Scanner scanner = new Scanner(System.in);
    static void main() {

        boolean ativo = true; // para dizer se o programa esta ativo ou nao

        int[][] grelha = gerarGrelha(); // criei uma funçao mais a frente para gerar a tabela de sudoku e esta para guardar a tabela modificada

        while(ativo){

            System.out.println();

            imprimirGrelha(grelha); // no inicio do while para estar sempre a imprimir

            System.out.println();
            System.out.println("MENU:");
            System.out.println();

            System.out.println("0 - Sair \n" +
                    "1 – Aplicar permutacao de dois numeros\n" +
                    "2 – Aplicar permutacao de duas linhas de uma mesma faixa horizontal\n" +
                    "3 – Aplicar permutacao de duas colunas de uma mesma faixa vertical\n" +
                    "4 – Aplicar permutacao de duas faixas horizontais\n" +
                    "5 – Aplicar permutacao de duas faixas verticais\n" +
                    "6 – Aplicar reflexao horizontal \n"+
                    "7 – Aplicar reflexao vertical \n"+
                    "8 – Indicar quadricula  \n");

            System.out.println("\nEscola uma das opçoes a cima:");

            String escolharaw = scanner.nextLine();

            char escolha = escolharaw.charAt(0); // para ignorar o que estiver a frente . ) ou qualuqer outra coisa

            switch (escolha) {
                case '0':
                    System.out.println("\n Programa encerrado \n");
                    ativo = false;
                    break;
                case '1':
                    permutarDoisNum(grelha);
                    break;
                case '2':
                    permaturarFlinhas(grelha);
                    break;
                case '3':
                    permaturarFcolunas(grelha);
                    break;
                case '4':
                    permaturarFaixaHorizontal(grelha);
                    break;
                case '5':
                    permaturarFaixaVertical(grelha);
                    break;
                case '6':
                    reflexaoHorizontal(grelha);
                    break;
                case '7':
                    reflexaoVertical(grelha);
                    break;
                case '8':
                    int[][] nova = lerGrelha();

                    if (validacaoCompleta(nova)) {
                        grelha = nova;
                        System.out.println("Nova grelha aceite");
                    } else {
                        System.out.println("Grelha inválida");
                    }
                    break;

                default:
                    System.out.println("opçao invalida");
            }
        }
    }

    static int[][] gerarGrelha(){
        int[][] grelha = new int[9][9]; // Reservar espaço na memoria para um 9 por 9

        for (int i = 0; i < 9; i++) {
            for (int j = 0 ; j < 9 ; j++) { // percorrer o 9x9
                grelha[i][j]=(i/3+3*(i%3)+j)%9+1; // GQ(i+1)(i+j) é a formla matematica para começar no (1,1) pq nao existe me matematica (0,0)
            }
        }
        return grelha;
    }

    static void imprimirGrelha(int[][] grelha){

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0){ // se for divisivel por 3 coloca ---
                System.out.println("-----------------------");
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0){ // se divisivel por 3 coloca
                    System.out.print("| ");
                }
                System.out.print(grelha[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------");
    }

    static void permutarDoisNum(int[][] grelha){
    //Definir os numeros random
        int n1 = (int)(Math.random() * 9) + 1; // ate ao 9 e min de 1
        int n2 = (int)(Math.random() * 9) + 1;

        // garantir que sao diferenets se nao acaba por ser igual
        while (n2 == n1){
            n2 = (int)(Math.random() * 9) + 1;
        }

        // fazer a troca de numeros de duas posiçoes nao fisicas
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9 ; j++) {
                if (grelha[i][j] == n1){
                    grelha[i][j] = n2;
                } else if (grelha[i][j] == n2) {
                    grelha[i][j] = n1;
                }
            }
        }

        System.out.println("Priemiro numero: " + n1 + "\nSegundo numero: " + n2);
    }

    static void permaturarFlinhas(int[][] grelha){
        System.out.println();
        System.out.println("indica a primeira linha que se quer trocar (1 a 9)");
        int l1 = scanner.nextInt();
        System.out.println("indica a segunda linha que se quer trocar (1 a 9)");
        int l2 = scanner.nextInt();

        scanner.nextLine(); // limpar

        if (l1 < 1 || l1 > 9 || l2 < 1 || l2 > 9){
            System.out.println("Linhas invalidas. tem que estar entre 1 e 9.");
            return;
        }
        if (l1 == l2){
            System.out.println("As linhas tem de ser diferentes.");
            return;
        }

        int linha1 = l1-1; // para retirar um do numero recebido para ler entre 0 a 8
        int linha2 = l2-1;

        if (linha1 / 3 != linha2 / 3){
            // a divisao serve para ver se estao na mesma faixa pq 0/3 = 0 mas 3/3 = 1 e 6/3=2
            System.out.println("Nao estao na mesma faixa");
            return;
        }

        //Usar um sistema de swap que guarda a variavel em uma temporaria para nao perder o valor
        int[] temp = grelha[linha1];
        grelha[linha1] = grelha[linha2];
        grelha[linha2] = temp;

        System.out.println("Linhas " + l1 + " e "+ l2 + " trocadas");
    }
    static void permaturarFcolunas(int[][] grelha){
        // usar a mesma base que a funçao anterior so mudar apra colunas

        System.out.println();
        System.out.println("indica a primeira coluna que se quer trocar (1 a 9)");
        int c1 = scanner.nextInt();
        System.out.println("indica a segunda coluna que se quer trocar (1 a 9)");
        int c2 = scanner.nextInt();

        scanner.nextLine(); // limpar

        if (c1 < 1 || c1 > 9 || c2 < 1 || c2 > 9){
            System.out.println("Linhas invalidas. tem que estar entre 1 e 9.");
            return;
        }
        if (c1 == c2){
            System.out.println("As linhas tem de ser diferentes.");
            return;
        }

        int coluna1 = c1-1; // para retirar um do numero recebido para ler entre 0 a 8
        int coluna2 = c2-1;

        if (coluna1 / 3 != coluna2 / 3){
            // a divisao serve para ver se estao na mesma faixa pq 0/3 = 0 mas 3/3 = 1 e 6/3=2
            System.out.println("Nao estao na mesma faixa");
            return;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9 ; j++) {
                if (grelha[i][j] == grelha[i][coluna1] ){ // usar a mesma logica do swap mas para os numeros individuais por isso o loop for

                    int temp = grelha[i][coluna1];
                    grelha[i][coluna1] = grelha[i][coluna2];
                    grelha[i][coluna2] = temp;

                }
            }
        }
        System.out.println("Linhas " + c1 + " e "+ c2 + " trocadas");
    }
    static void permaturarFaixaHorizontal(int[][] grelha){

        System.out.println();
        System.out.println("indica a primeira faixa que se quer trocar (1 a 3)");
        int f1 = scanner.nextInt();
        System.out.println("indica a segunda faixa que se quer trocar (1 a 3)");
        int f2 = scanner.nextInt();

        scanner.nextLine(); // limpar

        if (f1 < 1 || f1 > 3 || f2 < 1 || f2 > 3){
            System.out.println("Faixas invalidas, tem que estar entre 1 e 3");
            return;
        }
        if (f1 == f2){
            System.out.println("As faixas tem de ser diferentes");
            return;
        }

        int faixa1 = f1-1; // para retirar um do numero recebido para ler entre 0 a 8
        int faixa2 = f2-1;

        int comeco1 = faixa1 * 3; // esta a identificar o começo da faixa
        int comeco2 = faixa2 * 3; // esta a identificar o começo da faixa

        /* porque a escolha de faixas ocorre entre 1 e 3 que vai se subtrair para ficar em lingugem java
        e fica entre 0 e 2
        se multiplicar por 3 o numero de linhas de cada faixa vai se obter o primeiro numero de cada neste caso 0, 3 ou 6
         */

        for (int i = 0; i < 3; i++) { // 3 pq é o numero de linhas que se tem que trocar de cada faixa
            int[] temp = grelha[ comeco1+ i];
            grelha[comeco1 + i] = grelha[comeco2 + i];
            grelha[comeco2 + i] = temp;

            /*
            decobrir onde começa exemplo f1 e f3 passariam a ser f0 e f2 que sao (0,1,2) e (6,7,8)
            entao na parte de cima descobre o começo 0 e 6
            depois troca um de cada vez itinerando mais um no começo sempre que encrementa o i
             */
        }
        System.out.println("Faixas " + f1 + " e " + f2 + " trocadas");
    }
    static void permaturarFaixaVertical(int[][] grelha){

        System.out.println();
        System.out.println("indica a primeira faixa que se quer trocar (1 a 3)");
        int f1 = scanner.nextInt();
        System.out.println("indica a segunda faixa que se quer trocar (1 a 3)");
        int f2 = scanner.nextInt();

        scanner.nextLine(); // limpar

        if (f1 < 1 || f1 > 3 || f2 < 1 || f2 > 3){
            System.out.println("Faixas invalidas, tem que estar entre 1 e 3");
            return;
        }
        if (f1 == f2){
            System.out.println("As faixas tem de ser diferentes");
            return;
        }

        int faixa1 = f1-1; // para retirar um do numero recebido para ler entre 0 a 8
        int faixa2 = f2-1;

        int comeco1 = faixa1 * 3; // esta a identificar o começo da faixa
        int comeco2 = faixa2 * 3; // esta a identificar o começo da faixa

        for (int i = 0; i < 9; i++) { // para percorrer todas as linhas
            for (int j = 0; j < 3; j++) { // mas como a logica do anterior so serve para trocar as faixas
                int temp = grelha[i][comeco1 + j];
                grelha[i][comeco1 + j] = grelha[i][comeco2 + j];
                grelha[i][comeco2 + j] = temp;
            }
        }
    }
    static void reflexaoHorizontal(int[][] grelha){
        int last = 8;
        for (int i = 0; i < 4; i++) { // percorre so metade do array
            int[] temp = grelha[last-i];
            grelha[last-i] = grelha[i];
            grelha[i] = temp;

        }
    }
    static void reflexaoVertical(int[][] grelha){
        int last = 8;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = grelha[i][last-j];
                grelha[i][last-j] = grelha[i][j];
                grelha[i][j] = temp;
            }
        }
    }
    static int[][] lerGrelha(){

        int[][] novaGrelha = new int[9][9]; // como se fez para gerar

        System.out.println("Introduz a quadrícula (9 linhas com 9 números):");

        for (int i = 0; i < 9; i++) { // usar um loop for para percorer a grelha e guardar os numeros
            for (int j = 0; j < 9; j++) {
                novaGrelha[i][j] = scanner.nextInt();
            }
        }
        scanner.nextLine();//limpar
        return novaGrelha;
    }
    static boolean vetorValido(int[] v){ // sudoku é varios vetores, entao esta funçao serve para verificar um vetor

        boolean[] visto = new boolean[10]; // indices 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        // Array atual do boolean {F F F F F F F F}
        int soma = 0;
        int produto = 1;
        for (int i = 0; i < 9; i++) {
            int numeroAtual = v[i];

            if (numeroAtual < 1 || numeroAtual > 9 ){ // verifica se o numero esta dentro de 1 e 9
                return  false; // termina a funçao aqui
            }
            if (visto[numeroAtual]){ // verifica se este numero ja pareceu antes
                return false; // termina a funçao aqui
            }
            visto[numeroAtual] = true;// se nada do anterior se verificar ele vai trocar o valor um por um para T
            soma += v[i];
            produto *= v[i];

        }
        if (soma != 45 || produto != 362880) {
            return false;
        }
        return true; // se nada do anterior se confirmar entao o vetor esta valido
    }
    static boolean linhasValidas(int[][] grelha) { // verificar linha a linha

        for (int i = 0; i < 9; i++) {
            if (!vetorValido(grelha[i])) {
                return false; // vai verificar cada linha de i e ver se o vetor é veridico
            }
        }
        return true; // se todos os vetores forem verdade entao vai sair do for e retornar true
    }
    static  boolean colunasValido(int[][] grelha){ // verificar as colunas
        for (int col = 0; col < 9; col++) {
            int[] vetorColuna = new int[9];
            for (int lin = 0; lin < 9; lin++) { // usa o for loop para percorrer de cima para baixo
                vetorColuna[lin] = grelha[lin][col]; // guardar os valores da coluna em um array
            }
            if(!vetorValido(vetorColuna)){
                return false;
            }
        }
        return true;
    }
    static boolean blocosValidos(int[][] grelha){
        /*
        o que é um bloco
        (0,0)(0,1)(0,2)
        (1,0)(1,1)(1,2)
        (2,0)(2,1)(2,2)

        por isso o primeiro loop for for vai precorrer os blocos indo linha inicial e coluna inicial (0,0)
         */

        for (int bL = 0; bL < 3; bL++) { // bL vai representar os blocos em linha
            for (int bC = 0; bC < 3; bC++) { //bC vai representar blocos em coluna
                int[] bloco = new int[9]; // criar espaço no array para o bloco
                int indice = 0; // dar um indice ao array

                for (int i = 0; i < 3; i++) { // desloca na vertical
                    for (int j = 0; j < 3; j++) { // desloca na horizontal
                        bloco[indice] = grelha[bL * 3 + i][bC * 3 + j]; // o bl * 3 bai ao bloco inicial como no exercicio anterior e adiciona o i ou o j para andar pelo array
                        indice++;
                    }
                }

                if(!vetorValido(bloco)){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean validacaoCompleta(int[][] grelha){
        return linhasValidas(grelha) && colunasValido(grelha) && blocosValidos(grelha); //vai chamar todas as funçoes de verificaçao
    }
}



/*
5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9

este vai estar correcto


5 3 5 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
este vai estar errado
 */