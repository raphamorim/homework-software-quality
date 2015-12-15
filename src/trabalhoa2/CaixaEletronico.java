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

        String mensagem = "";
        mensagem = "\nCaixa Eletrônico - Saque";
        mensagem = mensagem + "\n---------------------------------------";
        mensagem = mensagem + "\nSaque realiazado com sucesso! ";
        mensagem = mensagem + "\nNotas de 5: ";
        mensagem = mensagem + String.valueOf(quantidadeNotas.get(5)) + "\nNotas de 10: ";
        mensagem = mensagem + String.valueOf(quantidadeNotas.get(10)) + "\nNotas de 20: ";
        mensagem = mensagem + String.valueOf(quantidadeNotas.get(20)) + "\nNotas de 50: ";
        mensagem = mensagem + String.valueOf(quantidadeNotas.get(50)) + "\nNotas de 100: ";
        mensagem = mensagem + String.valueOf(quantidadeNotas.get(100));

        quantidadeNotas.entrySet().stream().forEach((entry) -> {
            controleSaldo.removerNotas(entry.getKey(), entry.getValue());
        });

        this.quantidadeSaques += 1;
        this.valorTotalSaques += quantiaOriginal;
        return mensagem;
    }
}
