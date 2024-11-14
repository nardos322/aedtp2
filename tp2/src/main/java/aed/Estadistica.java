package aed;

import java.util.ArrayList;


public class Estadistica {
    private Ciudad[] ciudades;
    private ArrayList<Integer> ciudadesMayorGanancia;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private PriorityQueue<Ciudad> mayorSuperavit;
    private int totalGanancias;
    private int totalTraslados;

    public Estadistica(int cantCiudades){  //O(c)
        ciudades = new Ciudad[cantCiudades];             //O(c)
        HeapHandle[] handleCiudades = new HeapHandle[cantCiudades];   //O(c)
        ciudadesMayorGanancia = new ArrayList<>();             //O(1)
        ciudadesMayorPerdida = new ArrayList<>();            //O(1)
        for (int i = 0; i < cantCiudades; i++) {           //O(c)
            ciudades[i] = new Ciudad(i);                 //O(1)
            handleCiudades[i] = new HeapHandle<>(ciudades[i], i);  //O(1)
            ciudades[i].setHandleCiudad(handleCiudades[i]);      //O(1)
        }
        mayorSuperavit = new PriorityQueue<>(handleCiudades, new SuperavitComparador());  //O(c)  por floyd
        totalGanancias = 0;
        totalTraslados = 0;
    }
    public void agregarTraslado(Traslado t){   //O(log(C))
        ciudades[t.getOrigen()].agregarGanancia(t.getGanancia());     //O(1)
        ciudades[t.getDestino()].agregarPerdida(t.getGanancia());      //O(1)
        ciudades[t.getOrigen()].setSuperavit();                         //O(1)
        ciudades[t.getDestino()].setSuperavit();                        //O(1)
        mayorSuperavit.updatePriority(ciudades[t.getOrigen()].getHandleCiudad());  //O(log(C))
        mayorSuperavit.updatePriority(ciudades[t.getDestino()].getHandleCiudad());  //O(log(C))
        if(ciudadesMayorGanancia.isEmpty() || ciudades[ciudadesMayorGanancia.get(0)].getGanancia()  == ciudades[t.getOrigen()].getGanancia() && ciudadesMayorGanancia.get(0) != t.getOrigen() ){  //O(1)
            ciudadesMayorGanancia.add(ciudades[t.getOrigen()].getId());     //O(1)
        } else if (ciudades[ciudadesMayorGanancia.get(0)].getGanancia() <= ciudades[t.getOrigen()].getGanancia()) {  //O(1)
            ciudadesMayorGanancia.clear();          //O(1)
            ciudadesMayorGanancia.add(ciudades[t.getOrigen()].getId());     // O(1)
        }
        //complejidad analaga al if  anterior
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
        return ciudadesMayorGanancia;             //O(1)
    }

    public ArrayList<Integer> getCiudadesMayorPerdida() {
        return ciudadesMayorPerdida;          //O(1)
    }

    public int getCiudadMayorSuperavit(){
        return mayorSuperavit.consultarMax().getId();      //O(1)
    }

    public int promedioGanancia(){
        return totalGanancias / totalTraslados;        //O(1)
    }

}
