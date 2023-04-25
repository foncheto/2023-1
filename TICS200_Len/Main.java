

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("12345678-9");
        if (cliente.validarRut()) {
            System.out.println("El RUT es válido");
        } else {
            System.out.println("El RUT es inválido");
        }
    }
}