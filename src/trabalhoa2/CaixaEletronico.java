package trabalhoa2;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaixaEletronico {

    private final ControleSaldo controleSaldo;
    private int quantidadeSaques;
    private float valorTotalSaques;

    public CaixaEletronico() {
        this.controleSaldo = new ControleSaldo();
        this.quantidadeSaques = 0;
        this.valorTotalSaques = 0;
    }

    public String exibirSaldo() {
        String mensagem = "\nCaixa Eletrônico - Consulta de Saldo";
        mensagem += "\nO Saldo é: " + String.valueOf(controleSaldo.obterSaldo());
        mensagem += "\nQuantidade de saques: " + String.valueOf(this.quantidadeSaques);
        mensagem += "\nValor dos saques: " + String.valueOf(this.valorTotalSaques);
        mensagem = controleSaldo.listaNotas.entrySet().stream().map((entry)
                -> "\nNotas de " + entry.getKey() + ": " + entry.getValue()).reduce(mensagem, String::concat);
        return mensagem;
    }

    public void depositar(int valor, int quantidade) {
        controleSaldo.adicionarNotas(valor, quantidade);
    }

    public String sacar(int quantiaOriginal) {
        int quantia = quantiaOriginal;
        Map<Integer, Integer> quantidadeNotas = new LinkedHashMap();
        quantidadeNotas.put(100, 0);
        quantidadeNotas.put(50, 0);
        quantidadeNotas.put(20, 0);
        quantidadeNotas.put(10, 0);
        quantidadeNotas.put(5, 0);

        if (quantia <= 0 || controleSaldo.obterSaldo() < quantia) {
            return "\nQuantia inválida ou saldo insuficiente";
        }

        for (Map.Entry<Integer, Integer> entry : quantidadeNotas.entrySet()) {
            if (quantia < entry.getValue()) {
                break;
            }
            int calculo = (int) Math.floor(quantia / entry.getKey());
            quantidadeNotas.put(entry.getKey(), calculo);
            quantia = quantia - (calculo * entry.getKey());
        }

        if (quantia > 0) {
            return "\nQuantidade de células não é múltiplo do valor solicitado";
        }

        String mensagem = "\nCaixa Eletrônico - Saque";
        for (Map.Entry<Integer, Integer> entry : quantidadeNotas.entrySet()) {
            mensagem += "\nNotas de " + entry.getKey() + ": " + entry.getValue();
            controleSaldo.removerNotas(entry.getKey(), entry.getValue());
        }

        this.quantidadeSaques += 1;
        this.valorTotalSaques += quantiaOriginal;
        return mensagem;
    }
}
