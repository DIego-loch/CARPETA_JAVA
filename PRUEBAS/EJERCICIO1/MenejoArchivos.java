//import java.io.*;
import java.util.*;

class MenejoArchivos {

    public static void main(String[] args) {
        // tratar de escribir mensaje en pantalla
        String escribir = "loch.Write(hola);";
        String cadena = new String();
        try (Scanner o = new Scanner(System.in)) {
            cadena = o.next();
            if (cadena.equals(escribir)) {
                System.out.println("hola");
            }
        } catch (Exception e) {
            System.out.println("no se puede ejecutar el codigo");
        }

        //manejo de archivos
        /*  try (FileWriter escribir = new FileWriter("saludo.txt")) {
          escribir.write("hola\n");
          escribir.write("pela la verga");
      } catch (Exception e) {
          System.out.println("archivo ya creado");
      } */
    }
}
