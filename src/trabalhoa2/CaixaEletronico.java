package trabalhoa2;

public class CaixaEletronico {

    private ControleSaldo controleSaldo;
    private int quantidadeSaques;
    private float valorTotalSaques;

    public CaixaEletronico() {
        this.controleSaldo = new ControleSaldo();
        this.quantidadeSaques = 0;
        this.valorTotalSaques = 0;
    }

    public String getSaldo() {
        String retorno = "";
        retorno = "\n---------------------------------------";
        retorno = retorno + "\nCaixa Eletrônico - Consulta de Saldo";
        retorno = retorno + "\n---------------------------------------";
        retorno = retorno + "\nO Saldo é: " + String.valueOf(controleSaldo.obterSaldo()) + "\nQuantidade de saques: ";
        retorno = retorno + String.valueOf(this.quantidadeSaques) + "\nValor dos saques: ";
        retorno = retorno + String.valueOf(this.valorTotalSaques) + "\nTotal notas de 5: ";
        retorno = retorno + String.valueOf(controleSaldo.obterQuantidadeNotas(5)) + "\nTotal notas de 10: ";
        retorno = retorno + String.valueOf(controleSaldo.obterQuantidadeNotas(10)) + "\nTotal notas de 20: ";
        retorno = retorno + String.valueOf(controleSaldo.obterQuantidadeNotas(20)) + "\nTotal notas de 50: ";
        retorno = retorno + String.valueOf(controleSaldo.obterQuantidadeNotas(50)) + "\nTotal notas de 100: ";
        retorno = retorno + String.valueOf(controleSaldo.obterQuantidadeNotas(100)) + ".";

        return retorno;
    }

    public void depositar(int valor, int quantidade) {
        controleSaldo.adicionarNotas(valor, quantidade);
    }

    public String sacar(int quantiaOriginal) {

        String retorno = "";
        int quantia = quantiaOriginal;
        if (quantia > 0 && controleSaldo.obterSaldo() >= quantia) {
            int qtdNota5 = 0;
            int qtdNota10 = 0;
            int qtdNota20 = 0;
            int qtdNota50 = 0;
            int qtdNota100 = 0;

            if (quantia >= 100) {
                qtdNota100 = (int) Math.floor(quantia / 100);

                if (controleSaldo.obterQuantidadeNotas(100) < qtdNota100) {
                    qtdNota100 = controleSaldo.obterQuantidadeNotas(100);
                }

                quantia = quantia - qtdNota100 * 100;
            }

            if (quantia >= 50) {
                qtdNota50 = (int) Math.floor(quantia / 50);

                if (controleSaldo.obterQuantidadeNotas(50) < qtdNota50) {
                    qtdNota50 = controleSaldo.obterQuantidadeNotas(50);
                }

                quantia = quantia - qtdNota50 * 50;
            }

            if (quantia >= 20) {
                qtdNota20 = (int) Math.floor(quantia / 20);

                if (controleSaldo.obterQuantidadeNotas(20) < qtdNota20) {
                    qtdNota20 = controleSaldo.obterQuantidadeNotas(20);
                }

                quantia = quantia - qtdNota20 * 20;
            }
            if (quantia >= 10) {
                qtdNota10 = (int) Math.floor(quantia / 10);

                if (controleSaldo.obterQuantidadeNotas(10) < qtdNota10) {
                    qtdNota10 = controleSaldo.obterQuantidadeNotas(10);
                }

                quantia = quantia - qtdNota10 * 10;
            }
            if (quantia >= 5) {
                qtdNota5 = (int) Math.floor(quantia / 5);

                if (controleSaldo.obterQuantidadeNotas(5) < qtdNota5) {
                    qtdNota5 = controleSaldo.obterQuantidadeNotas(5);
                }

                quantia = quantia - qtdNota5 * 5;
            }

            if (quantia == 0) {
                retorno = "\n---------------------------------------";
                retorno = retorno + "\nCaixa Eletrônico - Saque";
                retorno = retorno + "\n---------------------------------------";
                retorno = retorno + "\nSaque realiazado com sucesso! ";
                retorno = retorno + "\nTotal notas de 5: ";
                retorno = retorno + String.valueOf(qtdNota5) + "\nTotal notas de 10: ";
                retorno = retorno + String.valueOf(qtdNota10) + "\nTotal notas de 20: ";
                retorno = retorno + String.valueOf(qtdNota20) + "\nTotal notas de 50: ";
                retorno = retorno + String.valueOf(qtdNota50) + "\nTotal notas de 100: ";
                retorno = retorno + String.valueOf(qtdNota100) + ".";

                this.quantidadeSaques += 1;
                this.valorTotalSaques += quantiaOriginal;
                controleSaldo.removerNotas(5, qtdNota5);
                controleSaldo.removerNotas(10, qtdNota10);
                controleSaldo.removerNotas(20, qtdNota20);
                controleSaldo.removerNotas(50, qtdNota50);
                controleSaldo.removerNotas(100, qtdNota100);

            } else {
                retorno = "\nQuantidade de células não é múltiplo do valor solicitado";
            }

        } else {
            retorno = "\nQuantia inválida ou saldo insuficiente";
        }

        return retorno;
    }
}
