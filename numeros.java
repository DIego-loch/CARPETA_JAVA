class numeros {

    public static void main(String[] args) {
        mayor_menor sp = new mayor_menor();
        sp.comparador(3, 2);
    }
}

class mayor_menor {

    public void comparador(int a, int b) {
        if (a > b) System.out.println("El numero mayor es " + a);
        else System.out.println("El numero mayor es " + b);
    }
}
