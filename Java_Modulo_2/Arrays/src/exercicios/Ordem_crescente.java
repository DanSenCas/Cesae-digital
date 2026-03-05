package exercicios;

public class Ordem_crescente {
    static void main() {
        int[] num = {13, 7, 22, 3, 18, 9};
        //double[] num2 = {6.5, 7, -12.4, 0, -48};

        for (int i = 0; i < num.length-1; i++) { // loop externo, vai repetir o interno ate ele chegar ao fim
            for (int j = 0; j < num.length-1; j++) { // loop interno, quando chegar ao fim vai incrementar 1 no i
                if (num[j]> num[j+1]){ // j = 0 entao j + 1 = 1, vai ser o vizinho
                    int temp = num[j]; // troca de valores
                    num[j]= num[j+1];
                    num[j+1] = temp;
                }

            }

        }

        // aqui o array esta organizado

        for (int i = 0; i < num.length; i++){
            int n = num[i];
            System.out.print(n + " ");
        }
    }
}
