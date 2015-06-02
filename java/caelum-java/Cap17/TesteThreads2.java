class TesteThreads2 {

    public static void main(String[] args) throws InterruptedException  {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Programa 1: " + i);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Programa 2: " + i);
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Programa 3: " + i);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
