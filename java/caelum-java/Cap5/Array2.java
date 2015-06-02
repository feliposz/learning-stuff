class Array2 {
    public static void main(String[] args) {
        //int[] a1 = int[0];
        //int[] a2 = int[-1];
        //int[][] aa1 = int[][5]; //???
        int[][] aa1 = { {1, 2, 3}, {4, 5}, {6, 7, 8, 9}, {0} };
        
        for (int i = 0; i < aa1.length; i++) {
            System.out.print("[");
            for (int j = 0; j < aa1[i].length; j++) {
                System.out.print(" " + aa1[i][j]);
            }
            System.out.println(" ]");
        }
        
    }
}
