package aed;

import java.util.ArrayList;

public class BestEffort {
    PriorityQueue<Traslado> masRedituables;
    PriorityQueue<Traslado> masAntiguos;
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


    }

    public void registrarTraslados(Traslado[] traslados){
        for(Traslado t : traslados){
            t.setHandleGanancia(masRedituables.enqueue(t));
            t.setHandleAntiguedad(masAntiguos.enqueue(t));
        }
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
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
