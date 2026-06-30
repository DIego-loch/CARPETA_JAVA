package modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Muestra implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Estado {
        INGRESO, EN_PROCESO, PROCESADO
    }

    private String codigo;
    private String descripcion;
    private double[][] patron; // Matriz cuadrada
    private Estado estado;
    private Investigador investigadorAsignado; // Puede ser null si no está asignado

    public Muestra(String codigo, String descripcion, double[][] patron) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.patron = patron;
        this.estado = Estado.INGRESO;
        this.investigadorAsignado = null;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double[][] getPatron() { return patron; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Investigador getInvestigadorAsignado() { return investigadorAsignado; }
    public void setInvestigadorAsignado(Investigador investigador) { this.investigadorAsignado = investigador; }

    // Método para obtener el tamaño de la matriz
    public int getSize() {
        return patron.length;
    }

    @Override
    public String toString() {
        return codigo + " - " + descripcion + " (" + estado + ")";
    }
}