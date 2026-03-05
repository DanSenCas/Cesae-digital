
public interface JogoForcaInterface {
    boolean inicializarJogo();
    void processarTentativa(char letra);
    boolean verificarLetra(char letra) ;
    boolean verificarVitoria() ;
    boolean verificarDerrota() ;
    void mostrarEstado() ;
    void mostrarForca() ;
    void revelarLetras(char letra);
    int obterLetrasCorretas() ;
    int obterLetrasErradas() ;

}
