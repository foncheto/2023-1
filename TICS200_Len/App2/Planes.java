package App2;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//  Iniciamos con la clase planes
public class Planes {

  //Declaramos una lista llamada planes que contiene los datos de plan
    public List<Plan> planes = new ArrayList<>();

  //El usuario lee desde la consola en el siguiente código
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

  //Se define un get que devuelve la lista de los planes actuales que existen
    public List<Plan> getPlanes() {
        return planes;
    }

  //Definimos un set que establece la lista de los planes con la lista proporcionada
    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

  //Defininmos leerplanes que nos entrega la lista de clientes como argumento
  //y también creamos una lista con los planes únicos, o sea que no se repitan.
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

  //El usuario puede agregar nuevos planes de los que ya existen actualmente
    public void agregarPlan() throws IOException {
        System.out.println("Ingrese el código del plan: ");
        String cod_plan = System.console().readLine();
        Plan nuevo_plan = new Plan(cod_plan);
        planes.add(nuevo_plan);
    }

  //El usuario puede eliminar planes que ya existen, pero con condiciones
    public void eliminarPlan(List<Cliente> clientes) throws IOException {
        System.out.println("\nIngrese el código del plan a eliminar\n");
        String name = reader.readLine();
        for (int i = 0; i < planes.size(); i++) {
          Plan plan = planes.get(i);
          if (plan.getCod_plan().equals(name)) {
            for (Cliente cliente : clientes) {
              //Si el plan está adherido a un cliente, no dejará eliminarlo
              if (cliente.getCod_plan().equals(name)) {
                System.out.println("\nNo se puede eliminar el plan porque hay clientes asociados a el\n");
                return;
              } else {
                //En caso que no esté adherido a un cliente y esté solo, lo eliminará
                System.out.println("\nPlan eliminado\n");
                planes.remove(i);
              }
            }
            break;
          }
        }
      //En caso que no exista el plan, pues claramente no eliminará nada
        System.out.println("\nNo se encontró el plan\n");
      }

  //Finalmente entregará los planes después del proceso anterior
    public void verPlanes() throws IOException{
        for (Plan plan : planes) {
            System.out.println(plan.getCod_plan());
        }
        System.out.println("Total de planes: " + planes.size());
    }
}
