class temperatura {

    public static void main(String[] args) {
        conversion conv = new conversion();
        double celsius = 5;
        double fahrenheit = conv.celsiusAFahrenheit(celsius);
        System.out.println(fahrenheit);
    }
}

class conversion {

    public double celsiusAFahrenheit(double c) {
        return (9.0 / 5) * c + 32;
    }
}
