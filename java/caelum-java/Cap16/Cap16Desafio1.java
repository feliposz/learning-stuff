import java.util.Comparator;
import java.util.TreeSet;

class CompReverso<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T a, T b) {
        return a.compareTo(b) * -1;
    }
}

class Cap16Desafio1 {
    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>(new CompReverso<Integer>());

        for (int i = 1; i <= 1000; i++ ) {
            ts.add(i);
        }

        for (int item : ts) {
            System.out.println(item);
        }
    }
}