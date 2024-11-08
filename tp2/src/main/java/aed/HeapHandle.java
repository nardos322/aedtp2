package aed;

public class HeapHandle<T>{
    private T element;
    private int index;

    public HeapHandle(T element){
        this.element = element;
        this.index = -1;
    }

    public T getElement(){
        return element;
    }

    public int getIndex(){
        return index;
    }

    void setIndex(int index){
        this.index = index;
    }

    public String toString(){
        return String.valueOf(element);
    }
}
