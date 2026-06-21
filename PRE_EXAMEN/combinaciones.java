class combinaciones {

    public static void main(String[] args) {
        //numeros entre 0 y 9
        for (int i = 0; i <= 9; i++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    System.out.println("" + i + b + c);
                }
            }
        }
    }
}
