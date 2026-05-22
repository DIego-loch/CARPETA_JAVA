class saludar {

    public void saldudo_oficial(String nombre) {
        System.out.println("HOla " + nombre);
    }
}

class saludando {

    public static void main(String[] args) {
        saludar saludar1 = new saludar();
        saludar1.saldudo_oficial("Diego");
    }
}
