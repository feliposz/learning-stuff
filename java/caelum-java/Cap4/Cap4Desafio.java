class Fibonacci {

    int calculaFibonacci1(int i) { // Ex 1
        if (i < 2) {
            return i;
        } else {
            return calculaFibonacci1(i-2) + calculaFibonacci1(i-1);
        }
    }
    
    // Ex 3: A recursão é mais lenta neste caso pois para valores grandes são necessários muitas chamadas recursivas repetitivas desnecessárias.
    
    int calculaFibonacci2(int i) { // Ex 2
        return i < 2 ? i : calculaFibonacci2(i-2) + calculaFibonacci2(i-1);
    }
    
}
    
class Cap4Desafio {
    public static void main(String[] args) {
        Fibonacci fibo = new Fibonacci();
        
        System.out.println("fibo.calculaFibonacci1:");
        for (int i = 1; i <= 40; i++) {
            System.out.print(" " + fibo.calculaFibonacci1(i));
        }
        System.out.println();
        
        System.out.println("fibo.calculaFibonacci2:");
        for (int i = 1; i <= 40; i++) {
            System.out.print(" " + fibo.calculaFibonacci2(i));
        }
        System.out.println();
        
    }
}
