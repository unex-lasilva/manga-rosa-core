package Projeto1;

import java.util.ArrayList;
import java.util.List;

public class RadixSort implements Sort<Pedido> {
    private int comparisonCount = 0;

    @Override
    public List<Pedido> sort(List<Pedido> dataset) {
        if (dataset == null || dataset.size() == 0) {
            return dataset;
        }

        // Define a quantidade de dígitos dos IDs dos pedidos
        final int DIGIT_COUNT = 13;

        // Usa Radix Sort para ordenar por cada dígito do ID de trás para frente
        for (int digitIndex = DIGIT_COUNT - 1; digitIndex >= 0; digitIndex--) {
            countingSortByDigit(dataset, digitIndex);
        }

        return dataset;
    }

    private void countingSortByDigit(List<Pedido> dataset, int digitIndex) {
        int[] count = new int[128];  // Mantendo o tamanho da tabela ASCII para contar os caracteres
        List<Pedido> sorted = new ArrayList<>(dataset.size());

        // Inicializa lista temporária com o mesmo tamanho do dataset
        for (int i = 0; i < dataset.size(); i++) {
            sorted.add(null);
        }

        // Conta a frequência de cada caractere no dígito atual
        for (Pedido pedido : dataset) {
            char c = pedido.getId()[digitIndex];

            // Verifica se o valor de 'c' está dentro do intervalo válido
            if (c < 0 || c >= count.length) {
                throw new RuntimeException("Caractere fora do intervalo esperado: " + c);
            }
            count[c]++;
        }

        // Modifica count para que cada posição contenha o índice correto no array de saída
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Constrói a lista ordenada de trás para frente para garantir estabilidade
        for (int i = dataset.size() - 1; i >= 0; i--) {
            Pedido pedido = dataset.get(i);
            char c = pedido.getId()[digitIndex];

            // Verifica se o valor de 'c' está dentro do intervalo válido
            if (c < 0 || c >= count.length) {
                throw new RuntimeException("Caractere fora do intervalo esperado: " + c);
            }

            // Movimenta o pedido para a posição correta em 'sorted'
            sorted.set(count[c] - 1, pedido);
            count[c]--;

            // Incrementa o contador de movimentações
            comparisonCount++;  // Contando cada movimentação para a lista sorted
        }

        // Copia os elementos ordenados de volta para o dataset original
        for (int i = 0; i < dataset.size(); i++) {
            dataset.set(i, sorted.get(i));
        }
    }

    @Override
    public int numberComparisons() {
        return comparisonCount;
    }
}