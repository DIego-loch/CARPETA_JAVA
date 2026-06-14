class opcion_5 {

    static int AsientosDisponibles = 0;
    static int Fondo_recaudado = 0;
    static int AsientosOcupados = 0;

    public static void datos_reporte(int contador_asientos, int precio_total) {
        AsientosDisponibles = 120 - contador_asientos;
        AsientosOcupados = contador_asientos;
        Fondo_recaudado = precio_total;
    }

    public static void ReporteGeneral() {
        String reporte = """
                ======================================
                |           REPORTE DE GENERAL       |
                ======================================
                | Asientos Libres: %d                |
                | Asientos ocupados: %d              |
                | Ingresos Recaudados: %d            |
            """.formatted(
            AsientosDisponibles,
            AsientosOcupados,
            Fondo_recaudado
        );
        System.out.println(reporte);
    }
}
