class nombre {

    public static void main(String[] args) {
        Registro registro = new Registro();
        registro.mostrarregistro("diego123");
    }
}

class Registro {

    private boolean nombreValido(String nombre_original) {
        int contador = 0;
        for (int a = 0; a < nombre_original.length(); a++) {
            if (Character.isLetter(nombre_original.charAt(a))) {
                contador++;
            }
        }
        return contador >= 3;
    }

    public void mostrarregistro(String nombre) {
        if (nombreValido(nombre)) {
            System.out.println("Registro exitoso");
        } else System.out.println("usuario invalido");
    }
}
