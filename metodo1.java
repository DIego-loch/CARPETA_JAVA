import java.util.*;

class indexado {

    int valor_indexado1;

    indexado(int valor_indexado1) {
        this.valor_indexado1 = valor_indexado1;
    }

    public int calculadora_basica() {
        return this.valor_indexado1 + 1;
    }
}

class metodo1 {

    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        System.out.println("primero valor indexado");
        int valor1 = o.nextInt();
        indexado valor = new indexado(valor1);
        System.out.println(valor.calculadora_basica());
    }
}
