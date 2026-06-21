class array_objetos {

    public static void main(String[] args) {
        metodo2[] array = new metodo2[2];

        array[0] = new metodo2();
        array[1] = new metodo3();

        array[0].Objeto();
        array[1].Objeto();
    }
}

class metodo2 {

    public void Objeto() {
        System.out.println("hola");
    }
}

class metodo3 extends metodo2 {

    @Override
    public void Objeto() {
        System.out.println("hola1");
    }
}
