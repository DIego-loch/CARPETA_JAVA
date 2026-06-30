import java.util.*;

class ejercicio2 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        Alumno[] Alumnos = new Alumno[5];
        for (int i = 0; i < Alumnos.length; i++) {
            System.out.print("Ingrese el " + (i + 1) + " nombre:");
            String nombre = o.next();
            System.out.print("Ingrese su edad: ");
            int edad = o.nextInt();
            Alumnos[i] = new Alumno(nombre, edad);
        }
        System.out.print("Buscar el alumno: ");
        String busqueda = o.next();

        for (int j = 0; j < Alumnos.length; j++) {
            if (busqueda.equals(Alumnos[j].nombre)) {
                Alumnos[j].getDatos();
                return;
            } else if ((j + 1) > Alumnos.length) {
                System.out.println("No se encontro al alumno");
            }
        }

        o.close();
    }
}

class Alumno {

    public String nombre;
    public int edad;

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void getDatos() {
        System.out.println("Su nombre es: " + nombre);
        System.out.println("Su edad es: " + edad);
    }
}
