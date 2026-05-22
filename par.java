class par {

    public static void main(String[] args) {
        par_impar sp = new par_impar();
        boolean resultado = sp.esPar(5);
        System.out.println("el numero es: " + resultado);
    }
}

class par_impar {

    public boolean esPar(int a) {
        return a % 2 == 0;
    }
}
