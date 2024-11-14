package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BestEffortTestsPropios {
    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 10;
        listaTraslados = new Traslado[] {
                new Traslado(1,3,5,50,6),
                new Traslado(2,3,8,1000,8),
                new Traslado(3,3,6,2000,0),
                new Traslado(4,4,6,800,2),
                new Traslado(5,9,3,800,0),
        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    //Tests del orden en que se despachan los traslados mas redituables

    @Test

    void despachar_con_mas_ganancia_de_a_uno_simple(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(2, 0, 1, 400, 20),
                new Traslado(3, 3, 4, 500, 50),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{3}, sis.despacharMasRedituables(1));
        assertArrayEquals(new int[]{2}, sis.despacharMasRedituables(1));
        assertArrayEquals(new int[]{1}, sis.despacharMasRedituables(1));

    }

    @Test

    void despachar_con_mas_ganancia_de_a_uno_con_misma_ganancia(){

        cantCiudades = 4;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(6, 1, 2, 100, 20),
                new Traslado(3, 2, 3, 100, 50),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{1}, sis.despacharMasRedituables(1));
        assertArrayEquals(new int[]{3}, sis.despacharMasRedituables(1));
        assertArrayEquals(new int[]{6}, sis.despacharMasRedituables(1));

    }

    @Test

    void despachar_con_mas_ganancia_de_a_varios_simple(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 600, 10),
                new Traslado(2, 1, 2, 150, 20),
                new Traslado(3, 2, 3, 200, 50),
                new Traslado(4, 3, 4, 500, 15),
                new Traslado(5, 4, 2, 50, 25),
                new Traslado(6, 3, 0, 100, 5),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{1,4,3}, sis.despacharMasRedituables(3));
        assertArrayEquals(new int[]{2,6}, sis.despacharMasRedituables(2));
        assertArrayEquals(new int[]{5}, sis.despacharMasRedituables(1));



    }

    @Test

    void despachar_con_mas_ganancia_de_a_varios_con_igual_ganancia(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(2, 1, 2, 150, 20),
                new Traslado(3, 2, 3, 100, 50),
                new Traslado(4, 3, 4, 500, 1),
                new Traslado(5, 4, 2, 150, 25),
                new Traslado(6, 3, 0, 100, 5),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{4,2}, sis.despacharMasRedituables(2));
        assertArrayEquals(new int[]{5,1}, sis.despacharMasRedituables(2));
        assertArrayEquals(new int[]{3}, sis.despacharMasRedituables(1));
        assertArrayEquals(new int[]{6}, sis.despacharMasRedituables(1));



    }

    @Test

    void despachar_con_mas_ganancia_con_n_mayor_a_cantTraslados(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 500, 10),
                new Traslado(2, 1, 2, 150, 20),
                new Traslado(3, 2, 3, 1100, 50),
                new Traslado(4, 3, 4, 550, 15),
                new Traslado(5, 4, 2, 150, 25),
                new Traslado(6, 3, 0, 100, 5),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{3,4,1,2,5,6}, sis.despacharMasRedituables(9));
    }

    @Test

    //Tests del orden de como se despachan los traslados mas antiguos

    void despachar_mas_viejo_de_a_uno_simple(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 100),
                new Traslado(2, 0, 1, 400, 20),
                new Traslado(3, 3, 4, 500, 50),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{2}, sis.despacharMasAntiguos(1));
        assertArrayEquals(new int[]{3}, sis.despacharMasAntiguos(1));
        assertArrayEquals(new int[]{1}, sis.despacharMasAntiguos(1));

    }

    @Test

    void despachar_mas_viejo_de_a_varios_simple(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 100),
                new Traslado(2, 2, 1, 400, 200),
                new Traslado(3, 3, 4, 500, 50),
                new Traslado(4, 4, 1, 100, 150),
                new Traslado(5, 3, 1, 400, 20),
                new Traslado(6, 3, 2, 500, 500),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{5,3,1}, sis.despacharMasAntiguos(3));
        assertArrayEquals(new int[]{4}, sis.despacharMasAntiguos(1));
        assertArrayEquals(new int[]{2,6}, sis.despacharMasAntiguos(2));

    }

    @Test

    void despachar_mas_viejo_con_n_mayor_a_cantTraslados(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 500, 10),
                new Traslado(2, 1, 2, 150, 20),
                new Traslado(3, 2, 3, 1100, 50),
                new Traslado(4, 3, 4, 550, 15),
                new Traslado(5, 4, 2, 150, 25),
                new Traslado(6, 3, 0, 100, 5),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{6,1,4,2,5,3}, sis.despacharMasAntiguos(19));
    }


    //Tests de despacho y ver como se actualizan las estadisticas
    @Test
    void despachar_masAntiguosyMasRedituables(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        Traslado[] nuevos = {
                new Traslado(6,1,5,50,50),
                new Traslado(7,3,6,1000,4),
                new Traslado(8,1,3, 500, 6),
                new Traslado(9,1,4,5000, 5),
                new Traslado(10,8,1,4000,10)
        };

        assertArrayEquals(new int[]{3,2,4}, sis.despacharMasRedituables(3));
        sis.registrarTraslados(nuevos);
        assertArrayEquals(new int[]{9,10}, sis.despacharMasRedituables(2));
        assertArrayEquals(new int[]{5,7}, sis.despacharMasAntiguos(2));

        assertEquals(3, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorPerdida());

        Traslado[] nuevos2 = {
                new Traslado(11,5,2,200,20),
                new Traslado(12,7,5,1000,9),
                new Traslado(13,2,9,6000,30),
                new Traslado(14,9,0,300,14)
        };

        assertArrayEquals(new int[]{1,8}, sis.despacharMasAntiguos(2));
        assertEquals(8,sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorPerdida());

    }

    @Test
    void despachar_todosMasRedituables() {
        Traslado[] traslados = {
                new Traslado(1,2,4,50,10),
                new Traslado(2,1,3,1000,2),
                new Traslado(3,3,2,2000,5),
                new Traslado(4,7,8,800,0),
                new Traslado(5,9,5,500,1)
        };

        BestEffort empresa = new BestEffort(10,traslados);

        assertArrayEquals(new int[]{3,2,4,5,1}, empresa.despacharMasRedituables(5));
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), empresa.ciudadesConMayorPerdida());
    }

    @Test
    void despachar_todosMasAntiguos(){
        Traslado[] traslados = {
                new Traslado(1,2,4,50,10),
                new Traslado(2,1,3,1000,2),
                new Traslado(3,3,2,2000,5),
                new Traslado(4,7,8,800,0),
                new Traslado(5,9,5,500,1)
        };

        BestEffort empresa = new BestEffort(10,traslados);

        assertArrayEquals(new int[]{4,5,2,3,1}, empresa.despacharMasAntiguos(5));
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), empresa.ciudadesConMayorPerdida());
    }

    @Test
    void ciudadesMismaGananciaOPerdidaOSuperavit() {
        Traslado[] traslados = {
                new Traslado(1,1,5,1000,2),
                new Traslado(2,2,4,500,6),
                new Traslado(3,4,0,800,10),
                new Traslado(4,2,3,500,20),
                new Traslado(5,7,2,1000,7)
        };

        BestEffort empresa = new BestEffort(10, traslados);
        assertArrayEquals(new int[]{1,2,5,3,4}, empresa.despacharMasAntiguos(5));
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,7,2)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5,2)), empresa.ciudadesConMayorPerdida());



    }

    @Test
    void despacharMasTrasladosDeLosQueTengo(){

        Traslado[] traslados = {
                new Traslado(1,1,5,1000,2),
                new Traslado(2,2,4,500,6),
                new Traslado(3,4,0,800,10),
                new Traslado(4,2,3,500,20),
                new Traslado(5,7,2,1000,7)
        };

        BestEffort empresa = new BestEffort(10, traslados);
        assertArrayEquals(new int[]{1,2,5,3,4}, empresa.despacharMasAntiguos(10));
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,7,2)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5,2)), empresa.ciudadesConMayorPerdida());
        assertArrayEquals(new int[]{} , empresa.despacharMasRedituables(10));
    }


    @Test
    void despacharTrasladosConMismaGananciaNeta() {
        Traslado[] traslados = {
                new Traslado(1,1,5,1000,2),
                new Traslado(2,2,4,1000,6),
                new Traslado(3,4,0,1000,10),
                new Traslado(4,8,3,1000,20),
                new Traslado(5,7,2,1000,7)
        };

        BestEffort empresa = new BestEffort(10, traslados);
        assertArrayEquals(new int[]{1,2,3,4,5}, empresa.despacharMasRedituables(10));
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,7,2,4,8)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5,4,2,0,3)), empresa.ciudadesConMayorPerdida());
    }



    @Test
    void despachary_ver_otras_variables(){

        cantCiudades = 5;
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 10, 10),
                new Traslado(2, 1, 2, 400, 20),
                new Traslado(3, 2, 3, 500, 50),
                new Traslado(4, 3,4, 50, 15),
                new Traslado(5, 4, 3, 450, 25),
                new Traslado(6, 3, 2, 450, 5),
        };

        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        assertArrayEquals(new int[]{3,5}, sis.despacharMasRedituables(2));
        assertEquals( 2, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        assertEquals( 475, sis.gananciaPromedioPorTraslado());

        assertArrayEquals(new int[]{6}, sis.despacharMasAntiguos(1));
        assertEquals( 4, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        assertEquals( 466, sis.gananciaPromedioPorTraslado());

        assertArrayEquals(new int[]{2}, sis.despacharMasRedituables(1));
        assertEquals( 4, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        assertEquals( 450, sis.gananciaPromedioPorTraslado());

        assertArrayEquals(new int[]{1,4}, sis.despacharMasAntiguos(2));
        assertEquals( 4, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(2,3)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        assertEquals( 310, sis.gananciaPromedioPorTraslado());

    }


    @Test
    void stressTest() {
        int cantCiudades = 1000;
        int cantTraslados = 100000;
        Traslado[] traslados = new Traslado[cantTraslados];

        for (int i = 0; i < cantTraslados; i++) {
            traslados[i] = new Traslado(i, i % cantCiudades, (i + 1) % cantCiudades, (int) (Math.random() * 1000), (int) (Math.random() * 1000));
        }

        BestEffort sis = new BestEffort(cantCiudades, traslados);

        long startTime = System.currentTimeMillis();
        int[] despachadosRedituables = sis.despacharMasRedituables(50000);
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo para despachar 50000 traslados más redituables: " + (endTime - startTime) + " ms");

        // Verificar el orden de los traslados más redituables
        for (int i = 1; i < despachadosRedituables.length; i++) {
            assertTrue(traslados[despachadosRedituables[i-1]].getGanancia() >= traslados[despachadosRedituables[i]].getGanancia(),
                    "El orden de los traslados más redituables no se mantiene.");
            if(traslados[despachadosRedituables[i-1]].getGanancia() == traslados[despachadosRedituables[i]].getGanancia()){
                assertTrue(traslados[despachadosRedituables[i-1]].getId() < traslados[despachadosRedituables[i]].getId());
            }
        }

        startTime = System.currentTimeMillis();
        int[] despachadosAntiguos = sis.despacharMasAntiguos(50000);
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo para despachar 50000 traslados más antiguos: " + (endTime - startTime) + " ms");

        // Verificar el orden de los traslados más antiguos
        for (int i = 1; i < despachadosAntiguos.length; i++) {
            assertTrue(traslados[despachadosAntiguos[i-1]].getTimestamp() <= traslados[despachadosAntiguos[i]].getTimestamp(),
                    "El orden de los traslados más antiguos no se mantiene.");
        }

        startTime = System.currentTimeMillis();
        sis.ciudadConMayorSuperavit();
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo para obtener ciudad con mayor superávit: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sis.ciudadesConMayorGanancia();
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo para obtener ciudades con mayor ganancia: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sis.ciudadesConMayorPerdida();
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo para obtener ciudades con mayor pérdida: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sis.gananciaPromedioPorTraslado();
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo para obtener ganancia promedio por traslado: " + (endTime - startTime) + " ms");
    }
}
