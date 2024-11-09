package aed;

public class Main {
    public static void main(String[] args) {
        Traslado[] traslados = {
                new Traslado(1,3,5,50,6),
                new Traslado(2,3,8,60,5),
                new Traslado(3,3,6,2,1),
                new Traslado(4,2,6,800,2),
                new Traslado(4,2,6,200,1)
        };

        BestEffort empresa = new BestEffort(10, traslados);
        Traslado t1 = new Traslado(5,1,5,50,4);

        HeapHandle<Traslado> handle1 = empresa.masRedituables.enqueue(t1);

        t1.setHandleGanancia(handle1);


        System.out.println(t1.getId());
        System.out.println(empresa.masRedituables.consultarMax().getHandleGanancia().getIndex());


    






    }
}
