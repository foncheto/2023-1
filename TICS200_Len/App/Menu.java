package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
    Registro registro = new Registro();
    public Planes planes;
    public Sedes sedes;
    public void runMenu() {
        registro.createBackup();
        registro.leerRegistro();
        planes = new Planes();
        planes.leerPlanes(registro.getClientes());
        sedes = new Sedes();
        sedes.leerSedes(registro.getClientes());

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
                    registro.agregarCliente(planes.getPlanes(), sedes.getSedes());
                    break;
                case 4:
                    registro.eliminarCliente(reader);
                    break;
                case 5:
                    registro.editarCliente(planes.getPlanes(), sedes.getSedes());
                    break;
                case 6:
                    MenuPlanes menuPlanes = new MenuPlanes(planes, registro);
                    menuPlanes.menu();
                    break;
                case 7:
                    MenuSedes menuSedes = new MenuSedes(sedes, registro);
                    menuSedes.menu();
                    break;
                case 0:
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