package br.com.mangarosa.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mangarosa.model.Pedido;

public class RadixSortTest {

    // Gerar lista de Pedidos
    public static List<Pedido> generatePedidos(int numPedidos) {
        List<Pedido> pedidos = new ArrayList<>();
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < numPedidos; i++) {
            StringBuilder id = new StringBuilder(13);
            for (int j = 0; j < 13; j++) {
                id.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            pedidos.add(new Pedido(id.toString().toCharArray()));
        }

        return pedidos;
    }

    // Método para testar o desempenho do Radix Sort com diferentes tamanhos de entrada
    public static void runTest(int numPedidos) {
        // Gerar uma lista de pedidos
        List<Pedido> pedidos = generatePedidos(numPedidos);

        Radix radixSort = new Radix();

        // Medir o tempo de execução do algoritmo
        long startTime = System.currentTimeMillis();
        List<Pedido> sortedPedidos = radixSort.sort(pedidos);
        long endTime = System.currentTimeMillis();

        System.out.println("Número de pedidos: " + numPedidos);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");
        System.out.println("Número de comparações: " + radixSort.numberComparisons());
        System.out.println("Pedidos ordenados: " + sortedPedidos.size());
    }
}
