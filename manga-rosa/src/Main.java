import br.com.mangarosa.model.Pedido;
import br.com.mangarosa.sort.SortPedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Gera uma lista de 10 pedidos com IDs alfanuméricos aleatórios
        List<Pedido> pedidos = generateRandomPedidos(10);

        // Exibe os pedidos antes da ordenação
        System.out.println("Pedidos antes da ordenação:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Cria uma instância da classe de ordenação
        SortPedidos sorter = new SortPedidos();

        // Ordena os pedidos
        List<Pedido> sortedPedidos = sorter.sort(pedidos);

        // Exibe os pedidos após a ordenação
        System.out.println("\nPedidos após a ordenação:");
        for (Pedido pedido : sortedPedidos) {
            System.out.println(pedido);
        }

        // Mostra o número de comparações feitas pelo algoritmo
        System.out.println("\nNúmero de comparações realizadas: " + sorter.numberComparisons());
    }

    // Gera uma lista de pedidos com IDs alfanuméricos aleatórios
    private static List<Pedido> generateRandomPedidos(int size) {
        List<Pedido> pedidos = new ArrayList<>(size);
        Random random = new Random();
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Letras e números

        // Gera um ID de 13 caracteres alfanuméricos para cada pedido
        for (int i = 0; i < size; i++) {
            char[] id = new char[13];
            for (int j = 0; j < 13; j++) {
                id[j] = alphanumeric.charAt(random.nextInt(alphanumeric.length()));
            }
            pedidos.add(new Pedido(id)); // Adiciona o pedido à lista
        }

        return pedidos;
    }
}
