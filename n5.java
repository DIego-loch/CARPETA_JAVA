import java.util.*;

class Libro {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro prestado: " + titulo);
        } else {
            System.out.println("El libro no está disponible");
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("Libro devuelto: " + titulo);
    }

    public void mostrarInfo() {
        System.out.println(
            titulo +
                " - " +
                autor +
                " - " +
                (disponible ? "Disponible" : "Prestado")
        );
    }
}

class Biblioteca {

    private ArrayList<Libro> catalogo;
    private static int totalPrestamos = 0;

    public Biblioteca() {
        catalogo = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    public void prestarLibro(String titulo) {
        for (Libro libro : catalogo) {
            if (
                libro.getTitulo().equalsIgnoreCase(titulo) &&
                libro.isDisponible()
            ) {
                libro.prestar();
                totalPrestamos++;
                return;
            }
        }
        System.out.println("Libro no encontrado o no disponible");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : catalogo) {
            if (
                libro.getTitulo().equalsIgnoreCase(titulo) &&
                !libro.isDisponible()
            ) {
                libro.devolver();
                return;
            }
        }
        System.out.println(
            "Libro no encontrado o ya estaba disponible".concat("mundo")
        );
    }

    public void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO ---");
        for (Libro libro : catalogo) {
            libro.mostrarInfo();
        }
    }

    public void buscarPorAutor(String autor) {
        System.out.println("\nLibros de " + autor + ":");
        for (Libro libro : catalogo) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                libro.mostrarInfo();
            }
        }
    }

    public static int getTotalPrestamos() {
        return totalPrestamos;
    }
}

// Prueba completa:
public class n5 {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Agregar libros
        biblioteca.agregarLibro(
            new Libro("El Principito", "Saint-Exupéry", "123")
        );
        biblioteca.agregarLibro(new Libro("1984", "George Orwell", "456"));
        biblioteca.agregarLibro(new Libro("Don Quijote", "Cervantes", "789"));

        // Mostrar catálogo
        biblioteca.mostrarCatalogo();

        // Prestar libros
        biblioteca.prestarLibro("1984");
        biblioteca.prestarLibro("1984"); // Intento de prestar nuevamente

        // Buscar por autor
        biblioteca.buscarPorAutor("Cervantes");

        // Devolver
        biblioteca.devolverLibro("1984");

        // Mostrar estadísticas
        System.out.println(
            "\nTotal préstamos realizados: " + Biblioteca.getTotalPrestamos()
        );
    }
}
