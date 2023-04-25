class Persona {
    String nombre;
    String apellido;
    int edad;
    int rut;

    public Persona(String nombre, String apellido, int edad, int rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public int getRut() {
        return rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    @Override

    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                '}';
    }

public static void main(String[] args) {
    Persona persona = new Persona("Juan", "Perez", 25, 12345678);
    System.out.println(persona);
    persona.setNombre("Pedro");
    persona.setApellido("Gomez");
    persona.setEdad(30);
    System.out.println(persona);
    System.out.println(persona.toString());
}
}