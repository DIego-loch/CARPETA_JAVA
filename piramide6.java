class piramide6 {

    public static void main(String[] args) {
        for (int a = 1; a <= 5; a++) {
            for (int b = 1; b <= a; b++) {
                System.out.print(" ");
            }
            for (int c = 1; c <= 2 * (5 - a) + 1; c++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
