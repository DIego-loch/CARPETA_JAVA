import Controlador.*;
import Vista.*;

class MenuPrincipal {

    public static void main(String[] args) {
        form vista = new form();
        controlador controlado = new controlador(vista);
    }
}
