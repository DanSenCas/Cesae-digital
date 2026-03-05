package src.Aulas.Modulo3POO.Aula8_JFrames;

import javax.swing.*;

class Jframe_simples {

    public static void main(String[] args) {
        // Criar a janela
        JFrame janela = new JFrame("Minha Primeira Janela");

        // Configurações básicas
        janela.setSize(400, 300);                    // Tamanho (largura, altura)
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar aplicação ao fechar janela
        janela.setLocationRelativeTo(null);          // Centralizar na tela
        janela.setResizable(false);                  // Não redimensionável

        // Tornar visível
        janela.setVisible(true);


    }


}
