package aed;

import java.util.Comparator;

public class SuperavitComparador implements Comparator<Ciudad> {
    @Override
    public int compare(Ciudad c1, Ciudad c2) {
        if(c1.getSuperavit() > c2.getSuperavit()){
            return 1;
        }

        if(c1.getSuperavit() < c2.getSuperavit()){
            return -1;
        }

        if(c1.getId() < c2.getId()){
            return 1;
        }

        if(c1.getId() > c2.getId()) {
            return -1;
        }

        return 0;
    }
}
