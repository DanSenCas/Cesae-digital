package exercicios;

public class Soma_Array {
    static void main() {
        int[] num = {13, 7, 22, 3, 18, 9};
        //double[] num2 = {6.5, 7, -12.4, 0, -48};

        int soma = 0;

        for (int i = 0; i < num.length; i++){
            soma = soma + num[i];
        }

        System.out.println(soma);
    }
}
