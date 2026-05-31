import java.util.*;

class animal {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("Ingresa el nombre del animal");
        String nombre = o.nextLine();
        System.out.println("Ingresa la edad que tiene el animal");
        int edad = o.nextInt();
        Animal sp = new Animal(nombre, edad);
        sp.getAnimal();
    }
}

class Animal {

    private String nombre;
    private int edad;

    Animal(String nombre, int edad) {
        if (edad > 0) {
            this.nombre = nombre;
            this.edad = edad;
        }
    }

    public void getAnimal() {
        System.out.println("El animal es un " + nombre + " y tiene: " + edad);
    }
}
