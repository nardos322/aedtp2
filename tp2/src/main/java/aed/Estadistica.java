package aed;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Estadistica {
    private Ciudad[] ciudades;
    private ArrayList<Integer> ciudadesMayorGanancia;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private PriorityQueue<Ciudad> mayorSuperavit;
    int totalGanancias;
    int totalTraslados;

    public Estadistica(int cantCiudades){
        ciudades = new Ciudad[cantCiudades];
        HeapHandle[] handleCiudades = new HeapHandle[cantCiudades];
        ciudadesMayorGanancia = new ArrayList<>();
        ciudadesMayorPerdida = new ArrayList<>();
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
        if(ciudadesMayorGanancia.isEmpty() || ciudades[ciudadesMayorGanancia.get(0)].getGanancia()  == ciudades[t.getOrigen()].getGanancia() && ciudadesMayorGanancia.get(0) != t.getOrigen() ){
            ciudadesMayorGanancia.add(ciudades[t.getOrigen()].getId());
        } else if (ciudades[ciudadesMayorGanancia.get(0)].getGanancia() <= ciudades[t.getOrigen()].getGanancia()) {
            ciudadesMayorGanancia.clear();
            ciudadesMayorGanancia.add(ciudades[t.getOrigen()].getId());
        }
        if(ciudadesMayorPerdida.isEmpty() || ciudades[ciudadesMayorPerdida.get(0)].getPerdida()  == ciudades[t.getDestino()].getPerdida() && ciudadesMayorPerdida.get(0) != t.getDestino()){
            ciudadesMayorPerdida.add(ciudades[t.getDestino()].getId());
        } else if (ciudades[ciudadesMayorPerdida.get(0)].getPerdida() <= ciudades[t.getDestino()].getPerdida()) {
            ciudadesMayorPerdida.clear();
            ciudadesMayorPerdida.add(ciudades[t.getDestino()].getId());
        }
        totalGanancias += t.getGanancia();
        totalTraslados += 1;

    }

    public ArrayList<Integer> getCiudadesMayorGanancia() {
        return ciudadesMayorGanancia;
    }

    public ArrayList<Integer> getCiudadesMayorPerdida() {
        return ciudadesMayorPerdida;
    }

    public int getCiudadMayorSuperavit(){
        return mayorSuperavit.consultarMax().getId();
    }

    public int promedioGanancia(){
        return totalGanancias / totalTraslados;
    }

}
