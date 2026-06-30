import java.io.*;

public class Carpeta {

    public static void main(String[] args) {
        File archivo = new File("CARPETA");
        if (!archivo.exists()) {
            archivo.mkdir();
        }
        try (
            FileWriter conexion = new FileWriter("CARPETA/mensaje.txt");
            BufferedWriter escritura = new BufferedWriter(conexion);
            PrintWriter loch = new PrintWriter(escritura);
        ) {
            //mensaje
            loch.println("pela la verga");
            System.out.println("mensaje creado con exito");
        } catch (Exception e) {
            //mensaje 2
            System.out.println("mensaje no creado");
        }
    }
}
