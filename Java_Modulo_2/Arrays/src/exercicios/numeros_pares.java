package exercicios;

public class numeros_pares {
    static void main() {

        //int[] num = {13, 7, 22, 3, 18, 9};
        double[] num2 = {6.5, 7, -12.4, 0, -48};

        double counter = 0;

        for (int i = 0; i < num2.length; i++) {
            if (num2[i] % 2 == 0){
                counter++;
            }
        }

        System.out.println(counter);


    }
}
