/* Pedir el radio de una circunferencia y calcular su longitud. */
import java.util.*;

class ejercicio4 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("Ingrese el radio de la circunferencia");
        double r = o.nextDouble();

        //longitud
        double longitud = 2 * r * Math.PI;
        System.out.println("La longitud " + longitud);
        o.close();
    }
}
