package aed;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Estadistica {
    Ciudad[] ciudades;
    ArrayList<Ciudad> ciudadesMayorGanancia;
    ArrayList<Ciudad> ciudadesMayorPerdida;
    PriorityQueue<Ciudad> mayorSuperavit;
    int totalGanancias;
    int totalTraslados;

    public Estadistica(int cantCiudades){
        ciudades = new Ciudad[cantCiudades];
        HeapHandle[] handleCiudades = new HeapHandle[cantCiudades];
        ciudadesMayorGanancia = new ArrayList<>(cantCiudades);
        ciudadesMayorPerdida = new ArrayList<>(cantCiudades);
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad(i);
            handleCiudades[i] = new HeapHandle<>(ciudades[i], i);
            ciudades[i].setHandleCiudad(handleCiudades[i]);
        }
        mayorSuperavit = new PriorityQueue<>(handleCiudades, new SuperavitComparador());
        totalGanancias = 0;
        totalTraslados = 0;
    }
    public void agregarTraslado(Traslado t){
        ciudades[t.getOrigen()].agregarGanancia(t.getGanancia());
        ciudades[t.getDestino()].agregarPerdida(t.getGanancia());
        ciudades[t.getOrigen()].setSuperavit();
        ciudades[t.getDestino()].setSuperavit();
        mayorSuperavit.updatePriority(ciudades[t.getOrigen()].getHandleCiudad());
        mayorSuperavit.updatePriority(ciudades[t.getDestino()].getHandleCiudad());
        if(ciudadesMayorGanancia.get(0) == null ){
            ciudadesMayorGanancia.add(ciudades[t.getOrigen()]);
        }
    }
}
