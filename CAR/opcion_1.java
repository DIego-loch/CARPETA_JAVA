import java.util.*;

class opcion_1 {

    // String[][] actualizacion_estado = EstadoAsientos;

    public static void boleto_individual(
        String[][] EstadoAsientos,
        String[][] Nuevo_estado,
        Scanner o
    ) {
        System.out.println("==========COMPRA DE VOLETO INDIVIDUAL===========");
        ProcesosGenerales.vista_asientos(EstadoAsientos, true, Nuevo_estado);
        System.out.println("================================================");
        System.out.print("Selecciona tu asiento: ");
        String Asiento_individual = o.next();
        Asiento_individual = Asiento_individual.toUpperCase();
        ProcesosGenerales.Cambio_estado(
            Asiento_individual,
            EstadoAsientos,
            Nuevo_estado
        );
    }
}
