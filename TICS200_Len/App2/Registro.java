package App2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Registro {
    // Se crea lista de clientes
    List<Cliente> clientes = new ArrayList<>();

    // Metodo que crea archivo .bak de respaldo
    public void createBackup() {
        String csvFilePath = "App2/Datos/BigMuscle.csv";
        String backupFilePath = "App2/Datos/BigMuscle.bak";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(backupFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Respaldo creado exitosamente: " + backupFilePath);
        } catch (IOException e) {
            System.err.println("Error al guardar respaldo: " + e.getMessage());
        }
    }

    // Metodo que crea los clientes y los agrega a la lista de clientes validando los atributos RUT, FECHAS y PLAN/SEDE
    public void leerRegistro() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("App2/Datos/BigMuscle.csv"), StandardCharsets.UTF_8))) {
            String line;
            int count = 0;
            // Se lee por cada linea del archivo csv
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                // Se crea un objeto cliente de la mano de su constructor
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);

              // Se elimina el guion del rut del cliente para una validacion mas sencilla
                cliente.setRut(cliente.getRut().replace("-", ""));

                if (count == 0) { // Omitir la primera línea del archivo ya que esta corresponde a los titulos de las columnas y no es necesario validarlo ya que no serian validos.
                    clientes.add(cliente);
                    count++;
                    continue;
                }
                // Se utiliza el metodo validarRut del objeto Cliente para rechazar la adicion del cliente en caso de que este sea invalido
                try {if (!cliente.validarRut()) {
                    System.out.println("El RUT " + cliente.getRut() + " es inválido, el cliente fue omitido");
                } else if (!cliente.validarFecha()){
                  // Se utiliza el metodo validarFechas del objeto Cliente para rechazar la adicion del cliente en caso de que no exista alguna de las fechas o la fecha hasta sea menor que la fecha desde.
                    System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, el cliente fue omitido");
                } else if (!cliente.validarPlanSede()) {
                  // Se utiliza el metodo validarPlanSede del objeto Cliente para rechazar la adicion del cliente en caso de que no exista un valor en Plan y/o Sede.
                    System.out.println("El plan " + cliente.getCod_plan() + " y/o la sede " + cliente.getCod_sede() + " son inválidos, el cliente fue omitido");
                }
                else {
                  //Se agrega al cliente a clientes en caso de que todas las validaciones anteriores hayan dado True
                    clientes.add(cliente);
                }
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    // Metodo para visualizar el registro de clientes.
    public void verClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("Total de clientes: " + clientes.size());
    }

    // Metodo para buscar un cliente dentro de clientes por su llave unica de RUT.
    public void buscarClientePorRUT(BufferedReader reader) {
        System.out.print("Ingrese el RUT del cliente a buscar: ");
        String rut;
        try {
            rut = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el nombre del cliente: " + e.getMessage());
            return;
        }
        boolean encontrado = false;
      // Se busca por todos los Clientes
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equalsIgnoreCase(rut)) {
                System.out.println(cliente);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún cliente con ese nombre");
        }
    }
    // Metodo para eliminar un cliente de clientes por su RUT
    public void eliminarCliente(BufferedReader reader) {
        System.out.print("Ingrese el RUT del cliente a eliminar: ");
        String rut;
        try {
            rut = reader.readLine();
        } catch (IOException e) {
        System.err.println("Error al leer el rut del cliente: " + e.getMessage());
        return;
        }
        Iterator<Cliente> iterator = clientes.iterator();
        boolean encontrado = false;
        // Itera por toda la lista clientes buscando el cliente con ese RUT y en ese caso eliminarlo con iterator.remove()
        while (iterator.hasNext()) {
        Cliente cliente = iterator.next();
        if (cliente.getRut().equalsIgnoreCase(rut)) {
        iterator.remove();
        System.out.println("Cliente eliminado: " + cliente);
        encontrado = true;
        }
        }
        if (!encontrado) {
        System.out.println("No se encontró ningún cliente con ese nombre");
        }
    } 
    
    // Metodo para agregar un Cliente con las validaciones correspondientes al RUT, Fechas y Plan/Sede
    public void agregarCliente(List<Plan> planes, List<Sede> sedes) {
        System.out.print("Ingrese el RUT del cliente (sin guion): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        // Primero se solicitan todos los datos sin verificacion
        String rut;
        try {
            rut = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el rut del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre;
        try {
            nombre = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el nombre del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese la edad del cliente: ");
        String edad;
        try {
            edad = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer la edad del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese el código del plan del cliente: ");
        System.out.println("Los planes disponibles son: ");
        String cod_plan;
        try {
            cod_plan = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el código del plan del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese la descripción del plan del cliente: ");
        String descripcion_plan;
        try {
            descripcion_plan = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer la descripción del plan del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese la fecha de inicio del plan del cliente (formato sin guion: YYYYMMDD): ");
        String desde;
        try {
            desde = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer la fecha de inicio del plan del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese la fecha de término del plan del cliente (formato sin guion: YYYYMMDD): ");
        String hasta;
        try {
            hasta = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer la fecha de término del plan del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese el código de la sede del cliente: ");
        String cod_sede;
        try {
            cod_sede = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el código de la sede del cliente: " + e.getMessage());
            return;
        }
        System.out.print("Ingrese la ubicación de la sede del cliente: ");
        String ubicacion_sede;
        try {
            ubicacion_sede = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer la ubicación de la sede del cliente: " + e.getMessage());
            return;
        }

        //Luego se crea un cliente con los datos sin validar para luego utilizar los metodos de validacion de la clase Cliente
        Cliente cliente = new Cliente(rut, nombre, edad, cod_plan, descripcion_plan, desde, hasta, cod_sede, ubicacion_sede);
        // Se valida el rut, las fechas y si existe el plan y sede.
        if (!cliente.validarRut()) {
            System.out.println("El RUT " + cliente.getRut() + " es inválido, el cliente fue omitido");
        } else if (!cliente.validarFecha()){
            System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, el cliente fue omitido");
        } else if (!cliente.validarPlanSede()) {
            System.out.println("El plan " + cliente.getCod_plan() + " y/o la sede " + cliente.getCod_sede() + " son inválidos, el cliente fue omitido");
        }
        else {
            // FInalmente si todo lo anterior se cumplio se valida que el RUT ingresado no pertenezca a otro usuario
            boolean planValido = false;
            boolean sedeValida = false;
            for (Cliente existingCliente : clientes) {
                if (rut.equals(existingCliente.getRut())) {
                    System.out.println("Un cliente con ese RUT ya existe");
                    return;
                }
            }
          // Ademas se verifica que el plan se encuentre dentro de la lista de planes vigentes y no sea uno inventado de la nada
            for (Plan plan : planes) {
                if (cod_plan.equals(plan.getCod_plan())) {
                    planValido = true;
                    break;
                }
            }
          // Ademas se verifica que la sede se encuentre dentro de la lista de sedes vigentes y no sea una inventada de la nada
            for (Sede sede : sedes) {
                if (cod_sede.equals(sede.getCod_sede())) {
                    sedeValida = true;
                    break;
                }
            }
            if (!planValido) {
                System.out.println("El plan " + cod_plan + " no existe");
                return;
            } else if (!sedeValida) {
                System.out.println("La sede " + cod_sede + " no existe");
                return;
            }
          // Finalmente si el nuevo objeto Cliente con sus atributos logro ser validado correctamente se agrega a la lista de clientes.
            clientes.add(cliente);
            System.out.println("Cliente agregado: " + cliente);
        }
    }
  // Metodo para editar un cliente existente de la lista clientes, buscado por su RUT
    public void editarCliente(List<Plan> planes, List<Sede> sedes) {
        System.out.print("Ingrese el RUT del cliente a editar (sin guion): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String rut;
        try {
            rut = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer el rut del cliente: " + e.getMessage());
            return;
        }
        boolean encontrado = false;
      // Se busca el cliente asociado a ese rut por toda la lista de clientes
        for (Cliente cliente : clientes) {
          // En el caso de ser encontrado se solicitan los nuevos datos
            if (rut.equals(cliente.getRut())) {
                encontrado = true;
                System.out.print("Ingrese el nuevo nombre del cliente (actual: " + cliente.getNombre() + "): ");
                String nombre;
                try {
                    nombre = reader.readLine();
                    cliente.setNombre(nombre);
                } catch (IOException e) {
                    System.err.println("Error al leer el nombre del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese la nueva edad del cliente (actual: " + cliente.getEdad() + "): ");
                String edad;
                try {
                    edad = reader.readLine();
                    cliente.setEdad(edad);
                } catch (IOException e) {
                    System.err.println("Error al leer la edad del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese el nuevo código del plan del cliente (actual: " + cliente.getCod_plan() + "): ");
                String cod_plan;
                try {
                  // En el caso de las variables que requieren validacion se hace uso de una variable auxiliar para reemplazar el valor y utlizar el metodo de validacion asociado al objeto Cliente, en este caso cod_plan
                    cod_plan = reader.readLine();
                    String aux = cliente.getCod_plan();
                    cliente.setCod_plan(cod_plan);
                    boolean planValido = false;
                  // Se verifica si el plan existe en la lista de planes
                    for (Plan plan : planes) {
                        if (cod_plan.equals(plan.getCod_plan())) {
                            planValido = true;
                            break;
                        }
                    }
                    if (!planValido) {
                      // En caso de no ser valido se regresa al valor original.
                        System.out.println("El plan " + cod_plan + " no existe, se mantuvo el plan " + aux);
                        cliente.setCod_plan(aux);
                    } else {
                      // Se mantiene el cambio en caso de exito.
                        System.out.println("Plan cambiado exitosamente");
                    }

                } catch (IOException e) {
                    System.err.println("Error al leer el código del plan del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese la nueva descripción del plan del cliente (actual: " + cliente.getDescripcion_plan() + "): ");
                String descripcion_plan;
                try {
                    descripcion_plan = reader.readLine();
                    cliente.setDescripcion_plan(descripcion_plan);
                } catch (IOException e) {
                    System.err.println("Error al leer la descripción del plan del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese la nueva fecha de inicio del plan del cliente (formato sin guion: YYYYMMDD) (actual: " + cliente.getDesde() + "): ");
                String desde;
                String hasta;
                try {
                  // En este caso se hace uso de dos variables aux para desde y hasta para hacer la misma logica que en cod_plan pero usando validarFechas(), metodo de Cliente.
                    desde = reader.readLine();
                    String aux_d = cliente.getDesde();
                    cliente.setDesde(desde);
                    System.out.print("Ingrese la nueva fecha de término del plan del cliente (formato sin guion: YYYYMMDD) (actual: " + cliente.getHasta() + "): ");
                    hasta = reader.readLine();
                    String aux_h = cliente.getHasta();
                    cliente.setHasta(hasta);
                    if (!cliente.validarFecha()) {
                        System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, se mantuvieron las fechas " + aux_d + " y " + aux_h);                  
                      // En caso de no ser validas, se regresa a los valores anteriores.
                        cliente.setDesde(aux_d);
                        cliente.setHasta(aux_h);
                    } else {
                      // En el caso de ser validadas de manera exitosa se mantiene el cambio
                        System.out.println("Fechas cambiadas exitosamente");
                    }

                } catch (IOException e) {
                    System.err.println("Error al leer la fecha de término o inicio del plan del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese el nuevo código de la sede del cliente (actual: " + cliente.getCod_sede() + "): ");
                String cod_sede;
                try {
                  // Se realiza la misma logica que en cod_plan pero con la lista de sedes.
                    cod_sede = reader.readLine();
                    String aux = cliente.getCod_sede();
                    cliente.setCod_sede(cod_sede);
                    boolean sedeValida = false;
                    for (Sede sede : sedes) {
                        if (cod_sede.equals(sede.getCod_sede())) {
                            sedeValida = true;
                            break;
                        }
                    }
                    if (!sedeValida) {
                        System.out.println("La sede " + cod_sede + " no existe, se mantuvo la sede " + aux);
                        cliente.setCod_sede(aux);
                    } else {
                        System.out.println("Sede cambiada exitosamente");
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el código de la sede del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese la nueva ubicación de la sede del cliente (actual: " + cliente.getUbicacion_sede() + "): ");
                String ubicacion_sede;
                try {
                    ubicacion_sede = reader.readLine();
                    cliente.setUbicacion_sede(ubicacion_sede);
                } catch (IOException e) {
                    System.err.println("Error al leer la ubicación de la sede del cliente: " + e.getMessage());
                    return;
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró un cliente con ese RUT");
            return;
        }
        
    }
  // Metodo que permite guardar el registro con un input del nombre del archivo deseado.
    public void guardarClientesEnArchivo(String nombreArchivo) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("App/Datos/"+nombreArchivo))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getRut() + "," + cliente.getNombre() + "," + cliente.getEdad() + "," +cliente.getCod_plan() + "," + cliente.getDescripcion_plan() + "," + cliente.getDesde() + "," +cliente.getHasta() + "," + cliente.getCod_sede() + "," + cliente.getUbicacion_sede());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los clientes en el archivo: " + e.getMessage());
        }
    }

  // Getter registro de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }
  // Setter registro de clientes
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}