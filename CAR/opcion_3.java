import java.util.*;

public class opcion_3 {

    public static void asignacion_automatizada(
        String[][] EstadoAsientos,
        String[][] Nuevo_estado,
        Scanner o
    ) {
        String clase = """
                ================================
                |            Clase             |
                ================================
                | Seleccione la clase:         |
                | 1. VIP           ->  1 - 5   |
                | 2. Economica     ->  6 - 20  |
                ================================
                |Elige una opción:             |
                ================================
            """;
        System.out.print(clase);
        int clase_asiento = o.nextInt();
        ProcesosGenerales.MenorOcupacion(
            clase_asiento,
            EstadoAsientos,
            Nuevo_estado
        );
    }
}
