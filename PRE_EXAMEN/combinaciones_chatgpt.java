class Combinaciones {

    public static void main(String[] args) {
        for (int i = 0; i <= 999; i++) {
            // imprime con 3 dígitos, el %03d\n %d -> indica entero,
            // 3 -> indica 3 espacios,
            // 0 -> indica rellenar con ceros y
            // \n -> indica salto de línea
            System.out.printf("%03d\n", i);
        }
    }
}
