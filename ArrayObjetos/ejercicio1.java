import java.util.*;

class ejercicio1 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        Alumno[] Alumnos = new Alumno[3];
        for (int i = 0; i < Alumnos.length; i++) {
            System.out.print("Ingrese el " + i + 1 + " nombre:");
            String nombre = o.next();
            System.out.print("Ingrese su edad: ");
            int edad = o.nextInt();
            Alumnos[i] = new Alumno(nombre, edad);
        }

        for (int j = 0; j < Alumnos.length; j++) {
            Alumnos[j].getInfo();
        }

        o.close();
    }
}

class Alumno {

    private String nombre;
    private int edad;

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void getInfo() {
        System.out.println("su nombre es: " + nombre);
        System.out.println("su edad es: " + edad);
    }
}
