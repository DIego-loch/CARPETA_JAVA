import java.util.*;

class opcion_2 {

    static String boleto_individual;
    static int j = 0;

    public static void AsientosContiguos(
        Scanner o,
        String[][] EstadoAsientos,
        String[][] Nuevo_estado
    ) {
        String menu_contiguos = """
            ==========COMPRA DE ASIENTOS CONTIGUOS===========
            | Primera clase -> Filas 1-5 = $150 c/u          |
            | Segunda clase -> Filas 6-20 = $50 c/u          |
            ==================================================
            Seleccione:
            """;
        System.out.print(menu_contiguos);
        int opcion = o.nextInt();
        //verificacion de disponibilidad
        ProcesosGenerales.asientos_contiguos(
            opcion,
            EstadoAsientos,
            Nuevo_estado
        );
    }
}
