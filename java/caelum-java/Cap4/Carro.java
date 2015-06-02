class Carro {
    String cor;
    String modelo;
    double velocidadeAtual;
    double velocidadeMaxima;
    Motor motor = new Motor();

    void liga() {
        System.out.println("O carro está ligado");
    }

    void acelera(double quantidade) {
        double novaVelocidade = this.velocidadeAtual + quantidade;
        if (novaVelocidade > this.velocidadeMaxima) {
            novaVelocidade = this.velocidadeMaxima;
        }
        this.velocidadeAtual = novaVelocidade;
    }

    public String toString() {
        return "Cor: " + cor
            + " Modelo: " + modelo
            + " Vel: " + velocidadeAtual
            + " Max: " + velocidadeMaxima
            + " Marcha: " + pegaMarcha()
            + " Motor: " + motor.toString();
    }

    int pegaMarcha() {
        if (this.velocidadeAtual < 0) {
            return -1;
        } else if (this.velocidadeAtual < 40) {
            return 1;
        } else if (this.velocidadeAtual < 80) {
            return 2;
        } else {
            return 3;
        }
    }
}
