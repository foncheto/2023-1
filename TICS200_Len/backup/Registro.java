package App;

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
    List<Cliente> clientes = new ArrayList<>();

    public void createBackup() {
        String csvFilePath = "App/Datos/BigMuscle.csv";
        String backupFilePath = "App/Datos/BigMuscle.bak";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(backupFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Backup created successfully: " + backupFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred while creating the backup: " + e.getMessage());
        }
    }

    
    public void leerRegistro() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("App/Datos/BigMuscle.csv"), StandardCharsets.UTF_8))) {
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
        }
    }

    public void verClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("Total de clientes: " + clientes.size());
    }

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

    public void agregarCliente(List<Plan> planes, List<Sede> sedes) {
        System.out.print("Ingrese el RUT del cliente (sin guion): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

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
        Cliente cliente = new Cliente(rut, nombre, edad, cod_plan, descripcion_plan, desde, hasta, cod_sede, ubicacion_sede);
        if (!cliente.validarRut()) {
            System.out.println("El RUT " + cliente.getRut() + " es inválido, el cliente fue omitido");
        } else if (!cliente.validarFecha()){
            System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, el cliente fue omitido");
        } else if (!cliente.validarPlanSede()) {
            System.out.println("El plan " + cliente.getCod_plan() + " y/o la sede " + cliente.getCod_sede() + " son inválidos, el cliente fue omitido");
        }
        else {
            boolean planValido = false;
            boolean sedeValida = false;
            for (Cliente existingCliente : clientes) {
                if (rut.equals(existingCliente.getRut())) {
                    System.out.println("Un cliente con ese RUT ya existe");
                    return;
                }
            }
            for (Plan plan : planes) {
                if (cod_plan.equals(plan.getCod_plan())) {
                    planValido = true;
                    break;
                }
            }
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
            clientes.add(cliente);
            System.out.println("Cliente agregado: " + cliente);
        }
    }

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
        for (Cliente cliente : clientes) {
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
                    cod_plan = reader.readLine();
                    String aux = cliente.getCod_plan();
                    cliente.setCod_plan(cod_plan);
                    boolean planValido = false;
                    for (Plan plan : planes) {
                        if (cod_plan.equals(plan.getCod_plan())) {
                            planValido = true;
                            break;
                        }
                    }
                    if (!planValido) {
                        System.out.println("El plan " + cod_plan + " no existe, se mantuvo el plan " + aux);
                        cliente.setCod_plan(aux);
                    } else {
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
                    desde = reader.readLine();
                    String aux_d = cliente.getDesde();
                    cliente.setDesde(desde);
                    System.out.print("Ingrese la nueva fecha de término del plan del cliente (formato sin guion: YYYYMMDD) (actual: " + cliente.getHasta() + "): ");
                    hasta = reader.readLine();
                    String aux_h = cliente.getHasta();
                    cliente.setHasta(hasta);
                    if (!cliente.validarFecha()) {
                        System.out.println("Las fechas " + cliente.getDesde() +" y/o " + cliente.getHasta() + " son inválidas, se mantuvieron las fechas " + aux_d + " y " + aux_h);
                        cliente.setDesde(aux_d);
                        cliente.setHasta(aux_h);
                    } else {
                        System.out.println("Fechas cambiadas exitosamente");
                    }

                } catch (IOException e) {
                    System.err.println("Error al leer la fecha de término o inicio del plan del cliente: " + e.getMessage());
                    return;
                }
                System.out.print("Ingrese el nuevo código de la sede del cliente (actual: " + cliente.getCod_sede() + "): ");
                String cod_sede;
                try {
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}