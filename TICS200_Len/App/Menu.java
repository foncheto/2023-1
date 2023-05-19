package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Menu {
    public void runMenu() {
        Registro registro = new Registro();
        List<Cliente> clientes = registro.leerRegistro();

        if (clientes == null) {
            System.err.println("No se pudo cargar el registro");
            return;
        }

        // Mostrar el menú
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {
            System.out.println("MENU:");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Buscar un cliente por RUT");
            System.out.println("3. Agregar un nuevo cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Editar un cliente");
            System.out.println("6. Planes");
            System.out.println("7. Sedes");
            System.out.println("0. Salir y Guardar");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                opcion = -1;
            }
            System.out.println();
            switch (opcion) {
                case 1:
                    registro.verClientes();
                    break;
                case 2:
                    registro.buscarClientePorRUT(reader);
                    break;
                case 3:
                    System.out.println("Agregar un Cliente");
                    break;
                case 4:
                    registro.eliminarCliente(reader);
                    break;
                case 5:
                    System.out.println("Editar un Cliente");
                    break;
                case 6:
                    System.out.println("Planes");
                    break;
                case 7:
                    System.out.println("Sedes");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    guardarClientesEnArchivo(clientes);
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

    private static void guardarClientesEnArchivo(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("App/Datos/new.csv"))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getRut() + "," + cliente.getNombre() + "," + cliente.getEdad() + "," +cliente.getCod_plan() + "," + cliente.getDescripcion_plan() + "," + cliente.getDesde() + "," +cliente.getHasta() + "," + cliente.getCod_sede() + "," + cliente.getUbicacion_sede());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los clientes en el archivo: " + e.getMessage());
        }
    }
}