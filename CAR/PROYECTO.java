import java.util.*;

class proyecto {

    public static void main(String[] args) {
        //variables y matrices
        String[][] EstadoAsientos = new String[20][6];
        String[][] Nuevo_estado = new String[20][6];
        boolean estado = true;
        Scanner o = new Scanner(System.in);

        //Inicialización de matrices todos con asientos libres
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                Nuevo_estado[i][j] = "L";
            }
        }
        //Inicializacion de el nombre de cada asiento
        estado_asientos(EstadoAsientos);

        do {
            System.out.println(" ");
            System.out.println("¡¡BIENVENIDO AL SISTEMA DE VUELOS USAC!!");
            System.out.println("=================Menu=================");
            System.out.println("||Elige tu opción:                  ||");
            System.out.println("||1. Venta de Boleto Individual     ||");
            System.out.println("||2. Buscar Boletos Contiguos       ||");
            System.out.println("||3. Asignación Automática          ||");
            System.out.println("||4. Mostrar Mapa de la Cabina      ||");
            System.out.println("||5. Reporte de Vuelo               ||");
            System.out.println("||6. Salir                          ||");
            System.out.println("======================================");
            System.out.print("Selección: ");
            int seleccion_menu = o.nextInt();

            switch (seleccion_menu) {
                case 1:
                    opcion_1.boleto_individual(EstadoAsientos, Nuevo_estado, o);
                    break;
                case 2:
                    opcion_2.AsientosContiguos(o, EstadoAsientos, Nuevo_estado);
                    break;
                case 3:
                    opcion_3.asignacion_automatizada(
                        EstadoAsientos,
                        Nuevo_estado,
                        o
                    );
                    break;
                case 4:
                    opcion_4.asientos_vision1(Nuevo_estado);
                    break;
                case 5:
                    opcion_5.ReporteGeneral();
                    break;
                case 6:
                    estado = false;
                    opcion_6.ReporteFinal();
                    break;
            }
        } while (estado);

        o.close();
    }

    public static void estado_asientos(String[][] EstadoAsientos) {
        String[] Columnas = { "A", "B", "C", "D", "E", "F" };
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                EstadoAsientos[i][j] = Columnas[j] + (i + 1);
            }
        }
    }
}
