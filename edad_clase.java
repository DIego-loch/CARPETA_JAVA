class edad_clase {

    public static void main(String[] args) {
        Usuario sp = new Usuario();
        sp.setEdad(1);
        System.out.println("La edad es: " + sp.getEdad());
    }
}

class Usuario {

    private int edad;

    public void setEdad(int edad) {
        if (edad < 0) edad = 0;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }
}
