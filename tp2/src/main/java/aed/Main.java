package aed;

public class Main {
    public static void main(String[] args) {
        Traslado[] traslados = {
                new Traslado(1,3,5,50,6),
                new Traslado(2,3,8,1000,8),
                new Traslado(3,3,6,2,-1),
                new Traslado(4,4,6,800,2),
                new Traslado(5,9,3,800,0),
        };

        BestEffort empresa = new BestEffort(10, traslados);
        Traslado t1 = new Traslado(6,1,5,50,50);
        Traslado t2 = new Traslado(7, 3,6,1000,4);
        Traslado t3 = new Traslado(8,1,3,500,6);
        Traslado[] traslados2 = {t1,t2,t3};

        int[] id = empresa.despacharMasAntiguos(10);
        System.out.println(id);
        empresa.registrarTraslados(traslados2);
        int[] id2 = empresa.despacharMasRedituables(10);
        System.out.println(id2);
        int[] id3 = empresa.despacharMasRedituables(4);
        System.out.println(id3);
/*        for(int t : empresa.despacharMasRedituables(5)) {
            System.out.println(t);
        }

        for(HeapHandle<Traslado> k : empresa.masAntiguos.elementos.heap) {
            System.out.println(k.getElement());
        }
        */

        //System.out.println(empresa.estadisticas.ciudades[3].getSuperavit());





    }
}
