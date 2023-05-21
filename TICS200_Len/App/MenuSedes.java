package App;

import java.io.IOException;

public class MenuSedes extends Menu{

    Sedes sedes = super.sedes;
    Registro registro = super.registro;

    public MenuSedes(Sedes sedes, Registro registro) {
        this.sedes = sedes;
        this.registro = registro;
    }

    public void menu() {
        Sedes sedes = super.sedes;
        Registro registro = super.registro;
        int option;
        do {
          System.out.println("\n");
          System.out.println("Menú sedes");
          System.out.println("1. Agregar sede");
          System.out.println("2. Eliminar sede");
          System.out.println("3. Ver sedes");
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
                sedes.agregarSede();
              } catch (IOException e) {
                System.out.println("Error al ingresar la sede");
              }
              break;
            case 2:
              try {
                sedes.eliminarSede(registro.getClientes());
              } catch (IOException e) {
                System.out.println("Error al eliminar la sede");
              }
              break;
            case 3:
              sedes.verSedes();
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
