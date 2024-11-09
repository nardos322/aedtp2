package aed;

import java.util.ArrayList;

public class Estadistica {
    private Ciudad[] ciudades;
    private ArrayList<Ciudad> ciudadesMayorGanancia;
    private ArrayList<Ciudad> ciudadesMayorPerdida;
    private PriorityQueue<Ciudad> mayorSuperavit;
    private int totalGanancias;
    private int totalTraslados;

    public Estadistica(int cantCiudades){
        ciudades = new Ciudad[cantCiudades];
        ciudadesMayorGanancia = new ArrayList<>(cantCiudades);
        ciudadesMayorPerdida = new ArrayList<>(cantCiudades);
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad(i);
        }
        mayorSuperavit = new PriorityQueue<>(ciudades, new SuperavitComparador());
        totalGanancias = 0;
        totalTraslados = 0;
    }
    public void agregarTraslado(Traslado t){
        ciudades[t.getOrigen()].agregarGanancia(t.getGanancia());
        ciudades[t.getDestino()].agregarPerdida(t.getGanancia());
    }
}
