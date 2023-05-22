package App2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//Iniciamos con la clase sedes
public class Sedes {

  //Declaramos una lista llamada sedes que contiene los datos de sede
  public List<Sede> sedes = new ArrayList<>();

  //El usuario lee desde la consoloa en el siguiente código
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

  //Se define un get que devuelve la lista de las sedes actuales que existen
  public List<Sede> getSedes() {
    return sedes;
  }

  //Definimos un set que establece la lista de las sedes con la lista proporcionada
  public void setSedes(List<Sede> sedes) {
    this.sedes = sedes;
  }

  //Definimos leersedes que nos entrega la lista de clientes como argumento
  //y también creamos una lista con las sedes únicas, que no se repitan.
  public void leerSedes(List<Cliente> clientes) {
    List<Sede> sedesUnicas = new ArrayList<>();
    boolean skip = true; // salta primer cliente

    // Recorrer la lista de clientes
    for (Cliente cliente : clientes) {
        if (skip) {
            skip = false;
            continue;
        }

        String codSedeCliente = cliente.getCod_sede();
        Sede sede = new Sede(codSedeCliente);

        // Verificar si el plan ya existe en la lista de planes únicos
        boolean sedeExistente = false;
        for (int i = 0; i < sedesUnicas.size(); i++) {
            Sede sedeUnica = sedesUnicas.get(i);
            if (sedeUnica.getCod_sede().equals(codSedeCliente)) {
                sede = sedeUnica;
                sedeExistente = true;
                break;
            }
        }

        if (!sedeExistente) {
            sedesUnicas.add(sede);
        }
    }

    sedes = sedesUnicas;
  }
  //El usuario puede agregar nuevas sedes de las que ya existen actualmente
  public void agregarSede() throws IOException {
    System.out.println("\nIngrese el código de la nueva sede\n");
    String name = reader.readLine();
    Sede nueva_sede = new Sede(name);
    sedes.add(nueva_sede);
  }

  //El usuario puede eliminar sedes que ya existen, pero con condiciones
  public void eliminarSede(List<Cliente> clientes) throws IOException {
    System.out.println("\nIngrese el código de la sede a eliminar\n");
    String name = reader.readLine();
    for (int i = 0; i < sedes.size(); i++) {
      Sede sede = sedes.get(i);
      if (sede.getCod_sede().equals(name)) {
        for (Cliente cliente : clientes) {
          //Si la sede está adherida a un cliente, no dejará elimiarla
          if (cliente.getCod_sede().equals(name)) {
            System.out.println("\nNo se puede eliminar la sede porque hay clientes asociados a ella\n");
            return;
          } else {
            //El caso que no esté adherida a un cliente, la eliminará
            System.out.println("\nSede eliminada\n");
            sedes.remove(i);
          }
        }
        break;
      }
    }
    //En caso que no exista la sede, claramente no eliminará nada
    System.out.println("\nNo se encontró la sede\n");
  }

  //Finalmente entregará las sedes actuales después del proceso anterior.
  public void verSedes() {
    for (Sede sede : sedes) {
        System.out.println(sede.getCod_sede());
    }
    System.out.println("Total de sedes: " + sedes.size());
  }


}
  


