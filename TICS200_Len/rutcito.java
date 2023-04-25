class Test {
    public static void main(String[] args) {
        // prueba de rut sin digito verificador:
        int rut = 19689144;

        Boolean valido;
        int verificador;

        verificador = 7;
        valido = validarRut(rut, verificador);
        System.out.printf("El dígito verificador %d para el rut %d es: %s\n", verificador, rut, valido ? "válido" : "inválido");

        // prueba de rut con dígito correcto:
        verificador = 4;
        valido = validarRut(rut, verificador);
        System.out.printf("El dígito verificador %d para el rut %d es: %s\n", verificador, rut, valido ? "válido" : "inválido");
    }

    public static Boolean validarRut(final int rutSinVerificador, final int digitoVerificador) {
        int rut = rutSinVerificador; //rut sin dígito verificador.
        int contador = 2;
        int acumulador = 0;
        while (rut != 0) {
            int multiplo = (rut % 10) * contador;
            acumulador = acumulador + multiplo;
            rut = rut / 10;
            contador++;
            if (contador == 8) {
                contador = 2;
            }
        }
        int digitoCorrecto = acumulador % 10;
        digitoCorrecto = 11 - digitoCorrecto;
        if (digitoCorrecto == 11) {
            digitoCorrecto = 0;
        } else if (digitoCorrecto == 10) {
            digitoCorrecto = 1;
        }
//        System.out.printf("El dígito verificador es: %d\n", digitoCorrecto);
        return digitoCorrecto == digitoVerificador;
    }
}