package aed.testObjects;

public class Tarjeta implements Comparable<Tarjeta> {
    private String numero;
    private int prioridad;

    public Tarjeta(String numero, int prioridad){
        this.numero = numero;
        this.prioridad = prioridad;

    }
    public String getNumero() {
        return numero;
    }

    public int getPrioridad() {
        return prioridad;
    }
    public int compareTo(Tarjeta otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }
}
