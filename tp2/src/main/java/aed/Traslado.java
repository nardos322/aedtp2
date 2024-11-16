package aed;

public class Traslado {
    private int id;
    private int origen;
    private int destino;
    private int gananciaNeta;
    private int timestamp;
    private HeapHandle<Traslado> handleGanancia;
    private HeapHandle<Traslado> handleAntiguedad;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
        handleGanancia = null;
        handleAntiguedad = null;
    }

    public int getId(){
        return id;
    }
    public int getOrigen(){
        return origen;
    }
    public int getDestino(){
        return destino;
    }
    public int getGanancia() {
        return gananciaNeta;
    }

    public int getTimestamp(){
        return timestamp;
    }

    public HeapHandle<Traslado> getHandleGanancia(){
        return handleGanancia;
    }

    public HeapHandle<Traslado> getHandleAntiguedad(){
        return handleAntiguedad;
    }

    public void setHandleGanancia(HeapHandle<Traslado> handleGanancia) {    //O(1)
        this.handleGanancia = handleGanancia;
    }

    public void setHandleAntiguedad(HeapHandle<Traslado> handleAntiguedad) {    //O(|)
        this.handleAntiguedad = handleAntiguedad;
    }

}
