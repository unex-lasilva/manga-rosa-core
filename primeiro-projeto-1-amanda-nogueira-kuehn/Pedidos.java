package com.mycompany.pedidos;

import java.util.Arrays;

public class Pedidos {

    public static void main(String[] args) {
        // Exemplo de códigos alfanuméricos de 13 caracteres
        String[] pedidos = {"A123B45678901", "C789D12345672", "B456A12345673", "A123C78945604"};

        System.out.println("Antes da ordenação: " + Arrays.toString(pedidos));
        
        // Ordena os pedidos usando Counting Sort adaptado para strings de tamanho fixo
        countingSortPedidos(pedidos);
        
        System.out.println("Depois da ordenação: " + Arrays.toString(pedidos));
    }

    public static void countingSortPedidos(String[] pedidos) {
        // Ordena por cada caractere, começando do último para o primeiro
        int maxLength = 13; // tamanho fixo dos códigos alfanuméricos
        int numBuckets = 256; // número de possíveis caracteres ASCII

        // Itera sobre cada posição do final para o início
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            // Cria o array de contagem para cada caractere
            int[] count = new int[numBuckets];
            String[] output = new String[pedidos.length];

            // Conta a ocorrência de cada caractere na posição atual
            for (String pedido : pedidos) {
                count[pedido.charAt(pos)]++;
            }

            // Modifica o count para armazenar a posição de cada caractere
            for (int i = 1; i < numBuckets; i++) {
                count[i] += count[i - 1];
            }

            // Constrói o array de saída ordenado pela posição atual
            for (int i = pedidos.length - 1; i >= 0; i--) {
                char currentChar = pedidos[i].charAt(pos);
                output[count[currentChar] - 1] = pedidos[i];
                count[currentChar]--;
            }

            // Copia o array ordenado de volta para o array original
            System.arraycopy(output, 0, pedidos, 0, pedidos.length);
        }
    }
}
