import java.util.Comparator;
import java.util.TreeSet;

class Cap16Desafio1b {
    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a.compareTo(b) * -1;
            }
        });

        for (int i = 1; i <= 1000; i++ ) {
            ts.add(i);
        }

        for (int item : ts) {
            System.out.println(item);
        }
    }
}