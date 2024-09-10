package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;

import java.util.ArrayList;
import java.util.List;

public class CountingSort implements Sort<Pedido> {

    private int comparisons;

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        if (dataset == null || dataset.isEmpty()) {
            return dataset;
        }

        comparisons = 0; // Reseta contagem de comparações
        int maxLen = 13;// Encontra o comprimento máximo dos IDs (sempre será 13)

        for (int pos = maxLen - 1; pos >= 0; pos--) {// Ordena pelos IDs dos pedidos
            dataset = countingSortByCharacter(dataset, pos);
        }

        return dataset;
    }

    private List<Pedido> countingSortByCharacter(List<Pedido> dataset, int pos) {
        final int CHAR_RANGE = 256; // Total de caracteres possíveis

        int[] count = new int[CHAR_RANGE];
        List<Pedido> output = new ArrayList<>(dataset.size());// Cria o array de contagem

        for (int i = 0; i < dataset.size(); i++) {// Inicializa a lista de saída
            output.add(null);
        }

        for (Pedido pedido : dataset) {// Preenche o array de contagem
            char ch = getCharAt(pedido, pos);
            count[ch]++;
            comparisons++;
        }

        for (int i = 1; i < CHAR_RANGE; i++) {// Altera o array de contagem para representar a posição dos elementos
            count[i] += count[i - 1];
        }

        for (int i = dataset.size() - 1; i >= 0; i--) {// Constrói o array de saída
            Pedido pedido = dataset.get(i);
            char ch = getCharAt(pedido, pos);
            output.set(count[ch] - 1, pedido);
            count[ch]--;
            comparisons++;
        }

        return output;
    }

    private char getCharAt(Pedido pedido, int pos) {
        return pedido.getId()[pos];
    }

    @Override
    public int numberComparisons() {
        return comparisons;
    }
}
