class ejercicio {

    public static void main(String[] args) {
        persona alumno = new persona();
        alumno.setNombre("diego");

        System.out.println(
            "la nota de: " +
                alumno.getNombre() +
                "es mayor a 61? -> " +
                alumno.aprobado(61)
        );
    }
}

class persona {

    private String nombre;
    private double nota;

    public boolean aprobado(double nota) {
        this.nota = nota;
        return this.nota >= 61.00;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
