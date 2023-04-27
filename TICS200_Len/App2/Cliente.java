package App2;
public class Cliente {
    private String rut;
    private String nombre_completo;
    private int edad;
    private String cod_plan;
    private String descripcion_plan;
    private String desde;
    private String hasta;
    private String cod_sede;
    private String ubicacion_sede;

    public String getRut() {
        return rut;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public int getEdad() {
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

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public void setEdad(int edad) {
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

    public boolean validarRut() {
        String rutSinDv = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        int suma = 0;
        int multiplicador = 2;
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
}