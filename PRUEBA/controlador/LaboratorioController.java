package controlador;

import modelo.*;
import java.util.List;

public class LaboratorioController {
    private Laboratorio laboratorio;

    public LaboratorioController() {
        this.laboratorio = Laboratorio.getInstance();
    }

    // Métodos de autenticación y gestión
    public boolean autenticarAdmin(String usuario, String contrasena) {
        return "admin".equals(usuario) && "admin".equals(contrasena);
    }

    public Investigador autenticarInvestigador(String codigo, String contrasena) {
        return laboratorio.autenticarInvestigador(codigo, contrasena);
    }

    // Investigadores
    public void agregarInvestigador(Investigador inv) {
        laboratorio.agregarInvestigador(inv);
        Laboratorio.guardarDatos();
    }

    public boolean eliminarInvestigador(String codigo) {
        boolean eliminado = laboratorio.eliminarInvestigador(codigo);
        if (eliminado) Laboratorio.guardarDatos();
        return eliminado;
    }

    public Investigador buscarInvestigador(String codigo) {
        return laboratorio.buscarInvestigador(codigo);
    }

    public List<Investigador> getInvestigadores() {
        return laboratorio.getInvestigadores();
    }

    // Muestras
    public void agregarMuestra(Muestra muestra) {
        laboratorio.agregarMuestra(muestra);
        Laboratorio.guardarDatos();
    }

    public boolean eliminarMuestra(String codigo) {
        boolean eliminado = laboratorio.eliminarMuestra(codigo);
        if (eliminado) Laboratorio.guardarDatos();
        return eliminado;
    }

    public Muestra buscarMuestra(String codigo) {
        return laboratorio.buscarMuestra(codigo);
    }

    public List<Muestra> getMuestras() {
        return laboratorio.getMuestras();
    }

    public List<Muestra> getMuestrasDisponibles() {
        return laboratorio.getMuestrasDisponibles();
    }

    // Patrones
    public void agregarPatron(Patron patron) {
        laboratorio.agregarPatron(patron);
        Laboratorio.guardarDatos();
    }

    public boolean eliminarPatron(String codigo) {
        boolean eliminado = laboratorio.eliminarPatron(codigo);
        if (eliminado) Laboratorio.guardarDatos();
        return eliminado;
    }

    public Patron buscarPatron(String codigo) {
        return laboratorio.buscarPatron(codigo);
    }

    public List<Patron> getPatrones() {
        return laboratorio.getPatrones();
    }

    // Experimentos
    public void agregarExperimento(Experimento exp) {
        laboratorio.agregarExperimento(exp);
        Laboratorio.guardarDatos();
    }

    public List<Experimento> getExperimentos() {
        return laboratorio.getExperimentos();
    }

    // Asignación de experimentos
    public boolean asignarMuestraAInvestigador(String codigoMuestra, String codigoInvestigador) {
        Muestra muestra = laboratorio.buscarMuestra(codigoMuestra);
        Investigador investigador = laboratorio.buscarInvestigador(codigoInvestigador);

        if (muestra != null && investigador != null && muestra.getEstado() == Muestra.Estado.INGRESO) {
            muestra.setEstado(Muestra.Estado.EN_PROCESO);
            muestra.setInvestigadorAsignado(investigador);
            Laboratorio.guardarDatos();
            return true;
        }
        return false;
    }

    // Análisis de experimentos
    public boolean realizarAnalisis(Muestra muestra, Patron patron, Investigador investigador) {
        boolean resultado = laboratorio.analizarMuestra(muestra, patron);

        // Crear el experimento
        Experimento exp = new Experimento(
            muestra.getCodigo(),
            patron.getCodigo(),
            resultado,
            patron.getNombre()
        );
        laboratorio.agregarExperimento(exp);

        // Actualizar estado de la muestra
        muestra.setEstado(Muestra.Estado.PROCESADO);
        investigador.incrementarExperimentos();

        Laboratorio.guardarDatos();
        return resultado;
    }

    public List<Muestra> getMuestrasAsignadasAInvestigador(Investigador investigador) {
        List<Muestra> asignadas = new ArrayList<>();
        for (Muestra m : laboratorio.getMuestras()) {
            if (m.getInvestigadorAsignado() != null &&
                m.getInvestigadorAsignado().getCodigo().equals(investigador.getCodigo()) &&
                m.getEstado() != Muestra.Estado.PROCESADO) {
                asignadas.add(m);
            }
        }
        return asignadas;
    }
}