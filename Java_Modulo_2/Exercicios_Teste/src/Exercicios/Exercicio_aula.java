package Exercicios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Scanner;

public class Exercicio_aula {

    static void main(String[] args) throws IOException, InterruptedIOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o nome do ficheiro?");
        String nome = scanner.nextLine();

        File file = new File("ficheiros/" +nome + ".txt");
        if (file.exists()){
            System.out.println("O ficheiro ja existe");
        }else{
            if(file.createNewFile()){
                System.out.println("Ficheiro criado com sucesso");
            }else{
                System.out.println("houve um erro");
                return;
            }
        }

        FileWriter writer = new FileWriter(file);
        int linhas = -1;
        do{
            System.out.println("Quantas linhas prentendes escrever? (1 a 10)");
            linhas = scanner.nextInt();
        }while (0>linhas || linhas>10);

        for (int i = 0; i < linhas; i++) {
            System.out.println("O que pretendes escrever na linha?");
            String msg = scanner.nextLine();
            writer.write(msg + "\n");
        }
        writer.flush();
        writer.close();


    }
}
