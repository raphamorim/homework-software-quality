package trabalhoa2;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControleSaldo {

    public Map<Integer, Integer> listaNotas = new LinkedHashMap();

    public ControleSaldo() {
        inicializarListaNotas();
    }

    public void adicionarNotas(int valor, int quantidade) {
        listaNotas.put(valor, listaNotas.get(valor) + quantidade);
    }

    public void removerNotas(int valor, int quantidade) {
        listaNotas.put(valor, Math.max(0, listaNotas.get(valor) - quantidade));
    }

    public int obterSaldo() {
        int total = 0;

        for (int valor : listaNotas.keySet()) {
            total = total + (valor * listaNotas.get((valor)));
        }

        return total;
    }

    public int obterQuantidadeNotas(int valor) {
        return listaNotas.get(valor);
    }

    private void inicializarListaNotas() {
        listaNotas.put(5, 0);
        listaNotas.put(10, 0);
        listaNotas.put(20, 0);
        listaNotas.put(50, 0);
        listaNotas.put(100, 0);
    }
}
