import java.util.*;

public class empleados {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        boolean ciclo = true;
        while (ciclo) {
            System.out.println("Ingrese el nombre del empleado: ");
            String nombre1 = o.nextLine();
            Empleado empleado = new Empleado(nombre1);
            System.out.println("Cuentas creadas (Id): " + empleado.getId());
            empleado.mostrarNombre();

            System.out.println("¿Desea continuar? (s/n)");
            String respuesta = o.nextLine();
            if (respuesta.equals("n")) {
                ciclo = false;
            }
        }
    }
}

class Empleado {

    static int contadorEmpleados = 0;
    private String nombre_empleado;
    private int id_empleado;
    boolean ciclo = true;

    public Empleado(String nombre) {
        nombre_empleado = nombre;
        contadorEmpleados++;
        id_empleado = contadorEmpleados;
    }

    public void mostrarNombre() {
        System.out.println("Nombre: " + nombre_empleado);
    }

    public int getId() {
        return id_empleado;
    }
}
