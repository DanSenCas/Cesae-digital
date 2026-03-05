import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestouTarefas implements GestorTarefasInterface {
    //Atributos
    private String tarefa;
    private int indice;
    private ArrayList<String> listaTarefas;
    private int opçao;

    //Contrutor
    public GestouTarefas(){
        this.tarefa="";
        this.indice=0;
        this.listaTarefas = new ArrayList<>();
        this.opçao=0;
    }
    
    //getters e setters


    public ArrayList<String> getListaTarefas() {
        return listaTarefas;
    }

    public int getIndice() {
        return indice;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setListaTarefas(ArrayList<String> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    //Metodos
    public Scanner scanner = new Scanner(System.in);
    public void menu(){
        carregarTarefa();
        do {
            System.out.println("menu:" +
                    "\n1. Adicionar tarefa" +
                    "\n2. Remover tarefa" +
                    "\n3. Mostrar tarefas" +
                    "\n4. Sair\n");
            System.out.print("Opçao: ");
            opçao = scanner.nextInt();
            switch (opçao){
                case 1:
                    adicionarTarefa(tarefa);
                    break;
                case 2:
                    removerTarefa(indice);
                    break;
                case 3:
                    mostrarTarefas();
                    break;
                case 4:
                    System.out.println("Obrigado por usar");
                    guardarTarefa();
                    break;
                default:
                    System.out.println("Opçao nao valida");
            }
        } while (opçao != 4);
    }

    @Override
    public String adicionarTarefa(String tarefa) {
        System.out.println("Indique quantas tarefas quer adicionar");
        int numeroTarefas = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numeroTarefas; i++) {
            System.out.print("Tarefa " + (i + 1) + ": ");
            listaTarefas.add(scanner.nextLine());
            System.out.println();
        }
        return "";
    }

    @Override
    public int removerTarefa(int indice) {
        System.out.println("Diz o numero da tarefa que queres retirar");
        indice = scanner.nextInt() - 1;
        listaTarefas.remove(indice);
        return indice;
    }

    @Override
    public void mostrarTarefas() {
        System.out.println("Lista tarefas");
        for (int i = 0; i < listaTarefas.size(); i++) {
            System.out.println("Tarefa " + (i + 1) + ": " + listaTarefas.get(i));
        }
    }

    @Override
    public void guardarTarefa() {
        try{
            File ficheiroTarefa = new File("tarefas.txt");
            if (ficheiroTarefa.createNewFile()){
                System.out.println("File created");
            }

        }catch (IOException e){
            System.out.println("Erro");
            e.getStackTrace();
        }
    }

    @Override
    public void carregarTarefa() {
        try {
            BufferedReader lerTarefas = new BufferedReader(new FileReader("tarefas.txt"));
            String line = lerTarefas.readLine();

            while (line != null){
                listaTarefas.add(line);
                line = lerTarefas.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
