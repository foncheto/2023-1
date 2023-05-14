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
        try (BufferedReader br = new BufferedReader(new FileReader("BigMuscle.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);

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
    private String cod_plan;
    private String descripcion_plan;
    private String desde;
    private String hasta;
    private String cod_sede;
    private String ubicacion_sede;

    
    
    public Cliente(String nombre, String email, String telefono, String cod_plan, String descripcion_plan, String desde, String hasta, String cod_sede, String ubicacion_sede) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.cod_plan = cod_plan;
        this.descripcion_plan = descripcion_plan;
        this.desde = desde;
        this.hasta = hasta;
        this.cod_sede = cod_sede;
        this.ubicacion_sede = ubicacion_sede;
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

    public String getCod_plan() {
        return cod_plan;
    }

    public String getDescripcion_plan() {
        return descripcion_plan;
    }

    public String getDesde() {
        return desde;
    }

    public String getHasta() {
        return hasta;
    }

    public String getCod_sede() {
        return cod_sede;
    }
    
    public String getUbicacion_sede() {
        return ubicacion_sede;
    }

    @Override
    public String toString() {
        return nombre + " - " + email + " - " + telefono + " - " + cod_plan + " - " + descripcion_plan + " - " + desde + " - " + hasta + " - " + cod_sede + " - " + ubicacion_sede;
    }
}