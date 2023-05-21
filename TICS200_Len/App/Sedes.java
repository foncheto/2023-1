package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Sedes {

  public List<Sede> sedes = new ArrayList<>();

  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

  public List<Sede> getSedes() {
    return sedes;
  }

  public void setSedes(List<Sede> sedes) {
    this.sedes = sedes;
  }

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

  public void agregarSede() throws IOException {
    System.out.println("\nIngrese el código de la nueva sede\n");
    String name = reader.readLine();
    Sede nueva_sede = new Sede(name);
    sedes.add(nueva_sede);
  }

  public void eliminarSede(List<Cliente> clientes) throws IOException {
    System.out.println("\nIngrese el código de la sede a eliminar\n");
    String name = reader.readLine();
    for (int i = 0; i < sedes.size(); i++) {
      Sede sede = sedes.get(i);
      if (sede.getCod_sede().equals(name)) {
        for (Cliente cliente : clientes) {
          if (cliente.getCod_sede().equals(name)) {
            System.out.println("\nNo se puede eliminar la sede porque hay clientes asociados a ella\n");
            return;
          } else {
            System.out.println("\nSede eliminada\n");
            sedes.remove(i);
          }
        }
        break;
      }
    }
    System.out.println("\nNo se encontró la sede\n");
  }

  public void verSedes() {
    for (Sede sede : sedes) {
        System.out.println(sede.getCod_sede());
    }
    System.out.println("Total de sedes: " + sedes.size());
  }


}
  


