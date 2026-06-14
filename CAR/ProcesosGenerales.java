class ProcesosGenerales {

    static int[] contador_precio = new int[2];
    static int contador_asientos = 0,
        precio_total = 0;
    static int precio_boleto = 0;
    static String asiento, mayor;
    static int[] EquilibrioAvion = new int[2];
    static int indice_externo = 0,
        indice_interno = 0;

    public static void Cambio_estado(
        String Asiento_individual,
        String[][] EstadoAsientos,
        String[][] Nuevo_estado
    ) {
        //Validacion de estado y asignacion de asientos
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                if (Asiento_individual.equals(EstadoAsientos[i][j])) {
                    if (Nuevo_estado[i][j].equals("L")) {
                        contador_asientos++;
                        Nuevo_estado[i][j] = "X";
                        System.out.println(
                            "Asiento " +
                                Asiento_individual +
                                " vendido con exito"
                        );
                        if (j >= 0 && j < 3) {
                            EquilibrioAvion[0]++;
                        } else if (j >= 3 && j < 6) {
                            EquilibrioAvion[1]++;
                        }
                        if (i <= 4) {
                            precio_boleto = 150;
                            contador_precio[1] += 150;
                            switch (i) {
                                case 0:
                                    Nuevo_estado[1][j] = "B";
                                    contador_asientos++;
                                    break;
                                default:
                                    Nuevo_estado[i - 1][j] = "B";
                                    Nuevo_estado[i + 1][j] = "B";
                                    contador_asientos += 2;
                                    break;
                            }
                        } else if (i > 4 && i < 20) {
                            precio_boleto = 50;
                            contador_precio[0] += 50;
                        }
                        String boleto = """
                            +--------------------------------------------------+
                            |                 BOLETO ELECTRÓNICO               |
                            +--------------------------------------------------+
                            | ASIENTO DEL AVION :%s                            |
                            | PRECIO: $ %d                                     |
                            +--------------------------------------------------+
                            |             |||| | |||| || |||| | |||            |
                            +--------------------------------------------------+
                            """.formatted(Asiento_individual, precio_boleto);

                        System.out.println(boleto);
                        precio_total = contador_precio[0] + contador_precio[1];
                        opcion_5.datos_reporte(contador_asientos, precio_total);
                    } else {
                        System.out.println("Asiento ocupado");
                    }
                    return;
                }
            }
        }
        System.out.println("Asiento inexistente: verifique el asiento");
    }

    public static void vista_asientos(
        String[][] EstadoAsientos,
        boolean tipo,
        String[][] Estado_inabilitado
    ) {
        for (int i = 0; i < 20; i++) {
            if (i <= 8 && tipo) {
                for (int j = 1; j <= 10; j++) {
                    System.out.print(" ");
                }
            } else if (i > 8 && tipo) {
                for (int j = 1; j <= 7; j++) {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 3; j++){ 
                if (tipo) {
                    System.out.print("[" + EstadoAsientos[i][j] + "]");
                } else {
                    System.out.print("[" + Estado_inabilitado[i][j] + "]");
                }
            }
            System.out.print("||");

            for (int j = 3; j < 6; j++) {
                if (tipo) {
                    System.out.print("[" + EstadoAsientos[i][j] + "]");
                } else {
                    System.out.print("[" + Estado_inabilitado[i][j] + "]");
                }
            }
            System.out.println("");
        }
    }

    public static void asientos_contiguos(
        int opcion,
        String[][] EstadoAsientos,
        String[][] Nuevo_estado
    ) {
        //lado izquierdo
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }
            if (
                Nuevo_estado[opcion - 1][i].equals("L") &&
                Nuevo_estado[opcion - 1][i + 1].equals("L")
            ) {
                for (int inicio = i; inicio <= i + 1; inicio++) {
                    asiento = EstadoAsientos[opcion - 1][inicio];
                    Cambio_estado(asiento, EstadoAsientos, Nuevo_estado);
                }
                return;
            }
        }
        System.out.println("Asientos no encontrados en esta fila");
        return;
    }

    public static void MenorOcupacion(
        int clase_asiento,
        String[][] EstadoAsientos,
        String[][] Nuevo_estado
    ) {
        if (clase_asiento == 1) {
            indice_externo = 0;
            indice_interno = 6;
        } else if (clase_asiento == 2) {
            indice_externo = 6;
            indice_interno = 20;
        }

        if (EquilibrioAvion[0] > EquilibrioAvion[1]) {
            for (int i = indice_externo; i < indice_interno; i++) {
                for (int j = 3; j < 6; j++) {
                    if (Nuevo_estado[i][j].equals("L")) {
                        mayor = EstadoAsientos[i][j];
                        Cambio_estado(mayor, EstadoAsientos, Nuevo_estado);
                        return;
                    }
                }
            }
        } else if (EquilibrioAvion[1] > EquilibrioAvion[0]) {
            for (int i = indice_externo; i < indice_interno; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Nuevo_estado[i][j].equals("L")) {
                        mayor = EstadoAsientos[i][j];
                        Cambio_estado(mayor, EstadoAsientos, Nuevo_estado);
                        return;
                    }
                }
            }
        } else if (EquilibrioAvion[0] == EquilibrioAvion[1]) {
            for (int i = indice_externo; i < indice_interno; i++) {
                for (int j = 0; j < 6; j++) {
                    if (Nuevo_estado[i][j].equals("L")) {
                        mayor = EstadoAsientos[i][j];
                        Cambio_estado(mayor, EstadoAsientos, Nuevo_estado);
                        return;
                    }
                }
            }
        }
    }
}
