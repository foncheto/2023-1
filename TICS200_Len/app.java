class Persona {
    String nombre;
    String apellido;
    int edad;
    

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
    Persona persona = new Persona("Juan", "Perez", 25);
    System.out.println(persona);
    persona.setNombre("Pedro");
    persona.setApellido("Gomez");
    persona.setEdad(30);
    System.out.println(persona);
    System.out.println(persona.toString());
}
}