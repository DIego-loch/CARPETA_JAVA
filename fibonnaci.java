import java.util.*;

class fibonnaci {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        int numero = o.nextInt();
        System.out.println(
            "Numero en la posicion " + numero + "=" + fibonnaci(numero)
        );
    }

    public static int fibonnaci(int numero) {
        if (numero <= 2) {
            return 1;
        }

        return fibonnaci(numero - 1) + fibonnaci(numero - 2);
    }
}
