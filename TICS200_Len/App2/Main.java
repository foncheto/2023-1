package App2;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setRut("67315367");
        
        System.out.println(cliente.getRut());

        if (cliente.validarRut()) {
            System.out.println("El RUT es válido");
        } else {
            System.out.println("El RUT es inválido");
        }
        Cat cat = new Cat();
        cat.setName("Mishifu");
        cat.meow();
    }
}
