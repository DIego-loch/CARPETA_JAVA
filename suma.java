class suma {

    public static void main(String[] args) {
        operacion sp = new operacion();
        int resultado = sp.suma(4, 5);
        System.out.println("La suma total es: " + resultado);
    }
}

class operacion {

    public int suma(int a, int b) {
        return a + b;
    }
}
