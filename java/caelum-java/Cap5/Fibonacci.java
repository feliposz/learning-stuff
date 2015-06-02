class Fibonacci { // Desafio Cap 5
    
    int[] memoFibo = new int[100];
    
    Fibonacci() {
        memoFibo[0] = 0;
        memoFibo[1] = 1;
        for (int i = 2; i < memoFibo.length; i++) {
            memoFibo[i] = -1;
        }        
    }
    
    int fibonacci(int n) {
        if (memoFibo[n] == -1) {
            memoFibo[n] = fibonacci(n-2) + fibonacci(n-1);
        } 
        return memoFibo[n];
    }
    
    public static void main(String[] args) {
        Fibonacci fibo = new Fibonacci();
        
        for (int i = 0; i < 40; i++) {
            System.out.print(" " + fibo.fibonacci(i));
        }
    }
    
}