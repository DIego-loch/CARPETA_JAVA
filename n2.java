class n2 {

    public static void main(String[] args) {
        libro texto = new libro("La biblia", "Papito Dios", 1000);
        texto.leer();
        System.out.println("Es largo el sagrado libro: " + texto.esLargo());
    }
}

class libro {

    public String titulo;
    public String autor;
    public int paginas;

    libro(String titulo, String autor, int paginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    void leer() {
        System.out.println("Leyendo " + this.titulo + " de " + this.autor);
    }

    boolean esLargo() {
        return this.paginas > 300;
    }
}
