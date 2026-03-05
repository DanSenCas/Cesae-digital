import java.util.Scanner;

public class ExecutaJogo implements ExecutaJogoInterface {
    // Atributos
    private Scanner scanner;
    private GestorPalavras gestorPalavras;
    private JogoForca jogoForca;
    private boolean continuar;

    // Construtor
    public ExecutaJogo() {
        this.scanner = new Scanner(System.in);
        this.gestorPalavras = new GestorPalavras("palavras.txt");
        this.jogoForca = null; // sera inicializado no iniciar()
        this.continuar = true;

    }

    // Implementacao dos metodos da interface (ExecutaJogoInterface)
    @Override
    public void executar() { // controla todas as funçoes
        iniciar();
        while (continuar) {
            mostrarMenuPrincipal();
        }

    }

    @Override
    public void iniciar() { // o que fazer no inicio
        System.out.println("======Jogo da Forca ======");
        System.out.println("Bem-Vindo ao Jogo da Forca!");
        gestorPalavras.carregarPalavras();
        System.out.println("Total de palavras: " + gestorPalavras.obterTotalPalavras());
        String palavra = gestorPalavras.escolherPalavraAleatoria();
        System.out.println("=========================================\n");
        gestorPalavras.validarFicheiro();
        jogoForca = new JogoForca(palavra);
    }

    @Override
    public void mostrarMenuPrincipal() { // o que mostrar no menu principal
        int opcao;
        System.out.println("Menu Principal:");
        System.out.println("1. Iniciar Novo Jogo");
        System.out.println("2. Ver Estatísticas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        processarOpcao(opcao);
    }

    @Override
    public void processarOpcao(int opcao) { // o que fazer com a opcao escolhida
        switch (opcao) {
            case 1:
                executarJogo();
                break;
            case 2:
                mostrarEstatisticas();
                break;
            case 3:
                continuar = false;
                System.out.println("Obrigado por jogar! Até a próxima.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

    }

    @Override
    public void executarJogo() { // o jogo
        jogoForca.inicializarJogo();
        do {
            jogoForca.mostrarEstado();
            System.out.print("Digite uma letra: ");
            char letra = scanner.next().toUpperCase().charAt(0);
            jogoForca.processarTentativa(letra);
            jogoForca.verificarVitoria();
            jogoForca.verificarDerrota();
        } while (!jogoForca.verificarVitoria() && !jogoForca.verificarDerrota());
        jogoForca.mostrarEstado();

        if (jogoForca.verificarVitoria()) {
            System.out.println("\nParabéns! Você ganhou!");
        } else {
            System.out.println("\nQue pena! Você perdeu!");
        }
        System.out.println("A palavra era: " + gestorPalavras.getPalavraAtual());
        System.out.println("\nFim de jogo!");
        System.out.println("=========================================");

    }

    @Override
    public void mostrarEstatisticas() {
        System.out.println("Estatísticas do Jogo:");
        System.out.println("Palavra : " + gestorPalavras.getPalavraAtual());
        System.out.println("Letras corretas: " + jogoForca.obterLetrasCorretas());
        System.out.println("Letras erradas: " + jogoForca.obterLetrasErradas());
        if(jogoForca.verificarVitoria()){
            System.out.println("Resultado: Vitória");
        } else {
            System.out.println("Resultado: Derrota");
        }
        System.out.println("Hora de inicio: " + jogoForca.getHoraInicio());
        System.out.println("Hora de fim: " + jogoForca.getHoraFim());
        System.out.println("=========================================");
        System.out.println("Resultado guardado em um ficheiro estatisticas.txt");

    }

    @Override
    public boolean validarEntrada(String entrada) {
        return false;
    }


}
