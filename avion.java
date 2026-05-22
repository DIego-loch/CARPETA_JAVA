// Sistema de reserva de vuelos
class Vuelo {

    String numero;
    String destino;
    int asientosDisponibles;
    double precio;

    public Vuelo(String numero, String destino, int asientos, double precio) {
        this.numero = numero;
        this.destino = destino;
        this.asientosDisponibles = asientos;
        this.precio = precio;
    }

    boolean reservarAsiento() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
            return true;
        }
        return false;
    }
}

class AgenciaViajes {

    private ArrayList<Vuelo> vuelos;

    public AgenciaViajes() {
        vuelos = new ArrayList<>();
        vuelos.add(new Vuelo("AA101", "Madrid", 5, 500));
        vuelos.add(new Vuelo("BB202", "París", 0, 400)); // Sin asientos
        vuelos.add(new Vuelo("CC303", "Londres", 3, 450));
    }

    // 1. Buscar vuelo por número (devuelve objeto)
    public Vuelo buscarVuelo(String numero) {
        for (Vuelo v : vuelos) {
            if (v.numero.equals(numero)) {
                return v; // Devuelvo el vuelo encontrado
            }
        }
        return null;
    }

    // 2. Buscar vuelos con disponibilidad (devuelve lista de objetos)
    public ArrayList<Vuelo> buscarVuelosDisponibles() {
        ArrayList<Vuelo> disponibles = new ArrayList<>();
        for (Vuelo v : vuelos) {
            if (v.asientosDisponibles > 0) {
                disponibles.add(v); // Agrego el objeto a la lista
            }
        }
        return disponibles; // Devuelvo la lista completa
    }

    // 3. Buscar el vuelo más barato (devuelve el mejor objeto)
    public Vuelo buscarVueloMasBarato() {
        if (vuelos.isEmpty()) return null;

        Vuelo masBarato = vuelos.get(0);
        for (Vuelo v : vuelos) {
            if (v.precio < masBarato.precio) {
                masBarato = v; // Actualizo referencia al más barato
            }
        }
        return masBarato; // Devuelvo el objeto del vuelo más barato
    }
}

// Sistema de usuario
class Usuario {

    String nombre;
    ArrayList<Vuelo> misReservas = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    void reservarVuelo(Vuelo v) {
        if (v.reservarAsiento()) {
            misReservas.add(v);
            System.out.println(
                nombre + " reservó vuelo " + v.numero + " a " + v.destino
            );
        } else {
            System.out.println("No hay asientos en " + v.numero);
        }
    }

    void mostrarReservas() {
        System.out.println("\nReservas de " + nombre + ":");
        for (Vuelo v : misReservas) {
            System.out.println("- " + v.destino + " (" + v.numero + ")");
        }
    }
}

// Programa principal
public class Main {

    public static void main(String[] args) {
        AgenciaViajes agencia = new AgenciaViajes();
        Usuario usuario = new Usuario("Ana");

        // 1. Buscar un vuelo específico
        System.out.println("=== Buscando vuelo AA101 ===");
        Vuelo miVuelo = agencia.buscarVuelo("AA101");
        if (miVuelo != null) {
            System.out.println(
                "Encontrado: " + miVuelo.destino + " - $" + miVuelo.precio
            );
            usuario.reservarVuelo(miVuelo);
        }

        // 2. Buscar vuelos disponibles
        System.out.println("\n=== Vuelos disponibles ===");
        ArrayList<Vuelo> disponibles = agencia.buscarVuelosDisponibles();
        for (Vuelo v : disponibles) {
            System.out.println(
                v.destino + " - Asientos: " + v.asientosDisponibles
            );
        }

        // 3. Buscar el más barato
        System.out.println("\n=== Vuelo más barato ===");
        Vuelo barato = agencia.buscarVueloMasBarato();
        if (barato != null) {
            System.out.println(
                "El más barato: " + barato.destino + " - $" + barato.precio
            );
            usuario.reservarVuelo(barato);
        }

        // 4. Mostrar reservas del usuario
        usuario.mostrarReservas();
    }
}
