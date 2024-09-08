package br.com.mangarosa;

import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do algoritmo de ordenação Radix Sort para a classe Pedido.
 *
 * O Radix Sort é um algoritmo de ordenação não comparativo que usa a contagem de dígitos
 * para ordenar números. Esta implementação assume que o identificador de Pedido é uma string
 * com comprimento fixo.
 */
public class RadixSort implements Sort<Pedido> {

    private int comparisons = 0; // Contador de comparações realizadas durante a ordenação

    private static final int MAX_LENGTH = 13; // Comprimento máximo do identificador do Pedido

    /**
     * Ordena uma lista de objetos Pedido usando o algoritmo Radix Sort.
     *
     * @param dataset Lista de objetos Pedido a ser ordenada.
     * @return A lista ordenada de objetos Pedido.
     */
    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        for (int pos = MAX_LENGTH - 1; pos >= 0; pos--) {
            countingSort(dataset, pos);
        }
        return dataset;
    }

    /**
     * Realiza a ordenação dos pedidos com base em um caractere específico da posição
     * charPos usando o algoritmo Counting Sort.
     *
     * @param dataset Lista de objetos Pedido a ser ordenada.
     * @param charPos Posição do caractere no identificador para ordenar.
     */
    private void countingSort(List<Pedido> dataset, int charPos) {
        int n = dataset.size();
        List<Pedido> output = new ArrayList<>(n); // Lista de saída para armazenar pedidos ordenados
        int[] count = new int[256]; // Tabela de contagem para caracteres ASCII

        // Inicializa o array de contagem
        for (int i = 0; i < 256; i++) {
            count[i] = 0;
        }

        // Conta a frequência de cada caractere na posição charPos
        for (Pedido pedido : dataset) {
            char ch = pedido.getId()[charPos];
            count[ch]++;
        }

        // Ajusta as contagens para encontrar as posições finais
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Inicializa a lista output com nulls
        for (int i = 0; i < n; i++) {
            output.add(null);
        }

        // Preenche a lista output com base no array de contagem
        for (int i = n - 1; i >= 0; i--) {
            Pedido pedido = dataset.get(i);
            char ch = pedido.getId()[charPos];
            int index = count[ch] - 1;
            output.set(index, pedido);
            count[ch]--;
            comparisons++; // Incrementa o contador de comparações
        }

        // Copia os elementos ordenados de volta para a lista original
        for (int i = 0; i < n; i++) {
            dataset.set(i, output.get(i));
        }
    }

    /**
     * Retorna o número total de comparações realizadas durante a ordenação.
     *
     * @return O número total de comparações.
     */
    @Override
    public int numberComparisons() {
        return comparisons;
    }
}
