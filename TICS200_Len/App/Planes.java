package App;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Planes {

    public List<Plan> planes = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public List<Plan> getPlanes() {
        return planes;
    }
    
    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

    public void leerPlanes(List<Cliente> clientes) {
        List<Plan> planesUnicos = new ArrayList<>();
        boolean skip = true; // skip first client
        // Recorrer la lista de clientes
        for (Cliente cliente : clientes) {
            if (skip) {
                skip = false;
                continue;
            }

            String codPlanCliente = cliente.getCod_plan();
            Plan plan = new Plan(codPlanCliente);
    
            // Verificar si el plan ya existe en la lista de planes únicos
            boolean planExistente = false;
            for (int i = 0; i < planesUnicos.size(); i++) {
                Plan planUnico = planesUnicos.get(i);
                if (planUnico.getCod_plan().equals(codPlanCliente)) {
                    plan = planUnico;
                    planExistente = true;
                    break;
                }
            }
    
            if (!planExistente) {
                planesUnicos.add(plan);
            }
        }
    
        planes = planesUnicos;
    }

    public void agregarPlan() throws IOException {
        System.out.println("Ingrese el código del plan: ");
        String cod_plan = System.console().readLine();
        Plan nuevo_plan = new Plan(cod_plan);
        planes.add(nuevo_plan);
    }

    public void eliminarPlan(List<Cliente> clientes) throws IOException {
        System.out.println("\nIngrese el código del plan a eliminar\n");
        String name = reader.readLine();
        for (int i = 0; i < planes.size(); i++) {
          Plan plan = planes.get(i);
          if (plan.getCod_plan().equals(name)) {
            for (Cliente cliente : clientes) {
              if (cliente.getCod_plan().equals(name)) {
                System.out.println("\nNo se puede eliminar el plan porque hay clientes asociados a el\n");
                return;
              } else {
                System.out.println("\nPlan eliminado\n");
                planes.remove(i);
              }
            }
            break;
          }
        }
        System.out.println("\nNo se encontró el plan\n");
      }

    public void verPlanes() throws IOException{
        for (Plan plan : planes) {
            System.out.println(plan.getCod_plan());
        }
        System.out.println("Total de planes: " + planes.size());
    }
}
