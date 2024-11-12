package aed;

import java.util.ArrayList;

public class BestEffort {
    private PriorityQueue<Traslado> masRedituables;
    private PriorityQueue<Traslado> masAntiguos;
    private Estadistica estadisticas;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        HeapHandle<Traslado>[] handlesMasRedituables = new HeapHandle[traslados.length];
        HeapHandle<Traslado>[]handlesMasAntiguos = new HeapHandle[traslados.length];
        for (int i = 0; i < traslados.length; i++){
            handlesMasRedituables[i] = new HeapHandle<>(traslados[i], i);
            handlesMasAntiguos[i] = new HeapHandle<>(traslados[i], i);
        }
        for (int i = 0; i < handlesMasRedituables.length; i++){
            traslados[i].setHandleGanancia(handlesMasRedituables[i]);
        }
        for (int i = 0; i < handlesMasAntiguos.length; i++){
            traslados[i].setHandleAntiguedad(handlesMasAntiguos[i]);
        }
        masRedituables = new PriorityQueue<>(handlesMasRedituables, new MasRedituablesComparador());
        masAntiguos = new PriorityQueue<>(handlesMasAntiguos, new MasAntiguosComparador());
        estadisticas = new Estadistica(cantCiudades);

    }

    public void registrarTraslados(Traslado[] traslados){
        for(Traslado t : traslados){
            t.setHandleGanancia(masRedituables.enqueue(t));
            t.setHandleAntiguedad(masAntiguos.enqueue(t));
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] masRedituables = new int[n];
        int i = 0;
        while(i < n) {
            this.masAntiguos.eliminar(this.masRedituables.consultarMax().getHandleAntiguedad());
            estadisticas.agregarTraslado(this.masRedituables.consultarMax());
            masRedituables[i] = this.masRedituables.dequeueMax().getId();
            i++;

        }
        return masRedituables;
    }

    public int[] despacharMasAntiguos(int n){
        int[] masAntiguos = new int[n];
        int i = 0;
        while(i < n) {
            this.masRedituables.eliminar(this.masAntiguos.consultarMax().getHandleGanancia());
            estadisticas.agregarTraslado(this.masAntiguos.consultarMax());
            masAntiguos[i] = this.masAntiguos.dequeueMax().getId();
            i++;
        }
        return masAntiguos;
    }

    public int ciudadConMayorSuperavit(){
        return estadisticas.getCiudadMayorSuperavit();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return estadisticas.getCiudadesMayorGanancia();
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return estadisticas.getCiudadesMayorPerdida();
    }

    public int gananciaPromedioPorTraslado(){

        return estadisticas.promedioGanancia();
    }
    
}
