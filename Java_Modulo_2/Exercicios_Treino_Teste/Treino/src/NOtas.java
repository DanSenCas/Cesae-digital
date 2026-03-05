import java.util.ArrayList;
import java.util.Scanner;

//Programa que lê notas até o utilizador escrever -1, e depois mostra a média.

public class NOtas {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Integer> notas = new ArrayList<>();
    static int contagem = 0;
    static int media = 0;

    static void main(String[] args) {
        boolean ativo = true;

        do {
            System.out.println("Insira a notas que deseja, quando terminar insira -1.");
            notas.add(scanner.nextInt());
            contagem++;

            if(notas.get(contagem-1)<-1){
                System.out.println("Nao pode ser numeros a baixo do -1");
                continue;
            } else if (notas.contains(-1)) {
                ativo = false;
            }
        }while(ativo);

        media();

        System.out.println("A media dos numeros é: " + media);

    }

    static void media(){
        int soma = 0;

        for (int i = 0; i < notas.size(); i++) {
            if(notas.get(i)!=-1){
                soma = soma + notas.get(i);
            }
        }
        media = soma/(notas.size()-1);
    }
}
