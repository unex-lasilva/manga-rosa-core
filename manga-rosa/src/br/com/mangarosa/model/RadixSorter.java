package br.com.mangarosa.model;

import br.com.mangarosa.interfaces.Sort;
import java.util.ArrayList;
import java.util.List;

public class RadixSorter implements Sort<Pedido> {

    private int totalComparisons = 0; // Armazena o número total de comparações realizadas

    // Retorna a lista ordenada
    @Override
    public List<Pedido> sort(List<Pedido> pedidoList) {
        if (pedidoList.isEmpty()) return pedidoList;

        List<Pedido> orderedList = new ArrayList<>(pedidoList); // Cria uma cópia da lista original para ordenar

        // Itera sobre cada posição do caractere do ID (da posição mais significativa para a menos significativa).
        for (int pos = 12; pos >= 0; pos--) {
            // Ordena os pedidos com base na posição do caractere ou dígito atual e acumula o total de comparações.
            orderedList = countingSortByCharacterPosition(orderedList, pos);
        }

        return orderedList;
    }

    // Retorna o total de comparações realizadas
    @Override
    public int numberComparisons() {
        return this.totalComparisons;
    }

    private List<Pedido> countingSortByCharacterPosition(List<Pedido> pedidoList, int pos) {
        final int CHAR_RANGE = 62; // Total de caracteres possíveis (A-Z, a-z, 0-9).
        int[] count = new int[CHAR_RANGE + 1]; // Array para contar as ocorrências de cada caractere.
        List<Pedido> orderedList = new ArrayList<>(pedidoList.size()); // Lista para armazenar o resultado ordenado.

        // Inicializa a lista de saída com elementos nulos.
        for (int i = 0; i < pedidoList.size(); i++) {
            orderedList.add(null);
        }

        // Conta as ocorrências de cada caractere na posição 'pos' para todos os pedidos.
        for (Pedido pedido : pedidoList) {
            int index = getCharIndex(pedido.getId(), pos); // Obtém o índice do caractere.
            count[index]++; // Incrementa a contagem para o caractere.
        }

        // Atualiza o array de contagem para refletir as posições acumulativas.
        for (int i = 1; i <= CHAR_RANGE; i++) {
            count[i] += count[i - 1];
        }

        // Constrói a lista de saída com base no array de contagem.
        for (int i = pedidoList.size() - 1; i >= 0; i--) {
            Pedido pedido = pedidoList.get(i); // Obtém o pedido atual.
            int index = getCharIndex(pedido.getId(), pos); // Obtém o índice do caractere.
            orderedList.set(count[index] - 1, pedido); // Coloca o pedido na posição correta.
            count[index]--; // Decrementa o contador para o caractere.
            this.totalComparisons++; // Incrementa o contador de comparações.
        }

        return orderedList; // Retorna a nova lista ordenada
    }

    private int getCharIndex(char[] id, int pos) {
        char c = id[pos]; // Obtém o caractere na posição `pos`.
        if (c >= '0' && c <= '9') return c - '0'; // Para caracteres numéricos.
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10; // Para caracteres maiúsculos.
        return c - 'a' + 36; // Para caracteres minúsculos.
    }
}