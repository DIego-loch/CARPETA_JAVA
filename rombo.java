import java.util.*;

class piramide {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("Ingrese la N cantidad de lineas para el rombo");
        int a = o.nextInt();

        for (int i = 0; i < a; i++) {
            for (int t = 1; t <= a - i; t++) {
                System.out.print(" ");
            }
            for (int s = 1; s <= (2 * i) + 1; s++) {
                System.out.print("*");
            }
            System.out.println(" ");
        }
    }
}
