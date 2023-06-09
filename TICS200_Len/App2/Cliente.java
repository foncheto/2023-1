// Definimos la clase clientes, con sus respectivos atributos
package App2;
public class Cliente {
    private String rut;
    private String nombre_completo;
    private String edad;
    private String cod_plan;
    private String descripcion_plan;
    private String desde;
    private String hasta;
    private String cod_sede;
    private String ubicacion_sede;
    
    public Cliente(String rut, String nombre_completo, String edad, String cod_plan, String descripcion_plan, String desde, String hasta, String cod_sede, String ubicacion_sede) {
//definimos los nombres de casa variable
        this.rut = rut;
        this.nombre_completo = nombre_completo;
        this.edad = edad;
        this.cod_plan = cod_plan;
        this.descripcion_plan = descripcion_plan;
        this.desde = desde;
        this.hasta = hasta;
        this.cod_sede = cod_sede;
        this.ubicacion_sede = ubicacion_sede;
    }
// Metodo getter cada variable
    public String getRut() {
        return rut;
    }
    
    public String getNombre() {
        return nombre_completo;
    }
    
    public String getEdad() {
        return edad;
    }

    public String getCod_plan() {
        return cod_plan;
    }

    public String getDescripcion_plan() {
        return descripcion_plan;
    }

    public String getDesde() {
        return desde;
    }

    public String getHasta() {
        return hasta;
    }

    public String getCod_sede() {
        return cod_sede;
    }
    
    public String getUbicacion_sede() {
        return ubicacion_sede;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
//Metodo setter de los atributos
    public void setNombre(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setCod_plan(String cod_plan) {
        this.cod_plan = cod_plan;
    }

    public void setDescripcion_plan(String descripcion_plan) {
        this.descripcion_plan = descripcion_plan;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public void setCod_sede(String cod_sede) {
        this.cod_sede = cod_sede;
    }

    public void setUbicacion_sede(String ubicacion_sede) {
        this.ubicacion_sede = ubicacion_sede;
    }

    @Override
//luego creamos un string que tenga todas las variables separadas por "-"
    public String toString() {
        return rut + " - " + nombre_completo + " - " + edad + " - " + cod_plan + " - " + descripcion_plan + " - " + desde + " - " + hasta + " - " + cod_sede + " - " + ubicacion_sede;
    }
//creamos un metodo que valide el rut, creando primero el rut sin digito verificador, y despues verificamos su longitud.
    public boolean validarRut() {
        String rutSinDv = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        int suma = 0;
        int multiplicador = 2;

        if (rutSinDv.length() < 7 || rutSinDv.length() > 9) {
            return false;
        }

        for (int i = rutSinDv.length() - 1; i >= 0; i--) {
            int digito = Integer.parseInt(rutSinDv.substring(i, i + 1));
            suma += digito * multiplicador;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int resto = suma % 11;
        int resultado = 11 - resto;
        String dvCalculado = "";
        if (resultado == 11) {
            dvCalculado = "0";
        } else if (resultado == 10) {
            dvCalculado = "K";
        } else {
            dvCalculado = String.valueOf(resultado);
        }

        return dvCalculado.charAt(0) == dv;
    }
//validamos las fechas , primero sacando el guion del formato "YYYY-MM-DD", quedando 
// "YYYYMMDD"y la convertimos en un numero haciendo que la resta de eso verifique si desde es antes que hasta 
    public boolean validarFecha() {
        try {
            Integer.parseInt(desde.replace("-", ""));
            Integer.parseInt(hasta.replace("-", ""));
        } catch (NumberFormatException e) {
            return false;
        }
        
        int fecha = Integer.parseInt(desde.replace("-", ""));
        int fecha2 = Integer.parseInt(hasta.replace("-", ""));
        
        return fecha<=fecha2;
    }
// validacion de plan y sede
    public boolean validarPlanSede() {
        String plan = cod_plan;
        String sede = cod_sede;
        
        return !plan.equals("Vacio") && !sede.equals("Vacio");
    }
}
