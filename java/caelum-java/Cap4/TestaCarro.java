class TestaCarro {
    public static void main(String[] args) {
        Carro meuCarro;
        meuCarro = new Carro();
        meuCarro.cor = "Verde";
        meuCarro.modelo = "Fusca";
        meuCarro.velocidadeAtual = 0;
        meuCarro.velocidadeMaxima = 80;
        meuCarro.motor.potencia = 50;
        meuCarro.motor.tipo = "1500 Gasolina";
        meuCarro.liga();
        System.out.println(meuCarro.toString());
        meuCarro.acelera(20);
        System.out.println(meuCarro.toString());
        meuCarro.acelera(30);
        System.out.println(meuCarro.toString());
        meuCarro.acelera(100);
        System.out.println(meuCarro.toString());
    }
}