package br.com.mangarosa.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.mangarosa.interfaces.Sort;
import br.com.mangarosa.model.Pedido;

public class Ordenation implements Sort<Pedido> {

    // Variável para contagem da quantidade de comparações
    private int comparacoes = 0;

    // Radix Sort
    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        
        // Verifica se a entrada é nula ou vazia
        if (dataset == null || dataset.isEmpty()) {
            return dataset;
        }

        int maxLength = 13; // Os IDs devem ter no máximo 13 caracteres
        for (int i = maxLength - 1; i >= 0; i--) {
            dataset = countingSort(dataset, i);
            // Lê o ID da direita para a esquerda e aplica o método countingSort
        }

        return dataset;
    }

    // Método que aplica o CountingSort em cada posição do ID
    private List<Pedido> countingSort(List<Pedido> dataset, int i) {
        // Array que recebe os caracteres da tabela ASCII
        int[] count = new int[256];

        // Array para armazenamento dos pedidos ordenados
        Pedido[] output = new Pedido[dataset.size()];

        // Conta as ocorrências de cada caractere por posição
        for (Pedido pedido : dataset) {
            // Obtém o caractere na posição atual do ID do pedido
            char character = pedido.getId()[i];
            count[character]++;
            comparacoes++;
        }

        // Calcula a posição acumulada de cada caractere na saída
        for (int j = 1; j < 256; j++) {
            count[j] = count[j] + count[j - 1];
        }

        // Preenche o array de saída com os pedidos
        for (int j = dataset.size() - 1; j >= 0; j--) {
            char character = dataset.get(j).getId()[i];

            // Aloca o pedido na posição correta no array de saída
            output[count[character] - 1] = dataset.get(j);
            count[character]--;
        }

        // Retorna a lista ordenada
        return new ArrayList<>(Arrays.asList(output));
    }

    // Retorna o número de comparações
    @Override
    public int numberComparisons() {
       return comparacoes;
    }
    
}
