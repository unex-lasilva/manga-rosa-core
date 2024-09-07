package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;
import java.util.Arrays;
import java.util.List;

// Classe RadixSort que implementa o algoritmo Radix Sort para o tipo Pedido.
// Implementa a interface Sort<Pedido>, que define a necessidade de implementar o método de ordenação e a contagem de comparações.
public class RadixSort implements Sort<Pedido> {

    // Atributo para armazenar o número de comparações feitas durante a ordenação.
    private int comparisons;

    // Método que realiza a ordenação da lista de pedidos usando radix sort.
    // Converte a lista em um array para facilitar a manipulação, reseta o contador de comparações
    // e chama o método radixSort para ordenar o array.
    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        comparisons = 0; // Reseta o número de comparações
        Pedido[] pedidosArray = dataset.toArray(new Pedido[0]); // Converte a lista para array
        radixSort(pedidosArray); // Chama o método radixSort para ordenar o array
        return Arrays.asList(pedidosArray); // Retorna a lista ordenada
    }

    // Itera sobre cada posição do identificador (id) dos pedidos, da última para a primeira posição,
    // chamando o método countingSort para cada posição.
    private void radixSort(Pedido[] pedidos) {
        for (int pos = 12; pos >= 0; pos--) { // Assume que o id tem 13 caracteres (posições de 0 a 12)
            countingSort(pedidos, pos); // Ordena os pedidos com base no caractere na posição 'pos'
        }
    }

    // Método privado que implementa o Counting Sort para uma determinada posição do id dos pedidos.
    // Ordena os pedidos com base no caractere em uma posição específica do id, usando um array de contagem.
    private void countingSort(Pedido[] pedidos, int pos) {
        int n = pedidos.length; // Número de pedidos
        Pedido[] output = new Pedido[n]; // Array para armazenar o resultado da ordenação
        int[] count = new int[256]; // Array de contagem, com 256 posições (para caracteres ASCII)

        // Inicializa o array de contagem com zeros
        Arrays.fill(count, 0);

        // Conta a ocorrência de cada caractere na posição 'pos' do id dos pedidos
        for (Pedido pedido : pedidos) {
            comparisons++; // Incrementa o número de comparações
            char key = pedido.getId()[pos]; // Obtém o caractere na posição 'pos'
            count[key]++; // Incrementa a contagem para o caractere 'key'
        }

        // Atualiza o array de contagem para que cada posição contenha o índice final de cada caractere
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída, ordenando os pedidos com base no caractere da posição 'pos'
        for (int i = n - 1; i >= 0; i--) {
            char key = pedidos[i].getId()[pos]; // Obtém o caractere na posição 'pos'
            output[count[key] - 1] = pedidos[i]; // Coloca o pedido na posição correta do array de saída
            count[key]--; // Decrementa a contagem para o caractere 'key'
        }

        // Copia o array de saída de volta para o array original de pedidos
        System.arraycopy(output, 0, pedidos, 0, n);
    }

    // Método que retorna o número de comparações feitas durante a ordenação.
    @Override
    public int numberComparisons() {
        return comparisons;
    }
}
