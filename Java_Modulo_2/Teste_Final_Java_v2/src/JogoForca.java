import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class JogoForca implements JogoForcaInterface{
    // Atributos
    private String palavraSecreta;
    private char[] palavraRevelada;
    private String horaInicio; // Hora de inicio do jogo
    private List<Character> letrasTentadas;
    private int tentativasRestantes;
    private GestorPalavras gestorPalavras;
    private boolean jogoTerminado;
    private boolean jogadorVenceu;

    // Construtor

    public JogoForca(String palavraSecreta) {
        this.palavraSecreta = palavraSecreta.toUpperCase(); // Palavra secreta
        this.palavraRevelada = new char[palavraSecreta.length()]; // Array para revelar a palavra
        for (int i = 0; i < palavraRevelada.length; i++) { // pala colocar _ na palavra escondida
            palavraRevelada[i] = '_';
        }
        this.letrasTentadas = new ArrayList<>(); // Lista de letras tentadas
        this.tentativasRestantes = 6; // Numero inicial de tentativas
        this.horaInicio = ""; // Hora de inicio do jogo
        this.gestorPalavras = new GestorPalavras("palavras.txt"); // Classe para gerir palavras
        this.jogoTerminado = false;
        this.jogadorVenceu = false;
    }

    // Getters e Setters

    //Palavra secreta
    public String getPalavraSecreta (){
        return palavraSecreta;
    }
    public void setPalavraSecreta (String palavraSecreta) {
        this.palavraSecreta = palavraSecreta;
    }

    // Palavra revelada
    public char[] getPalavraRevelada() {
        return palavraRevelada;
    }
    public void setPalavraRevelada(char[] palavraRevelada) {
        this.palavraRevelada = palavraRevelada;
    }

    // Tentativas restantes
    public int getTentativasRestantes() {
        return tentativasRestantes;
    }
    public void setTentativasRestantes(int tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }

    // Hora inicio
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    // Letras tentadas
    public List<Character> getLetrasTentadas() {
        return letrasTentadas;
    }
    public void setLetrasTentadas(List<Character> letrasTentadas) {
        this.letrasTentadas = letrasTentadas;
    }

    // GestorPalavras
    public GestorPalavras getGestorPalavras() {
        return gestorPalavras;
    }
    public void setGestorPalavras(GestorPalavras gestorPalavras) {
        this.gestorPalavras = gestorPalavras;
    }

    // Jogo terminado
    public boolean isJogoTerminado() {
        return jogoTerminado;
    }
    public void setJogoTerminado(boolean jogoTerminado) {
        this.jogoTerminado = jogoTerminado;
    }
    // Jogador venceu
    public boolean isJogadorVenceu() {
        return jogadorVenceu;
    }
    public void setJogadorVenceu(boolean jogadorVenceu) {
        this.jogadorVenceu = jogadorVenceu;
    }

    // Implementacao dos metodos da interface (Funcionalidades)
    @Override
    public boolean inicializarJogo() {
        if (palavraSecreta == null || palavraSecreta.isEmpty()) {
            System.out.println("Erro: Palavra secreta vazia!");
            return false;
        }
        System.out.println("\n=== INICIANDO NOVO JOGO ===");
        System.out.println("Escolhendo palavra secreta...");
        System.out.println("Palavra secreta escolhida." + "(" + palavraSecreta.length()+ " letras)\n");
        System.out.println("Regras:\n" +
                "- Adivinhe a palavra letra por letra\n" +
                "- Você tem 6 tentativas erradas\n" +
                "- Resultado será guardado no ficheiro estatisticas.txt");
        System.out.println("Boa sorte!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Formato da hora
        horaInicio = LocalDateTime.now().format(formatter);

        return true;
    }

    @Override
    public void processarTentativa(char letra) {
        letra = Character.toUpperCase(letra); // Converte a letra para maiuscula

        if (letrasTentadas.contains(letra)) { // Verifica se a letra ja foi tentada
            System.out.println("Letra " + letra + " já foi tentada. Tente outra letra.");
            return;
        }

        letrasTentadas.add(letra); // Adiciona a letra a lista de letras tentadas

        if (verificarLetra(letra)) { // Se a letra estiver na palavra secreta
            System.out.println("Letra " + letra + " Existe na palavra.");
            revelarLetras(letra); // Revela as letras corretas na palavra revelada
        } else {
            tentativasRestantes--; // Diminui o numero de tentativas restantes
            System.out.println("Letra " + letra + " não existe na palavra.");
        }

    }

    @Override
    public boolean verificarLetra(char letra) {
        if (palavraSecreta.contains(letra + "")) { //transforma char em string e verifica se a letra esta na palavra secreta
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean verificarVitoria() {
        for (int i = 0; i < palavraSecreta.length(); i++) { // por cada letra da palavra sevcreta
            char letra = palavraSecreta.charAt(i);

            if (!letrasTentadas.contains(letra)) {
                return false;
            }

        }
        jogadorVenceu = true;
        jogoTerminado = true;
        return true;// se todas as letras estiverem na lista de letras retorna true
    }

    @Override
    public boolean verificarDerrota() {
        if (tentativasRestantes <= 0) {
            System.out.println("Você perdeu! A palavra secreta era: " + palavraSecreta);
            jogadorVenceu = false;
            jogoTerminado = true;
            return true;
        }
        return false;
    }
    @Override
    public int obterLetrasCorretas() {
        int count = 0;
        for (char letra : letrasTentadas){
            if (palavraSecreta.contains(letra+ "")){  // se a letra estiver na palavra secreta
                count++;
            }
        }
        return count;
    }

    @Override
    public int obterLetrasErradas() {
        int count = 0;
        for (char letra : letrasTentadas) {
            if (!palavraSecreta.contains(letra + "")) { // se a letra nao estiver na palavra secreta
                count++;
            }
        }
        return count;
    }

    @Override
    public void revelarLetras(char letra) {
        // Percorre a palavra secreta e revela todas as letras que já foram tentadas
        for (int i = 0; i < palavraSecreta.length(); i++) {
            char letraAtual = palavraSecreta.charAt(i);

            // Se a letra atual da palavra está na lista de tentadas, mostra
            if (letrasTentadas.contains(letraAtual)) {
                palavraRevelada[i] = letraAtual;
            }
        }
    }

    @Override
    public void mostrarEstado() {

        revelarLetras(' '); // atualiza a palavra revelada
        System.out.print("\nPalavra: ");
        for (char letra : palavraRevelada) { // por cada letra na palavra revelada
            System.out.print(letra + " "); // mostra a letra ou underline
        }
        System.out.println();

        System.out.print("Letras tentadas: ");
        for (char letra : letrasTentadas) { // por cada letra na lista de letras tentadas
            System.out.print(letra + " ");
        }
        System.out.println();

        System.out.println("Tentativas restantes: " + tentativasRestantes);

        mostrarForca();

    }

    @Override
    public void mostrarForca() {
        System.out.println();

        switch (6 - tentativasRestantes) {
            case 0:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\ |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\ |");
                System.out.println("/    |");
                System.out.println("     |");
                System.out.println("=========");
                break;
            case 6:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\ |");
                System.out.println("/ \\ |");
                System.out.println("     |");
                System.out.println("=========");
                break;
        }
    }

    public void guardarEstatisticas() {
        try {

            File pasta = new File("Estatisticas_Jogo");
            if (!pasta.exists()) {
                pasta.mkdir(); // Cria a pasta se nao existir
            }

            //dados
            String resultado;
            if (jogadorVenceu) {
                resultado = "VITORIA";
            } else {
                resultado = "DERROTA";
            }

            String linha = horaInicio + " - " + resultado+ " - " + palavraSecreta + "\n";

            FileWriter write = new FileWriter("Estatisticas_Jogo/estatisticas.txt", true);
            write.write(linha);
            write.close();
            System.out.println("Estatísticas guardadas com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao guardar estatísticas: " + e.getMessage()); // Mensagem de erro
        }

    }
}
