import java.util.List;
import java.util.ArrayList;
import java.io.File; // para verificar se ficheiro existe
import java.io.FileReader; // abre ficheiros para ler
import java.io.BufferedReader; // ler ficheiros linha a linha
import java.io.IOException; // tratar erros de IO

public class GestorPalavras implements GestorPalavrasInterface{
    // Atributos
    private List<String> palavras; // Lista de palavras
    private String ficheiroPalavras; // Nome do ficheiro de palavras
    private String palavraAtual; // Palavra atual do jogo

    //Construtor
    public GestorPalavras(String ficheiroPalavras) { // Recebe o nome do ficheiro de palavras
        this.ficheiroPalavras = ficheiroPalavras; // Nome do ficheiro de palavras
        this.palavras = new ArrayList<>(); // Cria lista vazia de palavras
        this.palavraAtual = ""; // comeca sem palavra nenhuma
    }

    // Implementacao dos metodos da interface (Funcionalidades)

    @Override
    public boolean carregarPalavras() {
        System.out.println("A carregar palavras do ficheiro");

        try{ // tenta fazer isto
            //Abrir ficheiro para ler
            BufferedReader leitor = new BufferedReader(new FileReader(ficheiroPalavras));
            String linha;

            //Enquanto houver linhas para ler
            while ((linha = leitor.readLine()) != null) {
                // Se a linha nao estiver vazia
                if (!linha.trim().isEmpty()) {
                    palavras.add(linha.trim()); // Adiciona a palavra a lista
                }
            }
            leitor.close(); // Fecha o ficheiro

            System.out.println("Carregadas " + palavras.size() + " palavras do ficheiro.");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String escolherPalavraAleatoria() {
        if (palavras.isEmpty()){
            return "";
        }
        // Escolhe uma palavra aleatoria da lista
        int indice = (int) (Math.random() * palavras.size());
        palavraAtual = palavras.get(indice);
        return palavraAtual;
    }

    @Override
    public int obterTotalPalavras() {
        return palavras.size();
    }

    @Override
    public boolean validarFicheiro() {
        File arquivo = new File(ficheiroPalavras); // Cria Objecto com o nome do ficheiro
        if(!arquivo.exists()) { // usa a funçao existe para verificar se o ficheiro existe
            System.out.println("Ficheiro de palavras nao existe.");
            return false;
        } else if (arquivo.length() == 1 ) {
            System.out.println("Ficheiro de palavras so tem uma letra.");
            return false;
        }
        return true;
    }

    // Getters e Setters
    public String getPalavraAtual() {
        return palavraAtual;
    }

    public void setPalavraAtual(String palavraAtual) {
        this.palavraAtual = palavraAtual;
    }

    public List<String> getPalavras() {
        return palavras;
    }

    public String getFicheiroPalavras() {
        return ficheiroPalavras;
    }

    public void setFicheiroPalavras(String ficheiroPalavras) {
        this.ficheiroPalavras = ficheiroPalavras;
    }
}
