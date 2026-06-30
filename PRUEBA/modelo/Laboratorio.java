package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Laboratorio instancia;
    private List<Investigador> investigadores;
    private List<Muestra> muestras;
    private List<Patron> patrones;
    private List<Experimento> experimentos;

    private Laboratorio() {
        investigadores = new ArrayList<>();
        muestras = new ArrayList<>();
        patrones = new ArrayList<>();
        experimentos = new ArrayList<>();
    }

    public static Laboratorio getInstance() {
        if (instancia == null) {
            instancia = cargarDatos();
        }
        return instancia;
    }

    // Métodos para manejar investigadores
    public void agregarInvestigador(Investigador inv) {
        investigadores.add(inv);
    }

    public boolean eliminarInvestigador(String codigo) {
        return investigadores.removeIf(inv -> inv.getCodigo().equals(codigo));
    }

    public Investigador buscarInvestigador(String codigo) {
        for (Investigador inv : investigadores) {
            if (inv.getCodigo().equals(codigo)) {
                return inv;
            }
        }
        return null;
    }

    public List<Investigador> getInvestigadores() {
        return new ArrayList<>(investigadores);
    }

    // Métodos para manejar muestras
    public void agregarMuestra(Muestra muestra) {
        muestras.add(muestra);
    }

    public boolean eliminarMuestra(String codigo) {
        return muestras.removeIf(m -> m.getCodigo().equals(codigo));
    }

    public Muestra buscarMuestra(String codigo) {
        for (Muestra m : muestras) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }

    public List<Muestra> getMuestras() {
        return new ArrayList<>(muestras);
    }

    public List<Muestra> getMuestrasDisponibles() {
        List<Muestra> disponibles = new ArrayList<>();
        for (Muestra m : muestras) {
            if (m.getEstado() == Muestra.Estado.INGRESO) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    // Métodos para manejar patrones
    public void agregarPatron(Patron patron) {
        patrones.add(patron);
    }

    public boolean eliminarPatron(String codigo) {
        return patrones.removeIf(p -> p.getCodigo().equals(codigo));
    }

    public Patron buscarPatron(String codigo) {
        for (Patron p : patrones) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public List<Patron> getPatrones() {
        return new ArrayList<>(patrones);
    }

    // Métodos para manejar experimentos
    public void agregarExperimento(Experimento exp) {
        experimentos.add(exp);
    }

    public List<Experimento> getExperimentos() {
        return new ArrayList<>(experimentos);
    }

    // Método de autenticación
    public Investigador autenticarInvestigador(String codigo, String contrasena) {
        for (Investigador inv : investigadores) {
            if (inv.getCodigo().equals(codigo) && inv.getContrasena().equals(contrasena)) {
                return inv;
            }
        }
        return null;
    }

    // --- ALGORITMO DE ANÁLISIS ---
    public boolean analizarMuestra(Muestra muestra, Patron patron) {
        double[][] matriz1 = muestra.getPatron();
        int n = matriz1.length;

        // 1. MATRIZ TEMP 1 = MATRIZ 1 * 3
        double[][] temp1 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp1[i][j] = matriz1[i][j] * 3;
            }
        }

        // 2. MATRIZ TEMP 2 = MATRIZ 1 * 7
        double[][] temp2 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp2[i][j] = matriz1[i][j] * 7;
            }
        }

        // 3. MATRIZ TEMP 3 = MATRIZ TEMP 1 * MATRIZ TEMP 2 (Multiplicación de matrices)
        double[][] temp3 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double suma = 0;
                for (int k = 0; k < n; k++) {
                    suma += temp1[i][k] * temp2[k][j];
                }
                temp3[i][j] = suma;
            }
        }

        // 4. MATRIZ 2 = MOD(MATRIZ TEMP 3, 2)
        int[][] matriz2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz2[i][j] = (int) temp3[i][j] % 2;
            }
        }

        // 5. Comparar con el patrón
        int[][] patronMatriz = patron.getMatriz();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz2[i][j] != patronMatriz[i][j]) {
                    return false; // No coincide
                }
            }
        }
        return true; // Coincide
    }

    // --- SERIALIZACIÓN ---
    private static final String ARCHIVO_DATOS = "laboratorio.dat";

    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(instancia);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Laboratorio cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
                return (Laboratorio) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new Laboratorio();
    }
}