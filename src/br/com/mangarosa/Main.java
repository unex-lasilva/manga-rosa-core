package br.com.mangarosa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mangarosa.model.*;

public class Main {
    public static void main(String[] args) {
        //cria um array de inteiros com diferentes tamanho
        int[] tamanhos = {10, 100, 1000, 10000, 100000};
        //itera sob o array de inteiros e aplica a ordenação do radix sort
        for (int tamanho : tamanhos) {
            //cria uma lista de pedidos aleatorios chamando o metodo gerarPedidosAleatorios passando tamanho
            List<Pedido> pedidos = gerarPedidosAleatorios(tamanho);
            //instancia o radix
            RadixSort radixSort = new RadixSort();
            //aplica o radix
            radixSort.sort(pedidos);
            //chama o metodo de contagem de comparações
            int comparacoes = radixSort.numberComparisons();
            System.out.println("Tamanho: " + tamanho + ", Comparações: " + comparacoes);
        }
    }
    //metodo para gerar pedidos aleatorios
    private static List<Pedido> gerarPedidosAleatorios(int tamanho) {
       //cria uma lista de pedidos
        List<Pedido> pedidos = new ArrayList<>();
        //instancia a biblioteca random para gerar os valores aleatorios
        Random random = new Random();
        //itera na variavel tamanho passada como argumento
        for (int i = 0; i < tamanho; i++) {
            //cria um array de char passando 13 caracteres
            char[] id = new char[13];
            // Laço aninhado com 13 iterações para gerar valores aleatórios (números ou letras) e atribuí-los ao array 'id'
            for (int j = 0; j < 13; j++) {
                if (random.nextBoolean()) {
                    id[j] = (char) ('0' + random.nextInt(10)); // gera numeros aleatorios de 0 a 9
                } else {
                    id[j] = (char) ('A' + random.nextInt(26)); // gera letras aleatorias de A-Z
                }
            }
            pedidos.add(new Pedido(id));
        }
        return pedidos;
    }

}
