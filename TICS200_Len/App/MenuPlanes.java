package App;

import java.io.IOException;


public class MenuPlanes extends Menu{
    
    Planes planes = super.planes;
    Registro registro = super.registro;

    public MenuPlanes(Planes planes, Registro registro) {
        this.planes = planes;
        this.registro = registro;
    }

    public void menu() {
        int option;
        do {
          System.out.println("\n");
          System.out.println("Menú planes");
          System.out.println("1. Agregar plan");
          System.out.println("2. Eliminar plan");
          System.out.println("3. Ver planes");
          System.out.println("4. Volver al menú principal");
          System.out.println("\n");
          System.out.println("Ingrese una opción: ");
    
          try {option = Integer.parseInt(System.console().readLine());
          } catch (NumberFormatException e) {
            option = -1;
          }
    
          switch (option) {
            case 1:
                try {
                    planes.agregarPlan();
                } catch (IOException e) {
                    System.out.println("Error al ingresar e plan");
                }
                break;
            case 2:
                try {
                    planes.eliminarPlan(registro.getClientes());
                } catch (IOException e) {
                    System.out.println("Error al eliminar el plan");
                }
                break;
            case 3:
                try {
                    planes.verPlanes();
                } catch (IOException e) {
                    System.out.println("Error al ver los planes");
                }
                break;
            case 4:
                System.out.println("\n");
                System.out.println("Volviendo al menú principal");
                System.out.println("\n");
                return;
            default:
                System.out.println("\n");
                System.out.println("Ingrese una opción válida");
                System.out.println("\n");
                break;
          }
        } while (option != 0);
      }
}
