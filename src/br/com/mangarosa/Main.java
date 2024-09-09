package br.com.mangarosa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mangarosa.model.*;

public class Main {
    public static void main(String[] args) {
        int[] tamanhos = {10, 100, 1000, 10000, 100000};
        for (int tamanho : tamanhos) {
            List<Pedido> pedidos = gerarPedidosAleatorios(tamanho);
            RadixSort radixSort = new RadixSort();
            radixSort.sort(pedidos);
            int comparacoes = radixSort.numberComparisons();
            System.out.println("Tamanho: " + tamanho + ", Comparações: " + comparacoes);
        }
    }

    private static List<Pedido> gerarPedidosAleatorios(int tamanho) {
        List<Pedido> pedidos = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                if (random.nextBoolean()) {
                    id[j] = (char) ('0' + random.nextInt(10));
                } else {
                    id[j] = (char) ('A' + random.nextInt(26));
                }
            }
            pedidos.add(new Pedido(id));
        }
        return pedidos;
    }

}
