package aed;



public class Main {
    public static class SimpleLCG {
        private static long seed;

        public SimpleLCG(long seed) {
            this.seed = seed;
        }

        public static int nextInt(int n) {
            long a = 11531;
            long c = 1243565;
            long m = (1L << 32);
            seed = (a * seed + c) % m;
            return (int)(seed % n);
        }

    }


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
        int n = 100;
        long inicio = 1L;
        SimpleLCG generator = new SimpleLCG(inicio);

        for(int i = 0; i < 20; i++){
            int numberRandom = generator.nextInt(n);
            System.out.println("el numero aleatorio es " + numberRandom);
        }


   /*     for(int i = 0; i < 10; i++ ){
            int y = Main.SimpleLCG.nextInt(100);
            System.out.println(y);
        }*/

    }
}
