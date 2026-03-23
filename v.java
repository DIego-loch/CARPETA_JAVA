class v {

    public static void main(String[] args) {
        for (int a = 1; a <= 4; a++) {
            for (int b = 1; b <= a; b++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int e = 1; e <= 7 - (2 * a); e++) {
                System.out.print(" ");
            }
            if (a != 4) {
                System.out.print("*");
            }
            System.out.println(" ");
        }
    }
}
