package aed.comparadores;
import java.util.Comparator;

public class PrimoComparatorMax implements Comparator<Integer> {


    private boolean isPrime(Integer number) {
        if (number <= 1) return false;
        int i = 2;
        while(i < number) {
            if(number % i == 0){
                return false;
            }
            i++;
        }
        return true;
    }

    @Override
    public int compare(Integer a, Integer b) {
        boolean isAPrime = isPrime(a);
        boolean isBPrime = isPrime(b);


        if (isAPrime && isBPrime) {
            return Integer.compare(a,b);
        }


        if (isAPrime && !isBPrime) {
            return 1;
        }

        if(!isAPrime && isBPrime) {
            return -1;
        }


        return Integer.compare(a, b);
    }

}
