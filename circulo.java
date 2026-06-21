class circulo {

    static void main(String[] args) {
        area AREA = new area();
        double resultado = AREA.are(3.0);
        System.out.println(resultado);
    }
}

class area {

    public double are(double radio) {
        return Math.PI * Math.pow(radio, 2);
    }
}
