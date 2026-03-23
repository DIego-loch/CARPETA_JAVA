/* Pedir el radio de un círculo y calcular su área. A=PI*r^2 */
import java.util.*;

class ejercicio3 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("Ingrese el radio de la circunferencia");
        double r = o.nextDouble();

        //area
        double area = Math.PI * Math.pow(r, 2);

        System.out.println("El valor del area es: " + area);
    }
}
