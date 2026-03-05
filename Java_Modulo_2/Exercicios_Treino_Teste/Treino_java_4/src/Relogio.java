
/*
Relógio Digital
Atributos sugeridos:
horas
minutos
segundos

Funções obrigatórias:
tick() → avança um segundo corretamente
acertarHora(h, m, s) → define uma nova hora
mostrarHora() → mostra a hora atual

 */

import java.util.Scanner;

public class Relogio {
    //Atributos
    private int horas;
    private int minutos;
    private int segundos;

    //contrutor
    public Relogio(int horas, int minutos, int segundos){
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }


    //Metodos
    public Scanner scanner = new Scanner(System.in);
    void tick(){
        segundos++;
    }
    void acertarHora(){
        System.out.println("digite as horas:");
        horas = scanner.nextInt();
        System.out.println("digite os minutos");
        minutos = scanner.nextInt();
        System.out.println("digite os segundos");
        segundos = scanner.nextInt();

    }
    void mostraHora(){
        System.out.println("Relogio:");
        System.out.println(horas+ ":" + minutos + ":" + segundos);
    }

    static void main() {
        Relogio relogiojava = new Relogio( 12 , 30 , 20);
        relogiojava.mostraHora();
        relogiojava.tick();
        relogiojava.mostraHora();
        relogiojava.acertarHora();
        relogiojava.mostraHora();
    }
}
