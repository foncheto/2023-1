package App2Try;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClienteCSV {

    public static void main(String[] args) {
        
        List<Cliente> clientes = new ArrayList<>();
        
        // Cargar los datos del archivo CSV en una lista de clientes
        try (BufferedReader br = new BufferedReader(new FileReader("App2Try/jaja.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2]);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        
        // Mostrar el menú
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {
            System.out.println("MENU:");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Buscar un cliente por nombre");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                opcion = -1;
            }
            System.out.println();
            switch (opcion) {
                case 1:
                    verClientes(clientes);
                    break;
                case 2:
                    buscarClientePorNombre(clientes, reader);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
            System.out.println();
        } while (opcion != 0);
        
        // Cerrar el objeto BufferedReader
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar el objeto BufferedReader: " + e.getMessage());
        }
    }
    
    private static void verClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    
    private static void buscarClientePorNombre(List<Cliente> clientes, BufferedReader reader) {
        System.out.print("Ingrese el nombre del cliente a buscar: ");
        String nombre;
        try {
            nombre = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el nombre del cliente: " + e.getMessage());
            return;
        }
        boolean encontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(cliente);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún cliente con ese nombre");
        }
    }
}

class Cliente {
    private String nombre;
    private String email;
    private String telefono;
    
    public Cliente(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + email + " - " + telefono;
    }
}
