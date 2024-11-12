package aed;
import java.util.Comparator;
public class MasAntiguosComparador implements Comparator<Traslado> {

    @Override
    public int compare(Traslado t1, Traslado t2) {
        if (t1.getTimestamp() < t2.getTimestamp()){
            return 1;
        }

        if (t1.getTimestamp() > t2.getTimestamp()){
            return -1;
        }
        return 0;
    }
}
