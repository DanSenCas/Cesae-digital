import java.util.Scanner;

public class Gestor_Lista_Compras {
    static Scanner scanner = new Scanner(System.in);
    static String[][] listaItens;
    static int numItens = 0;

    static void main(String[] args) {
        boolean ativo = true;
        System.out.println("=== Gestor de Lista de Compras ===");

        while(ativo){
            System.out.print("Quantos itens desejas adicionar? (1-10): ");
            int novosItens = scanner.nextInt();
            scanner.nextLine();

            int intensAntigos; //itens que ja estava na lista

            if (listaItens == null){ // se o array estiver vazio entao é a priemira vez
                numItens = novosItens; // os novos intes tranformas so em itens
                listaItens = new String[numItens][2];// criar tamanho do array, numItens input user e 2 para nome e preço
                intensAntigos = 0;
            }else { // se ja existe
                intensAntigos = numItens; // contem agora os numeros antigos

                String[][] novoArray = new String[numItens + novosItens][2];

                // Copiar itens antigos
                for(int i = 0; i < numItens; i++){ // vai copiar os do array antigo para o novo
                    novoArray[i][0] = listaItens[i][0];
                    novoArray[i][1] = listaItens[i][1];
                }

                listaItens = novoArray;  // Substituir pelo novo
                numItens = numItens + novosItens;  // Atualizar total de itens ou seja aumentar o tamanho do array
            }

            escreverItens(intensAntigos);
            mostrarLista();
            System.out.println("---------------");
            System.out.println("Subtotal: " + subtotal() + "€");
            System.out.println("IVA (23%): " + iva() + "€");
            System.out.println("Total: " + (subtotal()+iva()) + "€");

            System.out.println("Desejas adicionar mais itens? (S/N): ");
            String verificacao = scanner.nextLine();
            char resposta = verificacao.charAt(0);
            if (resposta == 'N' || resposta == 'n'){
                ativo = false;
            }
        }
    }

    static double iva(){
        double iva = subtotal() * 0.23;
        return iva;
    }

    static double subtotal(){
        double soma = 0;
        for (int i = 0; i < numItens; i++) {
            soma = soma + Double.parseDouble(listaItens[i][1]); // converter a string em um double
        }
        return soma;
    }

    static void mostrarLista(){ // montar uma lista
        System.out.println("\n=== Lista ===");
        for(int i = 0; i < numItens; i++){ //for loop
            System.out.println((i+1)+ ". " + listaItens[i][0] + " ........ " + listaItens[i][1] + "€");
        }
    }

    static void escreverItens(int intensAntigos){ // criar a lista de Itens
        int contador = intensAntigos + 1;

        while (contador<=numItens){
            System.out.println("--- Item " + contador + " ---");
            System.out.print("Nome do item: ");
            listaItens[contador-1][0]= scanner.nextLine();
            System.out.print("Preço: ");
            listaItens[contador-1][1]=scanner.nextLine();
            contador++;

            /* Logica deste array
            * contador começa no 1 para estar escrito no output para o user entao tira se um para começar no 0
            * listItens[contador-1] vai representar o numero de itens que vai se ter
            * listItens[contador-1][0]/[1] vai representar o nome e o preço nesta logica
            *            [0]        [1]
                        (Nome)    (Preço)
                   [0] →"arroz"     "2.5"
                   [1] →"massa"     "1.8"
                   [2] →"leite"     "0.9"*/
        }
    }
}
