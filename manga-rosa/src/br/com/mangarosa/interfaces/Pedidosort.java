package br.com.mangarosa.sort;

import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoSort implements Sort<Pedido> {
    private int comparisons;

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        comparisons = 0; // Reseta as comparações
        if (dataset == null || dataset.isEmpty()) {
            return dataset;
        }
        return radixSort(dataset, 10, 100, 1000, 10000, 1000000);  // Considera o tamanho das entradas
    }

    @Override
    public int numberComparisons() {
        return comparisons;
    }

    private List<Pedido> radixSort(List<Pedido> pedidos, int maxLength) {
        final int BUCKETS = 256;
        List<Pedido> in = new ArrayList<>(pedidos);
        List<Pedido> out = new ArrayList<>(pedidos.size());
        for (int i = 0; i < pedidos.size(); i++) {
            out.add(null);
        }

        for (int pos = maxLength - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS + 1];

            for (Pedido pedido : in) {
                count[getCharAt(pedido.getId(), pos) + 1]++;
            }

            for (int b = 1; b <= BUCKETS; b++) {
                count[b] += count[b - 1];
            }

            for (Pedido pedido : in) {
                out.set(count[getCharAt(pedido.getId(), pos)]++, pedido);
                comparisons++; // Incrementa o contador
            }

            List<Pedido> temp = in;
            in = out;
            out = temp;
        }

        return in;
    }

    private int getCharAt(char[] id, int pos) {
        return id[pos];
    }
}
