package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registro {
    
    public List<Cliente> leerRegistro() {
        List<Cliente> clientes = new ArrayList<>();
            
            // Cargar los datos del archivo CSV en una lista de clientes
        try (BufferedReader br = new BufferedReader(new FileReader("App/BigMuscle.csv"))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);
                
                cliente.setRut(cliente.getRut().replace("-", ""));
                
                if (count == 0) { // Omitir la primera línea del archivo
                    clientes.add(cliente);
                    count++;
                    continue;
                }

                try {if (!cliente.validarRut()) {
                    System.out.println("El RUT " + cliente.getRut() + " es inválido, el cliente fue omitido");
                } else if (!cliente.validarFecha()){
                    System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, el cliente fue omitido");
                } else if (!cliente.validarPlanSede()) {
                    System.out.println("El plan " + cliente.getCod_plan() + " y/o la sede " + cliente.getCod_sede() + " son inválidos, el cliente fue omitido");
                }
                else {
                    clientes.add(cliente);
                }
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return clientes;
    }
}
