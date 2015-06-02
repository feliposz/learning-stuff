class Foo {

    private int i;
    private double d;

    Foo(int i, double d) {
        this.i = i;
        this.d = d;
    }

    public String toString() {
        return String.format("(i = %d, d = %f)", i, d);
    }

    public boolean equals(Object bar) {
        if (!(bar instanceof Foo)) {
            return false;
        } else {
            Foo fooBar = (Foo)bar;
            return this.i == fooBar.i && this.d == fooBar.d;
        }
    }

    public static void main(String[] args) {
        Foo x = new Foo(1, 2.0);
        Foo y = new Foo(1, 2.0);
        Foo z = new Foo(2, 2.12345);

        Object o = new Object();

        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);

        System.out.println("x == y = " + x.equals(y));
        System.out.println("x == z = " + x.equals(z));
        System.out.println("y == z = " + y.equals(z));
        System.out.println("x == o = " + y.equals(o));
        System.out.println("o == o = " + o.equals(o));
    }

}
