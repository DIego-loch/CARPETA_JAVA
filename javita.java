class javita {

    public static void main(String[] args) {
        int n = 8;
        int m = 1;
        int resultado = m;
        boolean ciclo = true;
        while (ciclo) {
            if (m == n) {
                ciclo = false;
            }
            m = m + 1;
            resultado = resultado * m;
        }
        System.out.println(resultado);
    }
}
