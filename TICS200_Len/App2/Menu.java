package App2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
  // Inicia el registro 
    Registro registro = new Registro();
    public Planes planes;
    public Sedes sedes;
    public void runMenu() {
      // Genera el respaldo .bak
        registro.createBackup();
      // Lee el registro y rellena la lista clientes del registro con la info del csv
        registro.leerRegistro();
        planes = new Planes();
        planes.leerPlanes(registro.getClientes());
      // Genera la lista de planes a partir de la lista de clientes, atributo de registro
        sedes = new Sedes();
        sedes.leerSedes(registro.getClientes());
      // Genera la lista de sede a partir de la lista de clientes, atributo de registro

        // Mostrar el menú principal
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
                // Ejecuta el metodo verClientes del objeto registro
                    registro.verClientes();
                    break;
                case 2:
                // Ejecuta el metodo buscarClientePorRUT del objeto registro
                    registro.buscarClientePorRUT(reader);
                    break;
                case 3:
                // Ejecuta el metodo agregarClientes del objeto registro
                    registro.agregarCliente(planes.getPlanes(), sedes.getSedes());
                    break;
                case 4:
                // Ejecuta el metodo eliminarClientes del objeto registro
                    registro.eliminarCliente(reader);
                    break;
                case 5:
                // Ejecuta el metodo editarClientes del objeto registro
                    registro.editarCliente(planes.getPlanes(), sedes.getSedes());
                    break;
                case 6:
                // Ejecuta el metodo menu del objeto menuPlanes e inicializa el submenu de sedes
                    MenuPlanes menuPlanes = new MenuPlanes(planes, registro);
                    menuPlanes.menu();
                    break;
                case 7:
                // Ejecuta el metodo menu del objeto menuSedes e inicializa el submenu de sedes
                    MenuSedes menuSedes = new MenuSedes(sedes, registro);
                    menuSedes.menu();
                    break;
                case 0:
                // Guarda el archivo en el archivo original
                    System.out.println("Saliendo...");
                    registro.guardarClientesEnArchivo("BigMuscle.csv");
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
}