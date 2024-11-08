package aed.testObjects;

public class Contador {
    private int valor;

    public Contador(int valor) {
        this.valor = valor;
    }

    public void incrementar(){
        valor++;
    }

    public  void decrementar(){
        valor--;
    }

    public int getValor(){
        return valor;
    }

    public String toString(){
        return String.valueOf(valor);
    }
}
