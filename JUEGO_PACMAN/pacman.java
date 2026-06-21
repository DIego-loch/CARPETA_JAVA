import java.util.*;

class pacman {

    public static void main(String[] args) {
        String[][] mapa = new String[10][10];
        mapa_general.mapa_inicial(mapa);
        String movimiento = "";
        int x = 3;
        int y = 3;
        Scanner o = new Scanner(System.in);

        while (true) {
            limpiar_pantalla.limpiar();
            mapa_general.mapa_estado_cambiado(mapa, x, y);
            System.out.println(
                "A = izquierda, D = derecha, W = arriba, S = abajo, l = salir "
            );
            movimiento = o.next().toUpperCase();

            switch (movimiento) {
                case "A":
                    if (x > 0) {
                        x--;
                    }
                    break;
                case "D":
                    if (x < mapa.length - 1) {
                        x++;
                    }
                    break;
                case "S":
                    if (y < mapa.length - 1) {
                        y++;
                    }
                    break;
                case "W":
                    if (y > 0) {
                        y--;
                    }
                    break;
            }
        }
    }
}
