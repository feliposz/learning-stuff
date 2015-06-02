import java.util.ArrayList;

class Cap16Desafio2 {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();

        for (int i = 1; i <= 1000; i++ ) {
            al.add(0, i);
        }

        for (int item : al) {
            System.out.println(item);
        }
    }
}