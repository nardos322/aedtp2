package aed.comparadores;
import aed.testObjects.*;

import java.util.Comparator;

public class ContadorComparator implements Comparator<Contador> {

    public int compare(Contador c1, Contador c2) {
        return Integer.compare(c1.getValor(), c2.getValor());
    }
}
