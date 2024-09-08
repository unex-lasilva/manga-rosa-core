package com.example;

import java.util.Arrays;

public class RadixSort implements Sort {

    // Função para pegar o maior comprimento de string (código alfanumérico)
    private int getMax(Pedido[] pedidos) {
        int maxLength = 0;
        for (Pedido pedido : pedidos) {
            maxLength = Math.max(maxLength, pedido.getCodigo().length());
        }
        return maxLength;
    }

    // Função para realizar a ordenação com base em um dígito específico
    private void countingSort(Pedido[] pedidos, int pos) {
        int n = pedidos.length;
        Pedido[] output = new Pedido[n];
        int[] count = new int[256];  // Alfanuméricos podem ter até 256 caracteres

        // Inicializa a contagem com 0
        Arrays.fill(count, 0);

        // Armazena as contagens de cada caractere
        for (Pedido pedido : pedidos) {
            int index = pos < pedido.getCodigo().length() ? pedido.getCodigo().charAt(pos) : 0;
            count[index]++;
        }

        // Modifica count para armazenar as posições reais dos caracteres
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array ordenado
        for (int i = n - 1; i >= 0; i--) {
            int index = pos < pedidos[i].getCodigo().length() ? pedidos[i].getCodigo().charAt(pos) : 0;
            output[count[index] - 1] = pedidos[i];
            count[index]--;
        }

        // Copia os elementos ordenados para o array original
        System.arraycopy(output, 0, pedidos, 0, n);
    }

    // Função principal para Radix Sort
    @Override
    public void sort(Pedido[] pedidos) {
        int maxLen = getMax(pedidos);  // Pega o comprimento máximo de código alfanumérico

        // Faz a ordenação de cada dígito, começando pelo último (ordenação LSD)
        for (int pos = maxLen - 1; pos >= 0; pos--) {
            countingSort(pedidos, pos);
        }
    }
}
