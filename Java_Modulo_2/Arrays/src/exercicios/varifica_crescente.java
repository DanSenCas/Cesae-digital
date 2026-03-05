package exercicios;

public class varifica_crescente {
    static void main() {
        int[] num = {7, 13, 22, 3, 18, 9};
        //double[] num2 = {6.5, 7, -12.4, 0, -48};

        boolean crescente = true;

        for (int i = 0; i < num.length-1 ; i++) {
            if (num[i] > num[i+1]) {
                crescente = false;
                break;
            }
        }

        if (crescente){
            System.out.println("Esta por ordem");
        }else {
            System.out.println("Nao esta por ordem crescente");
        }
    }
}
