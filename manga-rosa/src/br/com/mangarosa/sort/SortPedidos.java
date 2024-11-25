package br.com.mangarosa.sort;

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.interfaces.Sort;

import java.util.Arrays;
import java.util.List;

public class SortPedidos implements Sort<Pedido> {

    private int comparisons; // Armazena o número de comparações feitas durante a ordenação

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        comparisons = 0; // Zera o contador de comparações

        // Converte a lista de pedidos para um array
        Pedido[] pedidosArray = dataset.toArray(new Pedido[0]);

        // Aplica o algoritmo Radix Sort
        radixSort(pedidosArray);

        // Retorna a lista ordenada
        return Arrays.asList(pedidosArray);
    }

    // Implementa o Radix Sort que ordena os pedidos caractere por caractere
    private void radixSort(Pedido[] pedidos) {
        int n = pedidos.length;

        // Ordena da posição mais à direita (12) até a mais à esquerda (0) do código
        for (int pos = 12; pos >= 0; pos--) {
            countingSort(pedidos, pos); // Aplica Counting Sort em cada posição do código
        }
    }

    // Implementa o Counting Sort para uma posição específica dos códigos dos pedidos
    private void countingSort(Pedido[] pedidos, int pos) {
        int n = pedidos.length;
        Pedido[] output = new Pedido[n]; // Array de saída para os pedidos ordenados

        int[] count = new int[256]; // Array de contagem para os caracteres ASCII
        Arrays.fill(count, 0); // Inicializa o array de contagem com 0

        // Conta a ocorrência de cada caractere na posição 'pos'
        for (Pedido pedido : pedidos) {
            comparisons++; // Incrementa o contador de comparações
            char key = pedido.getId()[pos]; // Obtém o caractere na posição 'pos'
            count[key]++;
        }

        // Ajusta o array de contagem para indicar as posições finais no array de saída
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída ordenado
        for (int i = n - 1; i >= 0; i--) {
            char key = pedidos[i].getId()[pos]; // Obtém o caractere na posição 'pos'
            output[count[key] - 1] = pedidos[i]; // Coloca o pedido na posição correta
            count[key]--;
        }

        // Copia o array de saída de volta para o array original
        System.arraycopy(output, 0, pedidos, 0, n);
    }

    // Retorna o número de comparações feitas pelo algoritmo
    @Override
    public int numberComparisons() {
        return comparisons;
    }
}
