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

        sis.despacharMasRedituables(3);
        sis.registrarTraslados(nuevos);
        sis.despacharMasRedituables(2);
        sis.despacharMasAntiguos(2);

        assertEquals(3, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorPerdida());

        Traslado[] nuevos2 = {
                new Traslado(11,5,2,200,20),
                new Traslado(12,7,5,1000,9),
                new Traslado(13,2,9,6000,30),
                new Traslado(14,9,0,300,14)
        };

        sis.despacharMasAntiguos(2);
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

        empresa.despacharMasRedituables(5);
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

        empresa.despacharMasAntiguos(5);
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
        empresa.despacharMasAntiguos(5);
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
        empresa.despacharMasAntiguos(10);
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,7,2)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5,2)), empresa.ciudadesConMayorPerdida());

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
        empresa.despacharMasAntiguos(10);
        assertEquals(1, empresa.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,7,2,4,8)), empresa.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5,4,2,0,3)), empresa.ciudadesConMayorPerdida());
    }
}
