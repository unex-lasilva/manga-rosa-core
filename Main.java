package Projeto1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Criação de alguns pedidos para testar o algoritmo
        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new Pedido("A12B34C56D789".toCharArray()));
        pedidos.add(new Pedido("B12A34C56D789".toCharArray()));
        pedidos.add(new Pedido("C34B12A56D789".toCharArray()));
        pedidos.add(new Pedido("Z98Y76X54W321".toCharArray()));
        pedidos.add(new Pedido("A11B22C33D444".toCharArray()));

        // Exibe lista original
        System.out.println("Lista original de pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Instancia o algoritmo RadixSort
        Sort<Pedido> radixSort = new RadixSort();

        // Ordena os pedidos usando o algoritmo
        List<Pedido> pedidosOrdenados = radixSort.sort(pedidos);

        // Exibe lista ordenada
        System.out.println("\nLista ordenada de pedidos:");
        for (Pedido pedido : pedidosOrdenados) {
            System.out.println(pedido);
        }

        // Mostra o número de comparações feitas
        System.out.println("\nNúmero de comparações / movimentações: " + radixSort.numberComparisons());

        // Teste de Performance
        int[] tamanhos = {10, 100, 1000, 10000, 100000};
        for (int tamanho : tamanhos) {
            List<Pedido> pedidosAleatorios = gerarPedidosAleatorios(tamanho);
            RadixSort radixSortTest = new RadixSort();
            radixSortTest.sort(pedidosAleatorios);
            int comparacoes = radixSortTest.numberComparisons();
            System.out.println("Tamanho: " + tamanho + ", Comparações: " + comparacoes);
        }
    }

    // Método para gerar pedidos aleatórios
    private static List<Pedido> gerarPedidosAleatorios(int tamanho) {
        List<Pedido> pedidos = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                // Gera um caractere aleatório: '0'-'9' ou 'A'-'Z'
                if (random.nextBoolean()) {
                    id[j] = (char) ('0' + random.nextInt(10)); // Gera dígitos
                } else {
                    id[j] = (char) ('A' + random.nextInt(26)); // Gera letras
                }
            }
            pedidos.add(new Pedido(id));
        }
        return pedidos;
    }
}