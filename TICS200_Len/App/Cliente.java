
package App;
public class Cliente {
    private String nombre;
    private String email;
    private String telefono;
    private String cod_plan;
    private String descripcion_plan;
    private String desde;
    private String hasta;
    private String cod_sede;
    private String ubicacion_sede;
    
    public Cliente(String nombre, String email, String telefono, String cod_plan, String descripcion_plan, String desde, String hasta, String cod_sede, String ubicacion_sede) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.cod_plan = cod_plan;
        this.descripcion_plan = descripcion_plan;
        this.desde = desde;
        this.hasta = hasta;
        this.cod_sede = cod_sede;
        this.ubicacion_sede = ubicacion_sede;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTelefono() {
        return telefono;
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

    @Override
    public String toString() {
        return nombre + " - " + email + " - " + telefono + " - " + cod_plan + " - " + descripcion_plan + " - " + desde + " - " + hasta + " - " + cod_sede + " - " + ubicacion_sede;
    }
}
