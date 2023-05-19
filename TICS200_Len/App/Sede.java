package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sede {
  private String cod_sede;
  private String ubicacion_sede;

  public List<Sede> sedes = new ArrayList<>();

  public Sede(String cod_sede, String ubicacion_sede) {
    this.cod_sede = cod_sede;
    this.ubicacion_sede = ubicacion_sede;
  }

  public String getCod_sede() {
    return cod_sede;
  }

  public String getUbicacion_sede() {
    return ubicacion_sede;
  }

  public List<Sede> leerSedes(List<Sede> sedes) {
    try (BufferedReader br = new BufferedReader(new FileReader("BigMuscle.csv"))) {
      String line;
      int count = 0;
      while ((line = br.readLine()) != null) {
        if (count == 0) {
          count++;
          continue;
        }
        String[] datos = line.split(",");
        Sede sede = new Sede(datos[7], datos[8]);
        sedes.add(sede);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sedes;
  }


  public List<Sede> agregarSede(Sede nueva_sede) {
    sedes.add(nueva_sede);
    return sedes;
  }

  public List<Sede> eliminarSede(Sede sede, List<Cliente> clientes) {
    List<Sede> sedes_nuevas = leerSedes(sedes);
    if (clientes.contains(sede)) {
      System.out.println("Existe un cliente con esa sede, no la puede eliminar");
      return sedes;
    }
    sedes.remove(sede);
    return sedes;
  }
}
  


