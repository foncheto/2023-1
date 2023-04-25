public class Cliente {
    private String rut;

    public Cliente(String rut) {
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public boolean validarRut() {
        String rutSinDv = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        int suma = 0;
        int multiplicador = 2;
        for (int i = rutSinDv.length() - 1; i >= 0; i--) {
            int digito = Integer.parseInt(rutSinDv.substring(i, i + 1));
            suma += digito * multiplicador;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int resto = suma % 11;
        int resultado = 11 - resto;
        String dvCalculado = "";
        if (resultado == 11) {
            dvCalculado = "0";
        } else if (resultado == 10) {
            dvCalculado = "K";
        } else {
            dvCalculado = String.valueOf(resultado);
        }

        return dvCalculado.charAt(0) == dv;
    }
    public static void main(String[] args) {
        Cliente cliente = new Cliente("67315367");
        if (cliente.validarRut()) {
            System.out.println("El RUT es válido");
        } else {
            System.out.println("El RUT es inválido");
        }
    }
}