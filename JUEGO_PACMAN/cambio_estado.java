class cambio_estado {

    public static void mapa_estado(String[][] mapa, int x, int y) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == y && j == x) {
                    mapa[i][j] = "|X";
                    return;
                } else {
                    mapa[i][j] = "|_";
                }
            }
        }
    }
}
