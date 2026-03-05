import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Sistema_biblioteca {
    static Scanner scanner = new Scanner(System.in);
    static int opcaoMenu, totalLivros;

    static String [] ISBN = new String[100];
    static String [] tituloLivro = new String[100];
    static String [] autorLivro = new String[100];
    static int [] quantidadeLivros = new int[100]; // 10 para o numero maximo de livros diferenets que se pode guardar
    static int[] quantidadesDisponiveis = new int[100];

    //Variaveis para a funçao de emprestimo de livro

    static String [] emprestadoISBN = new String[100]; // ISBN livro emprestado
    static String [] emprestadoNomeLeitor = new String[100]; // leitor que pediu
    static String [] emprestadoNumeroLeitor = new String[100]; //numero de leitor do leitor
    static String [] emprestadoData = new String[100]; // data requisitada
    static String [] emprestadoDataDevolucao = new String[100]; // daat devoluçao
    static boolean[] emprestimoAtivo = new boolean[100]; // ativo ou inativo
    static int totalEmprestimo = 0; // == total de livros

    static void main() {
        System.out.println("=== Sistema de Biblioteca ===");
        do {
            System.out.println();
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Procurar Livros");
            System.out.println("3. Mostrar Todos os Livros");
            System.out.println("4. Emprestar Livro");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Ver Estatísticas");
            System.out.println("7. Sair\n");

            escolherOpcao();
        }while(!(opcaoMenu ==7));

        System.out.println("Obrigado por usar o sistema da biiblioteca!");
    }

    static void escolherOpcao(){
        System.out.print("opção(1-7): ");
        opcaoMenu = scanner.nextInt();
        System.out.println();

        switch (opcaoMenu){
            case 1:
                adicionarLivro();
                break;
            case 2:
                procurarLivros();
                break;
            case 3:
                mostrarTodosLivros();
                break;
            case 4:
                emprestarLivros();
                break;
            case 5:
                devolverLivro();
                break;
            case 6:
                estatisticasBiblioteca();
                break;
            case 7:
                break;
            default:
                System.out.println("Opçao invalida");
        }
    }

    static void adicionarLivro(){

        /*Logica, variavel totalLivros vai servir para armazenar os livros começando do 0 (nao existe nenhum)
        * quando se inserir um livro vai aumentar o total de livros passando para o proximo quando se preencher :D*/

        if(totalLivros>=10){ // verificar se tem espaço
            System.out.println("A biblioteca esta cheia!");
            return;
        }
        scanner.nextLine();//limpar buffer

        System.out.println("--- Adicionar Novo Livro ---");

        System.out.print("Digite o título: ");
        tituloLivro[totalLivros] = scanner.nextLine();

        System.out.print("Digite o autor: ");
        autorLivro[totalLivros] = scanner.nextLine();

        boolean validarISBN = false;
        while(!validarISBN){ // lopp while para verificar se o ISBN esta valido
            System.out.print("Digite o ISBN: ");
            ISBN[totalLivros] = scanner.nextLine();

            if (ISBN[totalLivros].length() != 10){ // ver se tem 10 digitos
                System.out.println("ISBN invalido, deve de conter apenas 10 numeros");
                continue;//Volta ao início do while
            }
            boolean ISBNnumeros = true; // para saber se é numero
            for (int i = 0; i < ISBN[totalLivros].length(); i++) {
                char c = ISBN[totalLivros].charAt(i);
                if (c < '0' || c > '9'){
                    ISBNnumeros = false;
                    break; //sai fora do loop for
                }
            }

            if (!ISBNnumeros){
                System.out.println("ISBN inválido! Deve conter apenas números.");
                continue;
            }

            boolean ISBNjaExiste = false; // verificar se esse numero ja existe no array
            for(int i = 0; i < totalLivros; i++){
                if(ISBN[i].equals(ISBN[totalLivros])){
                    ISBNjaExiste = true;
                    break;
                }
            }

            if(ISBNjaExiste){
                System.out.println("ISBN já existe! Cada livro deve ter ISBN único.");
                continue;  //Volta ao início do while
            }

            validarISBN = true;
        }

        System.out.print("Digite a quantidade: ");
        quantidadeLivros[totalLivros] = scanner.nextInt();
        quantidadesDisponiveis[totalLivros] = quantidadeLivros[totalLivros]; // para começar todas disponiveis

        System.out.println("\n=== Livro adicionado com sucesso! ===");
        System.out.println("Título: " + tituloLivro[totalLivros]);
        System.out.println("Autor: " + autorLivro[totalLivros]);
        System.out.println("ISBN: " + ISBN[totalLivros]);
        System.out.println("Quantidade disponível: " + quantidadeLivros[totalLivros]);

        totalLivros++;
    }

    static void procurarLivros(){
        System.out.println("--- Procurar Livros ---");
        System.out.println("1. Procurar por Título");
        System.out.println("2. Procurar por Autor");
        System.out.println("3. Procurar por ISBN");
        System.out.print("Tipo de pesquisa: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a pesquisa: ");
        String pesquisaLivros = scanner.nextLine();

        boolean encontrado = false;

        System.out.println("\n=== Resultados encontrados ===");

        switch (escolha){
            case 1:
                for (int i = 0; i < totalLivros; i++) {
                    if(tituloLivro[i].toLowerCase().contains(pesquisaLivros.toLowerCase())){
                        System.out.println((i+1)+". "+tituloLivro[i]);
                        System.out.println("Autor: " + autorLivro[i]);
                        System.out.println("ISBN: " + ISBN[i]);
                        System.out.println("Disponível: " + quantidadesDisponiveis[i]);
                        System.out.println("Estado: " + estadoLivro(i));
                        encontrado = true;
                    }
                }
                if(!encontrado){
                    System.out.println("Nenhum livro foi encontrado na biblioteca.");
                }
                break;
            case 2:
                for (int i = 0; i < totalLivros; i++) {
                    if(autorLivro[i].toLowerCase().contains(pesquisaLivros.toLowerCase())){
                        System.out.println(i+". "+tituloLivro[i]);
                        System.out.println("Autor: " + autorLivro[i]);
                        System.out.println("ISBN: " + ISBN[i]);
                        System.out.println("Disponível: " + quantidadesDisponiveis[i]);
                        System.out.println("Estado: " + estadoLivro(i));
                        encontrado = true;
                    }
                }
                if(!encontrado){
                    System.out.println("Nenhum livro foi encontrado na biblioteca.");
                }
                break;
            case 3:
                for (int i = 0; i < totalLivros; i++) {
                    if(ISBN[i].equals(pesquisaLivros)){
                        System.out.println(i+". "+tituloLivro[i]);
                        System.out.println("Autor: " + autorLivro[i]);
                        System.out.println("ISBN: " + ISBN[i]);
                        System.out.println("Disponível: " + quantidadesDisponiveis[i]);
                        System.out.println("Estado: " + estadoLivro(i));
                        encontrado = true;
                    }
                }
                if(!encontrado){
                    System.out.println("Nenhum livro foi encontrado na biblioteca.");
                }
                break;
            default:
                System.out.println("Opçao invalida! Digite de novo!");
        }
    }

    static String estadoLivro(int indice){
        int disponiveis = quantidadesDisponiveis[indice]; // compara a quantidade disponivel com a quantidade de livros inicial
        int total = quantidadeLivros[indice];

        if(disponiveis == total){
            return "Completamente disponível";
        } else if(disponiveis == 0){
            return "Indisponivel";
        } else {
            return "Disponivel";
        }
    }
    static void emprestarLivros(){
        scanner.nextLine();
        System.out.println("--- Empréstimo de Livro ---");

        System.out.print("Digite o ISBN do livro: ");
        boolean ISBNencontrado = false;
        int indiceLivro = 0;
        String econtrarISBN = scanner.nextLine();
        for (int i = 0; i < totalLivros; i++) {
            if(ISBN[i].equals(econtrarISBN)){
                ISBNencontrado = true;
                indiceLivro = i;
                break;
            }
        }
        if (!ISBNencontrado){
            System.out.println("Nenhum livro com esse ISBN encontrado!");
            return;
        }
        if(quantidadesDisponiveis[indiceLivro] == 0){
            System.out.println("Livro ja nao esta disponivel!");
            return;
        }

        System.out.print("Digite o nome do leitor: ");
        String nomeLeitor = scanner.nextLine();

        System.out.print("Digite o número de leitor (exemplo-L00001): ");
        String numeroLeitor = scanner.nextLine();
        emprestadoNomeLeitor[totalEmprestimo] = nomeLeitor;
        emprestadoNumeroLeitor[totalEmprestimo] = numeroLeitor;
        emprestadoISBN[totalEmprestimo] = ISBN[indiceLivro];
        emprestadoData[totalEmprestimo] = dataAtual();
        emprestadoDataDevolucao[totalEmprestimo] = dataDevolucao();
        emprestimoAtivo[totalEmprestimo] = true;

        quantidadesDisponiveis[indiceLivro]--; // retirar dos livros disponiveis

        if (ISBNencontrado){
            System.out.println("=== Empréstimo realizado com sucesso! ===");
            System.out.println("Livro: " + tituloLivro[indiceLivro]);
            System.out.println("Leitor: "+ emprestadoNomeLeitor[totalEmprestimo]);
            System.out.println("Data de empréstimo: " + emprestadoData[totalEmprestimo]);
            System.out.println("Data de devolução: " + emprestadoDataDevolucao[totalEmprestimo]);
            System.out.println("Cópias restantes: " + quantidadesDisponiveis[indiceLivro] +"/"+quantidadeLivros[indiceLivro]);
        }
        totalEmprestimo++;
    }

    static void devolverLivro(){
        if(totalEmprestimo == 0){ // se nao existir nehum livro emprestado
            System.out.println("Não há empréstimos registados.");
            return;
        }

        scanner.nextLine();

        System.out.println("--- Devolução de Livro ---");

        System.out.print("Digite o ISBN do livro: ");
        String devolverISBN = scanner.nextLine();

        System.out.print("Digite o número do leitor: ");
        String devolverNumeroLeitor = scanner.nextLine();

        int indiceEmprestimo = -1; //pq o array começa em 0 entao o valor de 0 existe enqaunto -1 nao existe e se nao existir um empretimo minimo da erro

        for (int i = 0; i < totalEmprestimo; i++) { // loop for para ver se no array de emprestimo existe as variaveis
            if (emprestadoISBN[i].equals(devolverISBN) && emprestadoNumeroLeitor[i].equals(devolverNumeroLeitor) && emprestimoAtivo[i]){
                indiceEmprestimo = i; //guarda a posiçao de i quando encontra
                break;
            }
        }
        if(indiceEmprestimo == -1){
            System.out.println("Livro emprestado nao encontrado");
            return;
        }

        int indiceLivro = -1; //usar a logica anterior
        for (int i = 0; i < totalLivros; i++) {
            if (ISBN[i].equals(devolverISBN)){
                indiceLivro = i;
            }
        }
        if(indiceLivro == -1){
            System.out.println("livro nao encontrado");
            return;
        }

        emprestimoAtivo[indiceEmprestimo] = false;
        quantidadesDisponiveis[indiceLivro]++;

        System.out.println("\n=== Devolução realizada com sucesso! ===");
        System.out.println("Livro: " + tituloLivro[indiceLivro]);
        System.out.println("Leitor: " + emprestadoNomeLeitor[indiceEmprestimo]);
        System.out.println("Data de devolução: " + dataAtual());
        System.out.println("Estado: No prazo");
        System.out.println("Cópias disponíveis: " + quantidadesDisponiveis[indiceLivro]);

    }

    static String dataAtual(){
        LocalDate atual = LocalDate.now(); // data atual do computador
        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // formataçao para data dia-mes-ano
        return atual.format(formatado);
    }

    static String dataDevolucao(){
        LocalDate atual = LocalDate.now();
        LocalDate devolucao = atual.plusDays(7); // adiciona 7 dias
        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return devolucao.format(formatado);
    }

    static int emprestimosAtivos(int indice){
        return quantidadeLivros[indice] - quantidadesDisponiveis[indice]; // calcula a diferença entre a quantidade de livros e os disponiveis
    }

    static void mostrarTodosLivros(){

        if (totalLivros == 0){ // nao existem livros
            System.out.println("Biblioteca vazia");
            return;
        }
        System.out.println("=== Lista Completa de Livros ===\n");

        for (int i = 0; i < totalLivros; i++) { //percorrer cada livro
            System.out.println("Título: " + tituloLivro[i]);
            System.out.println("Autor: " + autorLivro[i]);
            System.out.println("ISBN: " + ISBN[i]);
            System.out.println("Quantidade total: " + quantidadeLivros[i]);
            System.out.println("Disponível: " + quantidadesDisponiveis[i]);
            System.out.println("Estado: " + estadoLivro(i));
            System.out.println("Empréstimos ativos: " + emprestimosAtivos(i));
            System.out.println("\n---------------------------\n");
        }
    }

    static void estatisticasBiblioteca(){
        System.out.println("=== Estatísticas da Biblioteca ===");
        System.out.println("Total de livros cadastrados: " + totalLivros);
        int totalCopias = 0;
        for (int i = 0; i < totalLivros; i++) {
            totalCopias=+quantidadeLivros[i];
        }
        System.out.println("Total de cópias: " + totalCopias);
        System.out.println("Livros emprestados: " + totalEmprestimo);
        System.out.println("Livros disponíveis: " + (totalCopias-totalEmprestimo));
    }
}
