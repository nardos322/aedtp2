package aed;

import java.util.ArrayList;

public class BestEffort {
    private PriorityQueue<Traslado> masRedituables;
    private PriorityQueue<Traslado> masAntiguos;
    private Estadistica estadisticas;


    public BestEffort(int cantCiudades, Traslado[] traslados){                           // O(C + T)
        HeapHandle<Traslado>[] handlesMasRedituables = new HeapHandle[traslados.length];
        HeapHandle<Traslado>[]handlesMasAntiguos = new HeapHandle[traslados.length];
        for (int i = 0; i < traslados.length; i++){                                          //O(T)
            handlesMasRedituables[i] = new HeapHandle<>(traslados[i], i);
            handlesMasAntiguos[i] = new HeapHandle<>(traslados[i], i);
        }
        for (int i = 0; i < handlesMasRedituables.length; i++){                            //O(T)
            traslados[i].setHandleGanancia(handlesMasRedituables[i]);
        }
        for (int i = 0; i < handlesMasAntiguos.length; i++){                               //O(T)
            traslados[i].setHandleAntiguedad(handlesMasAntiguos[i]);
        }
        masRedituables = new PriorityQueue<>(handlesMasRedituables, new MasRedituablesComparador());  //O(T)
        masAntiguos = new PriorityQueue<>(handlesMasAntiguos, new MasAntiguosComparador());          //O(T)
        estadisticas = new Estadistica(cantCiudades);                                                //O(C)

    }

    public void registrarTraslados(Traslado[] traslados){          //O(T*Log(T))
        for(Traslado t : traslados){                               //O(T)
            t.setHandleGanancia(masRedituables.enqueue(t));        //O(log(T))
            t.setHandleAntiguedad(masAntiguos.enqueue(t));         //O(log(T))
        }
    }

    public int[] despacharMasRedituables(int n){                     //O(n*(log(C) + log(T))
        int[] masRedituables = new int[n];                           //O(n)
        int tamañoInicial = this.masRedituables.size();              //O(1)
        int i = 0;                                                   //O(1)
        while(i < n) {                                               //O(n)
            if(i < tamañoInicial) {                                 //O(1)
                this.masAntiguos.eliminar(this.masRedituables.consultarMax().getHandleAntiguedad());  //O(Log(T))
                estadisticas.agregarTraslado(this.masRedituables.consultarMax());                    //O(Log(C))
                masRedituables[i] = this.masRedituables.dequeueMax().getId();                       //O(Log(T))
            }
            i++;


        }
        return masRedituables;
    }

    public int[] despacharMasAntiguos(int n){       //igual al antertior es analoga O(n*(log(C) + log(T))
        int[] masAntiguos = new int[n];
        int tamañoInicial = this.masAntiguos.size();
        int i = 0;
        while(i < n) {
            if (i < tamañoInicial) {
                this.masRedituables.eliminar(this.masAntiguos.consultarMax().getHandleGanancia());
                estadisticas.agregarTraslado(this.masAntiguos.consultarMax());
                masAntiguos[i] = this.masAntiguos.dequeueMax().getId();
            }
            i++;
        }
        return masAntiguos;
    }

    public int ciudadConMayorSuperavit(){
        return estadisticas.getCiudadMayorSuperavit();
    }  //O(1)

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return estadisticas.getCiudadesMayorGanancia();
    }  //O(1)

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return estadisticas.getCiudadesMayorPerdida();
    }    //O(1)

    public int gananciaPromedioPorTraslado(){          //O(1)

        return estadisticas.promedioGanancia();
    }
    
}
