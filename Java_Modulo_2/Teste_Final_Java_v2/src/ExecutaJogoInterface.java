
public interface ExecutaJogoInterface {

    void executar();
    void iniciar();
    void mostrarMenuPrincipal();
    void processarOpcao(int opcao);
    void executarJogo();
    void mostrarEstatisticas();
    boolean validarEntrada(String entrada) ;
}
