public interface GestorTarefasInterface {
    String adicionarTarefa(String tarefa);
    int removerTarefa (int indice);
    void mostrarTarefas();
    void guardarTarefa();
    void carregarTarefa();
}
