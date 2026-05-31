class calculadora1 {

    public static void main(String[] args) {
        calculadora cal = new calculadora();
        cal.mostrarMult(2, 2);
    }
}

class calculadora {

    private int multiplicar(int a, int b) {
        return a * b;
    }

    public void mostrarMult(int a, int b) {
        System.out.println("el valo es: " + multiplicar(a, b));
    }
}
