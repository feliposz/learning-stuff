class TesteThreads {

    

    public static void main(String[] args) {
        
        Thread t1 = new Thread(new Programa(1));
        Thread t2 = new Thread(new Programa(2));
        
        t1.start();
        t2.start();
    }
}

class Programa implements Runnable {
    
    private int id;
    
    Programa(int id) {
        this.id = id;
    }
    
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("Programa " + this.id + ": " + i);
        }
    }
    
}
