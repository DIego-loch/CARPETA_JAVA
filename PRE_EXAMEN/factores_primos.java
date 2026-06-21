class factores_primos {

    public static void main(String[] args) {
        descomponer(1280);
    }

    public static void descomponer(int numero) {
        System.out.print(numero + " = ");

        int divisor = 2;

        while (numero > 1) {
            int contador = 0;

            while (numero % divisor == 0) {
                numero = numero / divisor;
                contador++;
            }

            if (contador > 0) {
                System.out.print(divisor);

                if (contador > 1) {
                    System.out.print("^" + contador);
                }

                if (numero > 1) {
                    System.out.print(" * ");
                }
            }

            divisor++;
        }
    }
}
