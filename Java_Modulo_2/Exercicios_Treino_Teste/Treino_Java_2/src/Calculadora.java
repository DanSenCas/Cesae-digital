import java.util.Scanner;

public class Calculadora implements CalculadoraInterface {

    //Atributos
    private int a;
    private int b;

    //construtor
    public Calculadora(){
        this.a = 0;
        this.b = 0;
    }
    //getters e setters


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    //Metodos
    public Scanner scanner = new Scanner(System.in);

    public void pergunta(){

        System.out.println("Bem vindo a calculadora java!");
        numeros();
        System.out.println("Qual a operaçao que deseja fazer?");
        System.out.println("1-Soma" +
                "\n2-subtrair" +
                "\n3-multiplicar" +
                "\n4-dividir");
        System.out.print("\nResposta: ");
        int resposta = scanner.nextInt();
        switch (resposta){
            case 1:
                System.out.println(soma(a,b));
                break;
            case 2:
                System.out.println(subtrair(a,b));
                break;
            case 3:
                System.out.println(multiplicar(a,b));
                break;
            case 4:
                System.out.println(dividir(a,b));
                break;
            default:
                System.out.println(resposta + " nao é uma opçao valida");
                break;
        }
    }

    public void numeros(){
        System.out.println("Indique os nuemros que quer fazer o calculo!");
        System.out.print("Primeiro numero: ");
        a = scanner.nextInt();
        System.out.print("Segundo numero: ");
        b = scanner.nextInt();
    }


    @Override
    public int soma(int a, int b) {
        int soma = a + b;
        return soma;
    }

    @Override
    public int subtrair(int a, int b) {
        int subtrair = a - b;
        return subtrair;
    }

    @Override
    public int multiplicar(int a, int b) {
        int multiplicar = a * b;
        return multiplicar;
    }

    @Override
    public double dividir(int a, int b) {
        int dividir = a/b;
        return dividir;
    }
}
