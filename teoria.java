import java.util.Scanner;

class Teoria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese un numero: ");
        int suma1 = sc.nextInt();

        int contador = 0;

        while (suma1 != 1) {
            System.out.println("Valor: " + suma1);

            if (suma1 % 2 == 0) {
                suma1 = suma1 / 2;
            } else {
                suma1 = suma1 * 3 + 1;
            }

            contador++;
        }

        System.out.println("Valor final: 1");
        System.out.println("Pasos: " + contador);
    }
}
