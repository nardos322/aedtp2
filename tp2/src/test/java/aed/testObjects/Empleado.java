package aed.testObjects;

public class Empleado {
    private String nombre;
    private String departamento;
    private int edad;

    public Empleado(String nombre, String departamento, int edad) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
