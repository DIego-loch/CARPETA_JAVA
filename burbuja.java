import java.util.*;

class burbuja {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);

        System.out.print("Ingrese el número de notas: ");
        int numeros = o.nextInt();

        int nota_mayor = 0;
        int nota_menor = 100;
        int suma = 0;
        int nota_superior_60 = 0;
        int nota_inferior_60 = 0;

        for (int i = 1; i <= numeros; i++) {
            System.out.println("Ingrese la nota " + i);
            int nota = o.nextInt();

            suma += nota;

            if (nota > nota_mayor) {
                nota_mayor = nota;
            }

            if (nota < nota_menor) {
                nota_menor = nota;
            }

            if (nota >= 60) {
                nota_superior_60++;
            } else {
                nota_inferior_60++;
            }
        }

        double promedio = (double) suma / numeros;

        System.out.println("La nota mayor es " + nota_mayor);
        System.out.println("La nota menor es " + nota_menor);
        System.out.println("El promedio de las notas es " + promedio);
        System.out.println("Alumnos aprobados: " + nota_superior_60);
        System.out.println("Alumnos desaprobados: " + nota_inferior_60);

        o.close();
    }
}
