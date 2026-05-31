import java.util.*;

class contra {

    public static void main(String[] args) {
        Cuenta sp = new Cuenta();
        Scanner o = new Scanner(System.in);
        System.out.println("Ingresa la contraseña");
        String contra_nueva = o.nextLine();
        sp.setContraseña(contra_nueva);

        System.out.println("la contraseña es: " + sp.getContraseña());

        o.close();
    }
}

class Cuenta {

    private String contraseña;

    public void setContraseña(String cadena) {
        if (cadena.length() < 6) {
            System.out.println("Tiene menos de 6 caracteres");
            return;
        }
        contraseña = cadena;
    }

    public String getContraseña() {
        return contraseña;
    }
}
