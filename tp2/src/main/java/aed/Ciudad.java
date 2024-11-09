package aed;

public class Ciudad {
    private int id;
    private int gananciaTotal;
    private int perdidaTotal;
    private int superavit;

    public Ciudad(int id){
        this.id = id;
        gananciaTotal = 0;
        perdidaTotal = 0;
        superavit = 0;
    }

    public void agregarGanancia(int ganancia) {
        gananciaTotal += ganancia;
    }

    public void agregarPerdida(int perdida) {
        perdidaTotal += perdida;
    }

    public void superavit(){
        superavit = gananciaTotal - perdidaTotal;
    }

    public int getId(){ return id; }

    public int getGanancia(){
        return gananciaTotal;
    }

    public int getPerdida(){
        return perdidaTotal;
    }

    public int getSuperavit(){
        return superavit;
    }
}
