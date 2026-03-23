/*Pedir los coeficientes de una ecuación se 2º grado, y muestre sus soluciones reales. Si no existen,
debe indicarlo.*/
import java.util.*;

class ejercicio2 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);

        System.out.println("Ingrese el valor de a ");
        double a = o.nextDouble();
        System.out.println("Ingrese el valor de b ");
        double b = o.nextDouble();
        System.out.println("Ingrese el valor de c ");
        double c = o.nextDouble();
        double determinante = 0;

        //determinante
        if (a == 0) {
            System.out.println("NO ES UNA ECUACION CUADRATICA");
        } else {
            determinante = Math.sqrt(Math.pow(b, 2) - 4 * a * c);

            if (determinante > 0) {
                double x1 = ((b + determinante) / 2) * a;
                double x2 = ((b - determinante) / 2) * a;
                System.out.println("El valor de x1 es: " + x1);
                System.out.println("El valor de x2 es: " + x2);
            } else if (determinante == 0) {
                double x3 = (b / 2) * a;
                System.out.println("El valor unico es: " + x3);
            } else System.out.println("SIN SOLUCIONES REALES");
        }
    }
}
