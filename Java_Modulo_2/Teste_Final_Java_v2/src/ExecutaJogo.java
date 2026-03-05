import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        System.out.println("\nEncerrando o jogo. Obrigado por jogar!");

        File ficheiro = new File("Estatisticas_Jogo/estatisticas.txt");
        if (ficheiro.exists()) { // Verifica se o ficheiro de estatísticas existe
            System.out.println("\nFicheiro de estatísticas encontrado. A apagar para o proximo jogo.");
            if (ficheiro.delete()) {
                System.out.println("\nFicheiro de estatísticas apagado com sucesso.");
            } else {
                System.out.println("Não foi possível apagar o ficheiro de estatísticas.");
            }
        }

        scanner.close(); // Fecha o scanner para evitar vazamento de recursos
    }

    @Override
    public void iniciar() { // o que fazer no inicio
        System.out.println("======Jogo da Forca ======");
        System.out.println("Bem-Vindo ao Jogo da Forca!!!");
        gestorPalavras.carregarPalavras();
        System.out.println("Total de palavras: " + gestorPalavras.obterTotalPalavras());
        String palavra = gestorPalavras.escolherPalavraAleatoria();
        if (palavra.isEmpty()) {
            System.out.println("Não foi possível iniciar o jogo. Verifique o ficheiro de palavras.");
            continuar = false;
            return;
        }
        System.out.println("=========================================\n");
        if (gestorPalavras.validarFicheiro()==false) {
            continuar = false;
            return;
        }
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
    public void executarJogo() {
        String palavra = gestorPalavras.escolherPalavraAleatoria(); // Escolhe nova palavra
        jogoForca = new JogoForca(palavra); // Reinicia o jogo com nova palavra

        jogoForca.inicializarJogo(); // Inicializa o jogo
        do {
            jogoForca.mostrarEstado();
            System.out.print("Digite uma letra: ");

            String entrada = scanner.nextLine().toUpperCase();

            if (!validarEntrada(entrada)) { // Valida o input do utilizador
                continue;
            }

            char letra = entrada.charAt(0);

            jogoForca.processarTentativa(letra); // Processa a tentativa do utilizador
            jogoForca.verificarVitoria();
            jogoForca.verificarDerrota();
        } while (!jogoForca.verificarVitoria() && !jogoForca.verificarDerrota());
        jogoForca.mostrarEstado();

        if (jogoForca.verificarVitoria()) {
            System.out.println("\nParabéns! Você ganhou!");
        } else {
            System.out.println("\nQue pena! Você perdeu!");
        }

        System.out.println("\nFim de jogo!");
        System.out.println("=========================================");

        String horaFim = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
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
        System.out.println("Hora de fim: " + horaFim);
        System.out.println("=========================================");
        // Guardar estatisticas em ficheiro
        jogoForca.guardarEstatisticas();
        System.out.println("Resultado guardado em um ficheiro estatisticas.txt");

    }

    @Override
    public void mostrarEstatisticas() {
        try {
            // Abrir ficheiro de estatisticas
            BufferedReader leitor = new BufferedReader(new FileReader("Estatisticas_Jogo/estatisticas.txt"));
            String linha;

            int numeroJogo = 1; // Contador de jogos para colocar numeros nas linhas
            int totalJogos = 0; // Contador total de jogos para calcular taxa de sucesso
            int totalVitorias = 0; // Contador total de vitorias para calcular taxa de sucesso
            int totalDerrotas = 0; // Contador total de derrotas para calcular taxa de sucesso

            System.out.println("═══════════════════════════════");
            System.out.println(" ESTATÍSTICAS DOS JOGOS");
            System.out.println("═══════════════════════════════");
            System.out.println("Lendo ficheiro estatisticas.txt...\n");
            System.out.println(" HISTÓRICO DE JOGOS:");

            while ((linha = leitor.readLine()) != null) { // Enquanto houver linhas para ler
                if (!linha.trim().isEmpty()) { // Se a linha nao estiver vazia
                    System.out.println(numeroJogo + ". " + linha); // Mostrar linha lida e numero do jogo

                    // Contar vitórias e derrotas
                    if (linha.contains("VITORIA")) {
                        totalVitorias++;
                    } else if (linha.contains("DERROTA")) {
                        totalDerrotas++;
                    }

                    totalJogos++;
                    numeroJogo++;
                }
            }
            leitor.close();

            // Calcular taxa de sucesso
            double taxaSucesso = 0.0;
            if (totalJogos > 0) {
                taxaSucesso = ((double) totalVitorias / totalJogos) * 100;
            }

            System.out.println("═══════════════════════════════");
            System.out.println(" RESUMO GERAL");
            System.out.println("═══════════════════════════════");

            System.out.println("Total de jogos: " + totalJogos);
            System.out.println("Vitórias: " + totalVitorias);
            System.out.println("Derrotas: " + totalDerrotas);
            System.out.println("Taxa de sucesso: " + String.format("%.2f", taxaSucesso) + "%");

            System.out.println("═══════════════════════════════\n");

        } catch (FileNotFoundException e) { // Ficheiro nao encontrado
            System.out.println("Ficheiro de estatísticas não encontrado.");
            System.out.println("Joga um jogo para começar a registar estatísticas!\n");
        } catch (Exception e) { // Outros erros, talvez se forem encontrados
            System.out.println("Erro ao ler ficheiro de estatísticas: " + e.getMessage());
        }
    }

    @Override
    public boolean validarEntrada(String entrada) {
        if (entrada != null && entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
            return true;
        }else if (entrada == null || entrada.isEmpty()) {
            System.out.println("Entrada vazia. Por favor, digite uma letra.");
            return false;
        } else if (entrada.length()>1) {
            System.out.println("Entrada inválida. Por favor, digite apenas uma letra.");
            return false;
        } else {
            System.out.println("Entrada inválida.");
            return false;
        }
    }


}
