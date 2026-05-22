class n1 {

    public static void main(String[] args) {
        Perro michucho = new Perro("Coco", 7);
        michucho.ladrar();
        michucho.mostrarinfo();
    }
}

class Perro {

    String nombre;
    int edad;

    Perro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    void ladrar() {
        System.out.println("!Guau guau!");
    }

    void mostrarinfo() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Edad: " + this.edad);
    }
}
