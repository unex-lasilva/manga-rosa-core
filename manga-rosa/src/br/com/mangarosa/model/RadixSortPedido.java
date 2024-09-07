package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class RadixSortPedido implements Sort<Pedido> {

    private int comparisonCount = 0; // Contador de comparações realizadas

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        int n = dataset.size();

        // Se a lista tiver 0 ou 1 elemento, está ordenada
        if (n <= 1) return dataset;

        int numChars = 13; // Número fixo de caracteres no ID do pedido
        return radixSort(dataset, numChars);
    }

    // Método principal que executa o Radix Sort
    private List<Pedido> radixSort(List<Pedido> dataset, int numChars) {
        int n = dataset.size();

        // Ordena os pedidos para cada posição de caractere, da menos significativa para a mais significativa
        for (int charIndex = numChars - 1; charIndex >= 0; charIndex--) {
            dataset = countingSortByChar(dataset, charIndex);
        }

        return dataset;
    }

    // Ordena os pedidos com base no caractere na posição charIndex usando Counting Sort
    private List<Pedido> countingSortByChar(List<Pedido> dataset, int charIndex) {
        int n = dataset.size();
        List<Pedido> output = new ArrayList<>(n);

        // Array para contar a frequência de cada caractere (considerando ASCII)
        int[] count = new int[256]; // Suporta todos os caracteres ASCII

        // Inicializa a lista de saída com null
        for (int i = 0; i < n; i++) {
            output.add(null);
        }

        // Conta a frequência de cada caractere na posição charIndex
        for (Pedido pedido : dataset) {
            char charAtIdx = pedido.getId()[charIndex];
            count[charAtIdx]++;
            comparisonCount++; // Conta a comparação
        }

        // Calcula as posições acumuladas para a ordenação
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Constrói a lista ordenada com base nas posições acumuladas
        for (int i = n - 1; i >= 0; i--) {
            Pedido pedido = dataset.get(i);
            char charAtIdx = pedido.getId()[charIndex];
            output.set(count[charAtIdx] - 1, pedido);
            count[charAtIdx]--;
        }

        return output;
    }

    @Override
    public int numberComparisons() {
        // Retorna o número total de comparações realizadas
        return comparisonCount;
    }
}
