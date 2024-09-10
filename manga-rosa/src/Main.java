//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.sorting.Ordenation;

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = new ArrayList<>();

        // Gerar 100 entradas de pedidos com IDs alfanuméricos de 13 caracteres
        for (int i = 0; i < 100; i++) {
            String id = generateRandomId(13);
            pedidos.add(new Pedido(id.toCharArray()));
        }

        // Imprimindo a lista antes de ordenar
        System.out.println("Antes de ordenar:");
        pedidos.forEach(System.out::println);

        // Criando o RadixSortPedido e ordenando a lista
        Ordenation sorter = new Ordenation();
        List<Pedido> sortedPedidos = sorter.sort(pedidos);

        // Imprimindo a lista após a ordenação
        System.out.println("\nApós ordenar:");
        sortedPedidos.forEach(System.out::println);

        // Mostrando o número de comparações (operações de inserção no contexto do sort)
        System.out.println("\nNúmero de comparações: " + sorter.numberComparisons());
    }

    // Função para gerar um ID alfanumérico aleatório de tamanho especificado
    private static String generateRandomId(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }
        return id.toString();
    }
}
