void main(String[] args) {
    //int[] num = {12,7,22,8,18,9};
    double[] num2 = { 6.5, 7, -12.4,0,-48};

    double menor = num2[0];

    for (int i = 1; i < num2.length;) {
        if (menor>num2[i]){
            menor = num2[i];
        }else {
            i++;
        }

    }
    System.out.println(menor);

}
