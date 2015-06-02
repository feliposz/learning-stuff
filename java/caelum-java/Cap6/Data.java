class Data {
    int dia;
    int mes;
    int ano;

    public static boolean anoBissexto(int ano) {
        return (ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0);
    }

    Data(int dia, int mes, int ano) throws Exception {

        if (ano == 0 || ano < -5000 || ano > 9999) {
            throw new Exception("Ano inválido");
        }

        if (mes < 1 || mes > 12) {
            throw new Exception("Mês inválido");
        }

        if (dia < 1 || dia > 31) {
            throw new Exception("Dia inválido");
        }

        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                throw new Exception("Dia inválido para este mês (" + mes + ")");
            }
        }

        if (mes == 2) {
            boolean bissexto = Data.anoBissexto(ano);
            if (bissexto && dia > 29) {
                throw new Exception("Dia inválido para este mês (é ano bissexto)");
            } else if (!bissexto && dia > 28) {
                throw new Exception("Dia inválido para este mês (não é ano bissexto)");
            }
        }

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String toString() { // Exercício 9
        return this.dia + "/" + this.mes + "/" + this.ano;
    }

    private static void testaData(String msg, int dia, int mes, int ano) {
        System.out.print(msg+ ": ");
        try {
            Data d = new Data(dia, mes, ano);
            System.out.println(d.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        for (int m = 0; m <= 13; m++) {
            testaData("Testando mês " + m , 1, m, 2000);
        }
        for (int d = 0; d <= 32; d++) {
            testaData("Testando dia " + d, d, 1, 2000);
        }
        for (int a = -6000; a <= 10000; a+=1000) {
            testaData("Testando ano " + a, 1, 1, a);
        }
        testaData("Testando 31/abril", 31, 4, 2000);
        testaData("Testando 31/junho", 31, 6, 2000);
        testaData("Testando 31/setembro", 31, 9, 2000);
        testaData("Testando 31/novembro", 31, 11, 2000);
        testaData("Testando 29/fevereiro/1600", 29, 2, 1600);
        testaData("Testando 29/fevereiro/2000", 29, 2, 2000);
        testaData("Testando 30/fevereiro/2000", 30, 2, 2000);
        testaData("Testando 29/fevereiro/1996", 29, 2, 1996);
        testaData("Testando 29/fevereiro/2004", 29, 2, 2004);
        testaData("Testando 29/fevereiro/2008", 29, 2, 2008);
        testaData("Testando 29/fevereiro/2009", 29, 2, 2009);
        testaData("Testando 29/fevereiro/1700", 29, 2, 1700);
        testaData("Testando 29/fevereiro/1900", 29, 2, 1900);
    }
}

