package App;

import java.util.ArrayList;
import java.util.List;

public class Planes {

    public List<Plan> planes = new ArrayList<>();

    public List<Plan> getPlanes() {
        return planes;
    }
    
    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

    public void leerPlanes(List<Cliente> clientes) {
        List<Plan> planesUnicos = new ArrayList<>();
    
        // Recorrer la lista de clientes
        for (Cliente cliente : clientes) {
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

    public void agregarPlan() {
        System.out.println("Ingrese el código del plan: ");
        String cod_plan = System.console().readLine();
        Plan nuevo_plan = new Plan(cod_plan);
        planes.add(nuevo_plan);
    }

    public void verPlanes() {
        for (Plan plan : planes) {
            System.out.println(plan.getCod_plan());
        }
        System.out.println("Total de planes: " + planes.size());
    }
}
