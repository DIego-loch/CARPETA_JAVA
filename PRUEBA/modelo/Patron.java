package modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Patron implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nombre;
    private int[][] matriz; // Matriz de 0 y 1

    public Patron(String codigo, String nombre, int[][] matriz) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.matriz = matriz;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int[][] getMatriz() { return matriz; }
    public int getSize() {
        return matriz.length;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}