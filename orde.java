import java.util.Arrays;

public class orde {

    public static int[] unirVectores(int[] v1, int[] v2) {
        int n = v1.length;
        int m = v2.length;
        int[] resultado = new int[n + m];

        int i = n - 1; // v1 se recorre al revés (queda ascendente)
        int j = 0; // v2 ya está ascendente
        int k = 0;

        while (i >= 0 && j < m) {
            if (v1[i] <= v2[j]) {
                resultado[k++] = v1[i--];
            } else {
                resultado[k++] = v2[j++];
            }
        }

        // Copiar elementos restantes de v1
        while (i >= 0) {
            resultado[k++] = v1[i--];
        }

        // Copiar elementos restantes de v2
        while (j < m) {
            resultado[k++] = v2[j++];
        }

        return resultado;
    }

    public static void main(String[] args) {
        int[] v1 = { 20, 15, 10, 5 }; // Mayor a menor
        int[] v2 = { 1, 7, 12, 18, 25 }; // Menor a mayor

        int[] resultado = unirVectores(v1, v2);

        System.out.println("Vector unido ordenado de menor a mayor:");
        System.out.println(Arrays.toString(resultado));
    }
}
