package modelo;

import java.io.Serializable;

public class Investigador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nombre;
    private String genero;
    private String contrasena;
    private int experimentosRealizados;

    public Investigador(String codigo, String nombre, String genero, String contrasena) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.contrasena = contrasena;
        this.experimentosRealizados = 0;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public int getExperimentosRealizados() { return experimentosRealizados; }
    public void incrementarExperimentos() { this.experimentosRealizados++; }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}