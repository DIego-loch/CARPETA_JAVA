import java.util.*;

public class caminar {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);

        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = o.nextLine();

        int posicion = 20; // posición inicial

        while (true) {
            // Limpiar pantalla (simulado)
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }

            mostrarPersonaje(nombre, posicion);

            System.out.print("\nA = Izquierda, D = Derecha, S = Salir: ");
            String movimiento = o.nextLine().toUpperCase();

            switch (movimiento) {
                case "A":
                    if (posicion > 0) {
                        posicion--;
                    }
                    break;
                case "D":
                    posicion++;
                    break;
                case "S":
                    System.out.println("Juego terminado.");
                    return;
                default:
                    System.out.println("Movimiento inválido.");
            }
        }
    }

    public static void mostrarPersonaje(String nombre, int espacios) {
        String margen = " ".repeat(espacios);

        System.out.println(margen + nombre);
        System.out.println(margen + " O ");
        System.out.println(margen + "/|\\");
        System.out.println(margen + " | ");
        System.out.println(margen + "/ \\");
    }
}
