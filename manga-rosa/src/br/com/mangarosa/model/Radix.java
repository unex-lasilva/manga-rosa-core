package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;

import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o algoritmo Radix Sort para ordenar objetos do tipo Pedido,
 * com base em seus identificadores alfanuméricos.
 * Esta classe implementa a interface Sort<Pedido>, o que implica a necessidade de implementar
 * um método de ordenação e também o rastreamento do número de comparações realizadas durante o processo.
 */
public class Radix implements Sort<Pedido> {

    // Variável responsável por registrar o número de comparações realizadas durante o processo de ordenação.
    private int comparacoes;

    /**
     * Método que ordena uma lista de pedidos utilizando o algoritmo Radix Sort.
     * Converte a lista para um array, zera o contador de comparações e invoca o radixSort.
     *
     * @param dados A lista de pedidos a ser ordenada.
     * @return A lista de pedidos ordenada.
     */
    @Override
    public List<Pedido> sort(List<Pedido> dados) {
        comparacoes = 0; // Reinicializa o número de comparações
        Pedido[] arrayPedidos = dados.toArray(new Pedido[0]); // Converte a lista para array
        radixSort(arrayPedidos); // Chama o método de ordenação radixSort
        return Arrays.asList(arrayPedidos); // Retorna a lista ordenada
    }

    /**
     * Aplica o algoritmo Radix Sort iterando sobre cada posição dos identificadores dos pedidos,
     * da última posição até a primeira.
     *
     * @param pedidos O array de pedidos a ser ordenado.
     */
    private void radixSort(Pedido[] pedidos) {
        for (int posicao = 12; posicao >= 0; posicao--) { // Assume-se que o id tem 13 caracteres (índices de 0 a 12)
            countingSort(pedidos, posicao); // Ordena de acordo com o caractere na posição atual
        }
    }

    /**
     * Método auxiliar que implementa o Counting Sort para uma posição específica do identificador do pedido.
     * Utiliza um array de contagem para ordenar com base no caractere correspondente.
     *
     * @param pedidos O array de pedidos a ser ordenado.
     * @param posicao A posição do identificador utilizada para a ordenação.
     */
    private void countingSort(Pedido[] pedidos, int posicao) {
        int n = pedidos.length; // Número de pedidos
        Pedido[] resultado = new Pedido[n]; // Array de saída ordenado
        int[] contador = new int[256]; // Array de contagem (ASCII)

        // Inicializa o array de contagem
        Arrays.fill(contador, 0);

        // Contabiliza a ocorrência de cada caractere na posição indicada do id
        for (Pedido pedido : pedidos) {
            comparacoes++; // Incrementa o número de comparações
            char chave = pedido.getId()[posicao]; // Obtém o caractere na posição especificada
            contador[chave]++; // Incrementa a contagem para a chave (caractere)
        }

        // Ajusta o array de contagem para refletir a posição final de cada caractere
        for (int i = 1; i < 256; i++) {
            contador[i] += contador[i - 1];
        }

        // Constrói o array ordenado com base nos valores de contagem
        for (int i = n - 1; i >= 0; i--) {
            char chave = pedidos[i].getId()[posicao]; // Obtém o caractere na posição especificada
            resultado[contador[chave] - 1] = pedidos[i]; // Coloca o pedido na posição correta
            contador[chave]--; // Reduz a contagem da chave
        }

        // Copia o array de saída de volta para o array original
        System.arraycopy(resultado, 0, pedidos, 0, n);
    }

    /**
     * Retorna o número total de comparações realizadas durante o processo de ordenação.
     *
     * @return O número de comparações.
     */
    @Override
    public int numberComparisons() {
        return comparacoes;
    }
}
