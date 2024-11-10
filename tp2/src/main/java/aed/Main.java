package aed;

public class Main {
    public static void main(String[] args) {
        Integer[] a = {1,5,6,3};
        Heap<Integer> heap = new Heap<>(a);
        Traslado[] traslados = {
                new Traslado(1,3,5,50,6),
                new Traslado(2,3,8,6000,8),
                new Traslado(3,3,6,2,-1),
                new Traslado(4,2,6,800,2),
                new Traslado(5,4,9,200,0),
        };

        BestEffort empresa = new BestEffort(10, traslados);
        Traslado t1 = new Traslado(6,1,5,50,50);
        Traslado t2 = new Traslado(7, 3,6,4502,4);

        HeapHandle<Traslado> handle1 = empresa.masRedituables.enqueue(t1);
        HeapHandle<Traslado> handle2 = empresa.masAntiguos.enqueue(t1);
        t2.setHandleGanancia(empresa.masRedituables.enqueue(t2));
        //empresa.masRedituables.consultarMax().getHandleGanancia().setIndex(0);
        /*for(HeapHandle<Traslado> i : empresa.masAntiguos.elementos.heap){
            System.out.println(i.getIndex());
        }*/

        for(HeapHandle<Traslado> t : empresa.masRedituables.elementos.heap){
            System.out.println(t);
        }
       // System.out.println(empresa.masRedituables.elementos.heap.get(2).getElement());
     //   System.out.println(empresa.masAntiguos.consultarMax());

    






    }
}
