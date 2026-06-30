package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Experimento implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contador = 0;

    private int numeroAnalisis;
    private String codigoMuestra;
    private String codigoPatron;
    private LocalDateTime fechaHora;
    private boolean resultado; // true = éxito, false = fallido
    private String nombrePatron;

    public Experimento(String codigoMuestra, String codigoPatron, boolean resultado, String nombrePatron) {
        this.numeroAnalisis = ++contador;
        this.codigoMuestra = codigoMuestra;
        this.codigoPatron = codigoPatron;
        this.fechaHora = LocalDateTime.now();
        this.resultado = resultado;
        this.nombrePatron = nombrePatron;
    }

    // Getters
    public int getNumeroAnalisis() { return numeroAnalisis; }
    public String getCodigoMuestra() { return codigoMuestra; }
    public String getCodigoPatron() { return codigoPatron; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public boolean isResultado() { return resultado; }
    public String getNombrePatron() { return nombrePatron; }

    public String getFechaFormateada() {
        return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getResultadoTexto() {
        return resultado ? "ÉXITO" : "FALLIDO";
    }
}