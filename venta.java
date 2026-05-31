class Producto {

    private String nombre;
    private double precio;

    Producto(String nombre, double precio) {
        if (precio > 1) {
            this.nombre = nombre;
            this.precio = precio;
        } else {
            System.out.println("Precio invalido");
            System.exit(0);
        }
    }

    public void aplicarDescuento() {
        if (precio > 100) {
            double descuento = precio - (precio * 0.15);
            System.out.println(
                " El " +
                    nombre +
                    " valia: " +
                    precio +
                    " ahora con el descuento es: " +
                    descuento
            );
            return;
        } else System.out.println("precio de: " + precio);
        return;
    }
}

class venta {

    public static void main(String[] args) {
        Producto pro = new Producto("vitaflenaco", -1);
        pro.aplicarDescuento();
    }
}
