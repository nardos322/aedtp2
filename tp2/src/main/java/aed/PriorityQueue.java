package aed;

import java.util.Comparator;

public class PriorityQueue<T> {
    private Heap<T> elementos;
    private int tamaño;


    public PriorityQueue(HeapHandle[] array, Comparator<T> comparador) {
        elementos = new Heap<>(array, comparador);
        tamaño = array.length;
    }


    public HeapHandle<T> enqueue(T e) {
        HeapHandle<T> handle =  elementos.encolar(e);
        tamaño++;
        return handle;
    }

    public T consultarMax(){
        return elementos.root();
    }

    public T dequeueMax(){
        tamaño--;
        return elementos.desencolar();
    }

    public void updatePriority(HeapHandle<T> handle) {
        elementos.cambiarPrioridad(handle);
    }

    public void eliminar(HeapHandle<T> handle) {
        elementos.eliminar(handle);
        tamaño --;
    }

    public int size(){
        return tamaño;
    }


}
