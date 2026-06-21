class Ordenamiento1 {

    public static void main(String[] args) {
        int[] arr = { 50, 20, 25, 10, 40, 30, 27, 8, 32 };
        int aux;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                aux = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = aux;
            }
        }
        for (int r = 0; r < arr.length; r++) {
            System.out.println(arr[r]);
        }
    }
}
