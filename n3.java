public class CuentaBancaria {

    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
            System.out.println("Saldo inicial inválido, se estableció en 0");
        }
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depositado: $" + cantidad);
        } else {
            System.out.println("Cantidad inválida para depósito");
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Retirado: $" + cantidad);
        } else {
            System.out.println("Retiro no válido");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void mostrarEstado() {
        System.out.println("Saldo actual: $" + saldo);
    }
}

// Prueba:
public class n3 {

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);
        cuenta.depositar(500);
        cuenta.retirar(200);
        cuenta.mostrarEstado();
        System.out.println("Saldo vía getter: $" + cuenta.getSaldo());
    }
}
