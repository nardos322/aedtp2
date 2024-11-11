package aed;

import java.util.ArrayList;

public class BestEffort {
    PriorityQueue<Traslado> masRedituables;
    PriorityQueue<Traslado> masAntiguos;
    Estadistica estadisticas;

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
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
