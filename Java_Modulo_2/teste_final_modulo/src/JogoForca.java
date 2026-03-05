import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class JogoForca implements JogoForcaInterface{
    // Atributos
    private String palavraSecreta;
    private List<Character> letrasCorretas;
    private List<Character> letrasErradas;
    private int tentativasRestantes;
    private String palavraReveleada;
    private int horaInicio; // Hora de inicio do jogo
    private int horaFim; // Hora de fim do jogo

    // Construtor

    public JogoForca(String palavraSecreta) {
        this.palavraSecreta = palavraSecreta;
        this.letrasCorretas = new ArrayList<>();
        this.letrasErradas = new ArrayList<>();
        this.tentativasRestantes = 6; // Numero inicial de tentativas
        this.palavraReveleada = "_".repeat(palavraSecreta.length()); // faz _ pelo tamanho da palavra
        this.horaInicio = 0;
        this.horaFim = 0;
    }

    // Implementacao dos metodos da interface (Funcionalidades)
    @Override
    public boolean inicializarJogo() {
        System.out.println("\n=== INICIANDO NOVO JOGO ===");
        System.out.println("Escolhendo palavra secreta...");
        System.out.println("Palavra secreta escolhida." + "(" + palavraSecreta.length()+ " letras)\n");
        System.out.println("Regras:\n" +
                "- Adivinhe a palavra letra por letra\n" +
                "- Você tem 6 tentativas erradas\n" +
                "- Resultado será guardado no ficheiro estatisticas.txt");
        System.out.println("Boa sorte!");
        horaInicio = LocalDateTime.now().getHour(); // Registra a hora de inicio

        return false;
    }

    @Override
    public void processarTentativa(char letra) {
        if (verificarLetra(letra)) { // se o verificarLetra retornar true
            letrasCorretas.add(letra); // adicionar letra à lista de letras corretas
        } else {
            letrasErradas.add(letra);// adicionar letra à lista de letras erradas
            tentativasRestantes--; // diminuir o numero de tentativas restantes
            System.out.println("Tentativas restantes: " + tentativasRestantes);
        }

    }

    @Override
    public boolean verificarLetra(char letra) {
        if (palavraSecreta.contains(letra + "")) { //verifica se a letra está na palavra secreta
            System.out.println("Letra " + letra + " está na palavra secreta.");
            return true;
        }else {
            System.out.println("Letra " + letra + " NÃO está na palavra secreta.");
            return false;
        }
    }

    @Override
    public boolean verificarVitoria() {
        for (int i = 0; i < palavraSecreta.length(); i++) { // por cada letra da palavra sevcreta
            char letra = palavraSecreta.charAt(i);

            if (!letrasCorretas.contains(letra)) { // verifica se a letra nao esta na lista de letras corretas
                return false; // se alguma letra nao estiver na lista de letras corretas, retorna falso
            }

        }
        horaFim = LocalDateTime.now().getHour();
        return true;// se todas as letras estiverem na lista de letras corretas, retorna true
    }

    @Override
    public boolean verificarDerrota() {
        if (tentativasRestantes <= 0) {
            System.out.println("Você perdeu! A palavra secreta era: " + palavraSecreta);
            return true;
        }
        horaFim = LocalDateTime.now().getHour(); // Registra a hora de fim
        return false;
    }

    @Override
    public void mostrarEstado() {

        revelarLetras(' '); // atualiza a palavra revelada
        System.out.println("\nPalavra: " + palavraReveleada);
        System.out.print("Letras corretas: ");
        for (char letra : letrasCorretas) { // para cada letra na lista
            System.out.print(letra + " "); // imprime a letra e espaço
        }
        System.out.println();

        System.out.print("Letras erradas: ");
        for (char letra : letrasErradas) {
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

    @Override
    public void revelarLetras(char letra) {
        String palavraEscondida = ""; // String para construir a palavra com letras reveladas

        for (int i = 0; i < palavraSecreta.length(); i++) {
            char letraAtual = palavraSecreta.charAt(i);

            if (letraAtual == letra) { // Se a letra atual é a letra a ser revelada
                palavraEscondida += letraAtual + " ";
            }
            else if (letrasCorretas.contains(letraAtual)) { // Se a letra atual já foi revelada, mostra
                palavraEscondida += letraAtual + " ";
            }
            else {
                palavraEscondida += "_ "; // Mantém escondida
            }
        }

        this.palavraReveleada = palavraEscondida;
    }

    public void guardarEstatisticas() {
        EstatisticasJogo estatisticas = new EstatisticasJogo();
        estatisticas.registarJogo(this);
    }

    @Override
    public int obterLetrasCorretas() {
        return letrasCorretas.size();
    }

    @Override
    public int obterLetrasErradas() {
        return letrasErradas.size();
    }

    // Getters e Setters

    // Getter da palavra secreta
    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    // Setter da palavra secreta
    public void setPalavraSecreta(String palavraSecreta) {
        this.palavraSecreta = palavraSecreta;
    }

    // Getter das tentativas restantes
    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    // Setter das tentativas restantes
    public void setTentativasRestantes(int tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }

    // Getter da palavra revelada
    public String getPalavraReveleada() {
        return palavraReveleada;
    }

    // Setter da palavra revelada
    public void setPalavraReveleada(String palavraReveleada) {
        this.palavraReveleada = palavraReveleada;
    }

    // Getter da lista de letras corretas
    public List<Character> getLetrasCorretas() {
        return letrasCorretas;
    }

    // Getter da lista de letras erradas
    public List<Character> getLetrasErradas() {
        return letrasErradas;
    }

    // Getters para as horas
    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(int horaFim) {
        this.horaFim = horaFim;
    }

}
