package aed;
import java.util.ArrayList;
import java.util.Comparator;




public class Heap<T> {
    private ArrayList<HeapHandle<T>> heap;
    private Comparator<? super T> comparator;

    public Heap() {
        heap = new ArrayList<>();
        comparator = null;
    }

    public Heap(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public Heap(T[] array){
        heap = new ArrayList<>(array.length);
        comparator = null;
        int i = 0;
        while(i < array.length) {
            heap.add(new HeapHandle<>(array[i]));
            i++;
        }
        buildHeap();
    }

    public Heap(T[] array, Comparator<? super T> comparator) {
        heap = new ArrayList<>(array.length);
        this.comparator = comparator;
        int i = 0;
        while (i < array.length) {
            heap.add(new HeapHandle<>(array[i]));
            i++;
        }
        buildHeap();
    }

    private int padre(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2*i + 1;
    }

    private int right(int i) {
        return 2*i + 2;
    }

    public HeapHandle<T> encolar(T e) {
        if(e == null) {
            throw new NullPointerException("no se puede insertar valores nulos");
        }
        HeapHandle<T> handle = new HeapHandle<>(e);
        heap.add(handle);
        int index = heap.size() - 1;
        handle.setIndex(index);
        heapifyUp(index);
        return handle;

    }

    public T root(){
        if(heap.isEmpty()) {
            throw new IllegalStateException("Heap vacio");
        }

        return heap.get(0).getElement();
    }

    public T desencolar() {
        if(heap.isEmpty()) {
            throw new IllegalStateException("El heap esta vacio");
        }
        HeapHandle<T> rootHandle = heap.get(0);
        HeapHandle<T> lastHandleElement = heap.remove(heap.size() - 1);
        T rootElement = rootHandle.getElement();

        if(!heap.isEmpty()) {
            heap.set(0,lastHandleElement);
            lastHandleElement.setIndex(0);
            heapifyDown(0);
        }
        rootHandle.setIndex(-1);
        return rootElement;

    }

    public void cambiarPrioridad(HeapHandle<T> handle){
       int index = handle.getIndex();
        if (index != -1) {
            heapifyUp(index);
            heapifyDown(index);
        }
    }

    private void heapifyUp(int index) {
        if(index == 0) {
            return;
        }
        int padreIndex = padre(index);
        if (compare(heap.get(index).getElement(), heap.get(padreIndex).getElement()) > 0) {
            swap(index, padreIndex);
            heapifyUp(padreIndex);
        }
    }

    private void heapifyDown(int index) {
        int mayorIndex = index; // inicialmente asumimos que el nodo actual es el mayor
        int leftIndex = left(index);
        int rightIndex = right(index);

        // verifica si el hijo izquiero es mayor que el nodo actual
        if(leftIndex < heap.size() && compare(heap.get(leftIndex).getElement(), heap.get(mayorIndex).getElement()) > 0) {
            mayorIndex = leftIndex;
        }

        // verifica si el hijo derecho es mayor el nodo mas grande encontrado hasta ahora
        if(rightIndex < heap.size() && compare(heap.get(rightIndex).getElement(), heap.get(mayorIndex).getElement()) > 0) {
            mayorIndex = rightIndex;
        }

        if(mayorIndex != index) {
            swap(index, mayorIndex);
            heapifyDown(mayorIndex);
        }
    }

    private void buildHeap(){
        int ultimoNoHoja = (tamaño() / 2) - 1;
        for(int i = ultimoNoHoja; i >= 0; i-- ){
            heapifyDown(i);
        }
    }

    private void swap(int index1, int index2) {
        // Intercambiar los handles en el array
        HeapHandle<T> handle1 = heap.get(index1);
        HeapHandle<T> handle2 = heap.get(index2);

        heap.set(index1, handle2);
        heap.set(index2, handle1);

        // Actualizar los índices en los handles
        handle1.setIndex(index2);
        handle2.setIndex(index1);
    }

    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        } else {
            // Invertimos el orden para que el mayor tenga mayor prioridad
            return ((Comparable<? super T>) first).compareTo(second);
        }
    }
    public String toString() {
        return heap.toString();
    }

    public int tamaño(){
        return heap.size();
    }

    public boolean vacia(){
        return heap.size() == 0;
    }

}
