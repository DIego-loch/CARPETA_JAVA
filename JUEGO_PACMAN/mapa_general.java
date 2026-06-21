class mapa_general {

    public static void mapa_inicial(String[][] mapa) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapa[i][j] = "|_";
            }
        }
    }

    public static void mapa_estado_cambiado(String[][] mapa, int x, int y) {
        cambio_estado.mapa_estado(mapa, x, y);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
    }
}
