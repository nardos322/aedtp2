package aed;
import java.util.Comparator;
public class MasRedituablesComparador implements Comparator<Traslado> {
    @Override
    public int compare(Traslado t1, Traslado t2) {

        if (t1.getGanancia() > t2.getGanancia()) {
            return 1;
        }
        if (t1.getGanancia() < t2.getGanancia()) {
            return -1;
        }

        if (t1.getId() < t2.getId()) {
            return 1;
        }
        if (t1.getId() > t2.getId()) {
            return -1;
        }

        return 0;
    }
}
