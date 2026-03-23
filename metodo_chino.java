class metodo_chino {

    public static void main(String[] ars) {
        for (int a = 1; a <= 5; a++) {
            // Espacios a la izquierda
            for (int c = 5; c >= a; c--) {
                System.out.print(" ");
            }

            // Asteriscos
            for (int b = 1; b <= a; b++) {
                System.out.print("*");
                if (b >= 2) System.out.print("*");
            }

            // Salto de línea
            System.out.println("");
        }
    }
}
